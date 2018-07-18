package com.fit.test;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class Test {
 
	private static final Logger logger = LoggerFactory.getLogger(Test.class);
 
	private static boolean running = true;
 
	public static void main(String[] args) throws IOException {
 
		final AtomicInteger counter = new AtomicInteger();
 
		new Thread("my-thread") {
			@Override
			public void run() {
				while (running) {
					logger.info("Hello World - {}", counter.getAndIncrement());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						logger.error("my-thread error", e);
					}
				}
			}
		}.start();
 
		ServerSocket server = new ServerSocket(8001);
		Socket socket = server.accept();
		BufferedReader buffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String line = null;
		while (running && (line = buffer.readLine()) != null && "bye".equals(line)) {
			logger.info("receive command : bye");
			running = false;
		}
		buffer.close();
		socket.close();
		server.close();
	}
}
