package org.jdamico.gpsd.client.threads;

import org.jdamico.gpsd.client.GpsdClientRuntime;
import org.jdamico.gpsd.client.entities.GpsTpvEntity;
import org.jdamico.gpsd.client.entities.GpsSkyEntity;
import com.google.gson.Gson;

public class VerifierThread extends Thread {
	
	public static Double X = null;
	public static Double Y = null;
	public static Double Z = null;
	
	public void run() {
		Gson gson = new Gson();
		GpsSkyEntity gpsSkyEntity;

		while(GpsdClientRuntime.shouldListenOutput) {
			try {
				if(GpsdClientRuntime.outputMessageMap.containsKey("WATCH") && GpsdClientRuntime.outputMessageMap.containsKey("TPV") && GpsdClientRuntime.outputMessageMap.containsKey("WATCH") && GpsdClientRuntime.outputMessageMap.containsKey("DEVICES") && GpsdClientRuntime.outputMessageMap.containsKey("SKY")) {
					gpsSkyEntity = gson.fromJson(GpsdClientRuntime.outputMessageMap.get("SKY"), GpsSkyEntity.class);
					if(gpsSkyEntity.getGdop() !=null && gpsSkyEntity.getGdop() < GpsdClientRuntime.DOP_MINIMAL_PRECISION && gpsSkyEntity.getPdop() !=null && gpsSkyEntity.getPdop() < GpsdClientRuntime.DOP_MINIMAL_PRECISION) {
						
						GpsTpvEntity gpsGstEntity = gson.fromJson(GpsdClientRuntime.outputMessageMap.get("TPV"), GpsTpvEntity.class);
						X = gpsGstEntity.getLon();
						Y = gpsGstEntity.getLat();
						Z = gpsGstEntity.getAlt();
						
						
						if(X != null && Y!=null && Z!=null) {
							GpsdClientRuntime.shouldListenOutput = false;
							System.out.println("POSITION COLLECTED =>  X: "+X + ", Y: "+Y+", Z: "+Z);
						}
						
					}else System.out.println("DOP without precision.");
				}else System.out.println("NO GPS CLASSES YET.");
				Thread.sleep(GpsdClientRuntime.PROCESS_INTERVAL_MS);
			} catch (InterruptedException e) {
				System.err.println("Exception at "+this.getClass().getCanonicalName()+": "+e.getMessage());
				System.exit(1);
			}
		}
	}
}
