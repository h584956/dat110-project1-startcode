package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.*;

public class Sensor extends RPCStub {

	private byte RPCID = 1;
	
	public int read() {
		
		
		byte[] marshalled = RPCUtils.marshallVoid(RPCID);
		
		byte[] call = rpcclient.call(marshalled);
		
		int temp = RPCUtils.unmarshallInteger(call);
		
		return temp;
	}
	
}
