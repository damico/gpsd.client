package org.jdamico.gpsd.client.threads;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.jdamico.gpsd.client.TimeSpaceRuntime;
import org.jdamico.gpsd.client.entities.GpsSkyEntity;
import org.jdamico.gpsd.client.entities.GpsTpvEntity;
import org.jdamico.gpsd.client.entities.Satellite;

import com.google.gson.Gson;

import gapchenko.llttz.Converter;
import gapchenko.llttz.IConverter;
import gapchenko.llttz.stores.TimeZoneListStore;

public class ListenerThread extends Thread {
	
	public static Double X = null;
	public static Double Y = null;
	public static Double Z = null;
	public String statusChar = "!";
	
	IConverter iconv = Converter.getInstance(TimeZoneListStore.class);
	
	public void run() {
		Gson gson = new Gson();
		GpsSkyEntity gpsSkyEntity;
		GpsTpvEntity gpsTpvEntity;
		Map<String, String> usedSatsMap = new HashMap<>();
		Map<String, String> fixMap = new HashMap<>();
		Map<String, String> timeMap = new HashMap<>();
		Map<String, String> tzMap = new HashMap<>();
		
		while(TimeSpaceRuntime.shouldListenOutput) {
			try {
				if(TimeSpaceRuntime.outputMessageMap.containsKey("WATCH") && TimeSpaceRuntime.outputMessageMap.containsKey("TPV") && TimeSpaceRuntime.outputMessageMap.containsKey("WATCH") && TimeSpaceRuntime.outputMessageMap.containsKey("DEVICES") && TimeSpaceRuntime.outputMessageMap.containsKey("SKY")) {
					gpsSkyEntity = gson.fromJson(TimeSpaceRuntime.outputMessageMap.get("SKY"), GpsSkyEntity.class);
					String device = gpsSkyEntity.getDevice();
					usedSatsMap.put(device, "00");
					tzMap.put(device, "000");
					gpsTpvEntity = gson.fromJson(TimeSpaceRuntime.outputMessageMap.get("TPV"), GpsTpvEntity.class);
					if(gpsTpvEntity.getTime() != null && gpsTpvEntity.getTime().length() == 24) {
						System.out.println("gpsTpvEntity.getTime().length()            "+gpsTpvEntity.getTime().length());
						timeMap.put(gpsTpvEntity.getDevice(), gpsTpvEntity.getTime().split("T")[1].split("\\.")[0]);
					}
					if(gpsSkyEntity.getGdop() !=null && gpsSkyEntity.getGdop() < TimeSpaceRuntime.DOP_MINIMAL_PRECISION && gpsSkyEntity.getPdop() !=null && gpsSkyEntity.getPdop() < TimeSpaceRuntime.DOP_MINIMAL_PRECISION) {
						
						
						List<Satellite> sats = gpsSkyEntity.getSatellites();
						
						int sumUsedSats = 0;
						for (Satellite satellite : sats) {
							if(satellite.getUsed()) sumUsedSats++;
						}
						usedSatsMap.put(device, sumUsedSats >= 0 && sumUsedSats < 10 ? "0"+String.valueOf(sumUsedSats): String.valueOf(sumUsedSats) );
						
						
						GpsTpvEntity gpsGstEntity = gson.fromJson(TimeSpaceRuntime.outputMessageMap.get("TPV"), GpsTpvEntity.class);
						X = gpsGstEntity.getLon();
						Y = gpsGstEntity.getLat();
						Z = gpsGstEntity.getAlt();
						
						
						if(X != null && Y!=null && Z!=null) {
							//GpsdClientRuntime.shouldListenOutput = false;
							System.out.println("POSITION COLLECTED =>  X: "+X + ", Y: "+Y+", Z: "+Z);
							fixMap.put(device, "*");
							
							
							
							
							
							TimeZone tz = iconv.getTimeZone(Y, X);
							int offset = tz.getRawOffset()/3600000;
							String tzOffset = "000";
							if(offset>0 && offset <10) tzOffset = "0"+String.valueOf(offset);
							else if(offset < 0 && offset > -10) tzOffset = "-0"+String.valueOf(offset*-1);
							else if((offset > 0 && offset < 1) || (offset < 0 && offset > -1)) tzOffset = "000";
							tzMap.put(device, tzOffset);
							
						}
						
					}else {
						System.out.println("DOP without precision.");
						fixMap.put(device, "!");
					}
				}else System.out.println("NO GPS CLASSES YET.");
				
				Set<String> keySet = timeMap.keySet();
				Iterator<String> keySetIter = keySet.iterator();
				while (keySetIter.hasNext()) {
					String key = keySetIter.next(); 
					String log = usedSatsMap.get(key)+fixMap.get(key)+timeMap.get(key)+"TZ"+tzMap.get(key);
					System.out.println("-------------> "+key+" "+log);
					writeStrToFile(log, "/tmp/"+key.replaceAll("/", "_")+".gps");
				}
				
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.err.println("Exception at "+this.getClass().getCanonicalName()+": "+e.getMessage());
				System.exit(1);
			}
		}
	}
	public void writeStrToFile(String str, String fileName) {

		FileWriter fw = null;
		BufferedWriter out = null;
		try {
			fw = new FileWriter(fileName);
			out = new BufferedWriter(fw);
			out.write(str);  
		}
		catch (IOException e)
		{
			e.printStackTrace();

		}
		finally
		{
			if(out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(fw != null)
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}	
	}
}
