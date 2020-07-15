package com.lakeqiu.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author lakeqiu
 */
@ChannelHandler.Sharable
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * @param ctx 上下文
     * @param msg 客户端发送的信息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("客户端[" + ctx.channel().remoteAddress() + "]发送信息："
                + byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("pipeline hashcode:" + ctx.pipeline().hashCode() + ",  handler hashcode:" + this.hashCode());
        ctx.fireChannelRead(msg);
        /*ctx.channel().eventLoop().schedule(() -> {
            System.out.println("开始");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {

            }
            System.out.println("结束");
        }, 5, TimeUnit.SECONDS);*/
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端^_^", CharsetUtil.UTF_8));
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("我加入ChannelPipeline了^_^");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("我退出ChannelPipeline了o(╥﹏╥)o");
    }
}
