package com.kcb.common.socket.kaicom;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class KaiComDecoder implements ProtocolDecoder {
	 private final Charset charset;
	 private int bufferLength = 128;
		 public KaiComDecoder(Charset charset) {
		        if (charset == null) {
		            throw new IllegalArgumentException("charset parameter shuld not be null");
		        }


		        this.charset = charset;

		        // Convert delimiter to ByteBuffer if not done yet.
		    }
		 public KaiComDecoder() {

		        this.charset = Charset.forName("UTF-8");

		        // Convert delimiter to ByteBuffer if not done yet.
		    }
	 
	@Override
	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception {
		String str = ioBufferToString(in);
		if(str != null){
			String[] list = str.split("\\|");
			if(list.length>1){
				if(list[1].compareToIgnoreCase("98") == 0||list[1].compareToIgnoreCase("01") == 0){
					 writeText(session, str, out);
					 return;
				}
			}
		}
	}
	
	
	public static String ioBufferToString(Object message)   
	{   
	      if (!(message instanceof IoBuffer))   
	      {   
	        return "";   
	      }   
	      IoBuffer ioBuffer = (IoBuffer) message;   
	      byte[] b = new byte [ioBuffer.limit()];   
	      ioBuffer.get(b);   
	      StringBuffer stringBuffer = new StringBuffer();   
	  
	      for (int i = 0; i < b.length; i++)   
	      {   
	  
	       stringBuffer.append((char) b [i]);   
	      }   
	       return stringBuffer.toString();   
	}  
	

	@Override
	public void finishDecode(IoSession session, ProtocolDecoderOutput out)
			throws Exception {
		// TODO Auto-generated method stub
		InetSocketAddress isa = (InetSocketAddress) session.getRemoteAddress();
		System.out.println("客户端:" + isa.getAddress().getHostAddress() + ":"
				+ isa.getPort() + "连接关闭了！");
	}

	@Override
	public void dispose(IoSession session) throws Exception {
		// TODO Auto-generated method stub
	}
    protected void writeText(IoSession session, String text, ProtocolDecoderOutput out) {
        out.write(text);
    }
}
