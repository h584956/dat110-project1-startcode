package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class Message {

	private byte[] payload;

	
	//Oppretter en payload med mindre enn 128 bytes
	public Message(byte[] payload) {
		if(payload.length < MessageConfig.SEGMENTSIZE ) {
			this.payload = payload; // TODO: check for length within boundary
		}
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

	
	//Putter payload inn i en kapsel, med lengden til payload på index 0 i encoded og dataen til payloaden på de resterende indexene i encoded.
	public byte[] encapsulate() {
		
		byte[] encoded = new byte[MessageConfig.SEGMENTSIZE];
		
		encoded[0] = (byte) payload.length;
		
		for(int i = 0; i < payload.length; i++) {
			encoded[i+1] = payload[i];
		}

		return encoded;
		
	}

	//Tar en encoded og lager en payload[] med størrelse utifra hva som er på index 0 i encoded (recieved). Putter inn dataene til encoded inn i payload 
	public void decapsulate(byte[] received) {

		int length = (int) received[0];
		
		payload = new byte[length];
		
		for(int i = 0; i < length; i++) {
			payload[i] = received[i+1];
		}
		
	}
}
