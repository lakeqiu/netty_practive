package com.lakeqiu.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * @author lakeqiu
 */
public class Test extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("===========================");
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("客户端[" + ctx.channel().remoteAddress() + "]发送信息："
                + byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("pipeline hashcode:" + ctx.pipeline().hashCode() + ",  handler hashcode:" + this.hashCode());
        ReferenceCountUtil.release(msg);
    }
}
