package com.kee.common.socket.server;

import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.kee.common.socket.action.SomeServer;


/**
 * mina服务端的的事件处理类
 * 
 * @author zhu
 */
public class MinaServerHandler extends IoHandlerAdapter {
	private SomeServer someServer;

	public void setSomeServer(SomeServer someServer) {
		this.someServer = someServer;
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
		try {
			System.out.println("客户端 " + session.getRemoteAddress() + " 关闭了连接");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 服务端接收消息
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		int result = someServer.doSome(message);
		String msg = message.toString();
		if ("quit".equals(msg)) {
			session.close(false);
			return;
		}
		// 对客户端做出的响应
		// 正常收到回复  OK_RECV
	    // 接收失败  OK_ERROR
		String response = "7   |OK_RECV";
		if(result==-1){
			response = "8   |OK_ERROR";
		}

		session.write(response);
	}

	/**
	 * 客户端连接的会话创建
	 */
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		InetSocketAddress isa = (InetSocketAddress) session.getRemoteAddress();
		System.out.println("客户端:" + isa.getAddress().getHostAddress() + ":"
				+ isa.getPort() + "连接进来了。");
		//sessions.put(session.getRemoteAddress().toString(), session);
	}

}
