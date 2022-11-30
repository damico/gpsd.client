package org.jdamico.gpsd.client.threads;

import org.jdamico.gpsd.client.TimeSpaceRuntime;

public class ListenerOutputThread extends Thread {
	
	public void run() {
		TimeSpaceRuntime.readUntil();
	}

}
