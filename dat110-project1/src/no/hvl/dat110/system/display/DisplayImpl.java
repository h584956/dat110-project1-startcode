package no.hvl.dat110.system.display;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.RPCImpl;
import no.hvl.dat110.rpc.RPCUtils;

public class DisplayImpl implements RPCImpl {

	public void write(String message) {
		System.out.println("DISPLAY:" + message);
	}
	
	public byte[] invoke(byte[] request) {
	
		
		String unmarshalledString = RPCUtils.unmarshallString(request);
		write(unmarshalledString);
		
		byte rpcid = request[0];
		
		byte[] reply = RPCUtils.marshallString(rpcid, unmarshalledString);
		
		return reply;
	}
}
