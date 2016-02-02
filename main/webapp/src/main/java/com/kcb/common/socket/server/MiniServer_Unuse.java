package com.kcb.common.socket.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * mina服务端. 这个类不会用,除非你要手动来启动.
 * 
 * @author zhu
 * 
 */
public class MiniServer_Unuse {
	private static final int port = 6889;

	public void start() throws IOException {
		// 用于创建服务端的监听
		IoAcceptor acceptor = new NioSocketAcceptor();
		// acceptor.getFilterChain().addLast("logger", new LoggingFilter() );
		// 编码过滤器
		// acceptor.getFilterChain().addLast("encode", new
		// ProtocolCodecFilter(new
		// TextLineCodecFactory(Charset.forName("UTF-8"))));
		acceptor.getFilterChain().addLast("encode",
				new ProtocolCodecFilter(new MyCodecFactory()));
		// 设置事件处理类\
		acceptor.setHandler(new MinaServerHandler());
		// 设置地址和端口
		acceptor.setDefaultLocalAddress(new InetSocketAddress("125.118.54.229",
				port));
		acceptor.bind();
		System.out.println("服务端准备完毕");
	}

	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("applicationContext-minaServer.xml");
		try {
			new MiniServer_Unuse().start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
