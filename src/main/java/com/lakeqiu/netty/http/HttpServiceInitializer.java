package com.lakeqiu.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author lakeqiu
 */
public class HttpServiceInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // 加入http解编码器
        pipeline.addLast("MyHttpServerCodec", new HttpServerCodec());

        // 加入自己的业务处理器
        pipeline.addLast(new HttpServerHandler());
    }
}
