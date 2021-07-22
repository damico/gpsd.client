package org.jdamico.gpsd.client.threads;

import org.jdamico.gpsd.client.Runtime;

public class OutputThread extends Thread {
	
	public void run() {
		Runtime.readUntil();
	}

}
