package no.hvl.dat110.messaging;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import no.hvl.dat110.TODO;

public class Connection {

	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection

	public Connection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream(socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	//Tar en message, encaspulater den, og sender den med outStream
	public void send(Message message) {
		
		byte[] encapsulated = message.encapsulate();
		
						try {
			
		outStream.write(encapsulated);
			
						} catch (IOException e) {
							e.printStackTrace();
						}
	}

	//Mottar en Message i form av encapsulated, opprettet en ny message med en decapsulated byte[]. 
	public Message receive() {

		Message message = new Message();
		byte[] recvbuf = new byte[MessageConfig.SEGMENTSIZE];		
					
						try {
		
		inStream.read(recvbuf, 0, recvbuf.length);
		
		message.decapsulate(recvbuf);	
						
						} catch (IOException e) {
							e.printStackTrace();}
		return message;

	}

	// close the connection by closing streams and the underlying socket
	public void close() {

		try {

			outStream.close();
			inStream.close();

			socket.close();
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}