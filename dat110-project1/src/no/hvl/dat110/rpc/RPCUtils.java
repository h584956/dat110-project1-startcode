package no.hvl.dat110.rpc;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class RPCUtils {

	// Utility methods for marshalling and marshalling of parameters and return values
	// in RPC request and RPC responses
	// data bytearrays and return byte arrays is according to the 
	// RPC message syntax [rpcid,parameter/return value]
	
	public static byte[] marshallString(byte rpcid, String str) {

		byte[] payload = str.getBytes();
		
		byte[] encoded = new byte[str.length()+1];
		
		encoded[0] = rpcid;
		
		for(int i = 0; i < payload.length; i++) {
			encoded[i+1] = payload[i]; 
		}

		return encoded;
	}

	public static String unmarshallString(byte[] data) {

//		byte[] decodedBits = new byte[data.length -1];
//		
//		for(int i = 0; i < decodedBits.length; i++) {
//			decodedBits[i] = data[i+1];
//		}
//		
//		String decoded = new String(decodedBits);
		
		String decoded = new String(data, 1, data.length-1);
		
		return decoded;
	}

	public static byte[] marshallVoid(byte rpcid) {

		byte[] encoded = new byte[1];
		encoded[0] = rpcid;

		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {

		byte rpcid = data[0];
		data = new byte[1];
		data[0] = rpcid;
	}

	public static byte[] marshallBoolean(byte rpcid, boolean b) {

		byte[] encoded = new byte[2];

		encoded[0] = rpcid;

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(byte rpcid, int x) {

		Integer tall = x;
		String tallet = tall.toString();
		byte[] encoded = marshallString(rpcid, tallet);

		return encoded;
	}

	public static int unmarshallInteger(byte[] data) {

		int decoded = Integer.parseInt(unmarshallString(data));
		
		return decoded;

	}
}
