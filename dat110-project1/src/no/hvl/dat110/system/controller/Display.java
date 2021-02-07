package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.*;

public class Display extends RPCStub {

	private byte RPCID = 1;

	public void write(String message) {

		byte[] marshalled = RPCUtils.marshallString(RPCID, message);
		
		byte[] call = rpcclient.call(marshalled);
		
		RPCUtils.unmarshallString(call);
		
	}
}
