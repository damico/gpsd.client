package org.jdamico.gpsd.client.threads;

import org.jdamico.gpsd.client.GpsdClientRuntime;

public class OutputThread extends Thread {
	
	public void run() {
		GpsdClientRuntime.readUntil();
	}

}
