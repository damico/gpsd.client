package org.jdamico.gpsd.client.threads;

import org.jdamico.gpsd.client.Runtime;
import org.jdamico.gpsd.client.entities.GpsTpvEntity;
import org.jdamico.gpsd.client.entities.GpsSkyEntity;

import com.google.gson.Gson;

public class VerifierThread extends Thread {
	public void run() {
		Gson gson = new Gson();
		GpsSkyEntity gpsSkyEntity;
		boolean loopRun = true;
		while(loopRun) {
			try {
				if(Runtime.outputMessageMap.containsKey("WATCH") && Runtime.outputMessageMap.containsKey("TPV") && Runtime.outputMessageMap.containsKey("WATCH") && Runtime.outputMessageMap.containsKey("DEVICES") && Runtime.outputMessageMap.containsKey("SKY")) {
					gpsSkyEntity = gson.fromJson(Runtime.outputMessageMap.get("SKY"), GpsSkyEntity.class);
					if(gpsSkyEntity.getGdop() !=null && gpsSkyEntity.getGdop() < Runtime.DOP_MINIMAL_PRECISION && gpsSkyEntity.getPdop() !=null && gpsSkyEntity.getPdop() < Runtime.DOP_MINIMAL_PRECISION) {
						loopRun = false;
						GpsTpvEntity gpsGstEntity = gson.fromJson(Runtime.outputMessageMap.get("TPV"), GpsTpvEntity.class);
						System.out.println("POSITION COLLECTED =>  X: "+gpsGstEntity.getLon() + ", Y: "+gpsGstEntity.getLat()+", Z: "+gpsGstEntity.getAlt());
						System.exit(0);
					}else System.out.println("DOP without precision.");
				}else System.out.println("NO GPS CLASSES YET.");
				Thread.sleep(Runtime.PROCESS_INTERVAL_MS);
			} catch (InterruptedException e) {
				System.err.println("Exception at "+this.getClass().getCanonicalName()+": "+e.getMessage());
				System.exit(1);
			}
		}
	}
}
