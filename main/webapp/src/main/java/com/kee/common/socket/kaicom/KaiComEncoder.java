package com.kee.common.socket.kaicom;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class KaiComEncoder extends ProtocolEncoderAdapter {

	@Override
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		// TODO Auto-generated method stub
	        out.write(stringToIoBuffer(message.toString()));
	}
	
	public static IoBuffer stringToIoBuffer(String str)   
	{   
	  
	       byte bt[] = str.getBytes();   
	  
	       IoBuffer ioBuffer = IoBuffer.allocate(bt.length);   
	       ioBuffer.put(bt, 0, bt.length);   
	       ioBuffer.flip();   
	       return ioBuffer;   
	}   

}
