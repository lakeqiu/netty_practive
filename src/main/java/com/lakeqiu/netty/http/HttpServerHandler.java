package com.lakeqiu.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author lakeqiu
 */
@ChannelHandler.Sharable
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        // 判断是不是HTTPRequest请求
        if (msg instanceof HttpRequest) {
            System.out.println("pipeline hashcode:" + ctx.pipeline().hashCode() + ",  handler hashcode:" + this.hashCode());
            System.out.println("msg类型信息：" + msg.getClass().getName());
            System.out.println("客户端[" + ctx.channel().remoteAddress() + "]发来信息");

            // 构造信息回送客户端
            ByteBuf byteBuf = Unpooled.copiedBuffer("<h2>Hello, 我是netty客户端</h2>", CharsetUtil.UTF_8);

            // 构造Http协议的response，即Http响应
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=utf-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());

            // 回送
            ctx.channel().writeAndFlush(response);
        }
    }
}
