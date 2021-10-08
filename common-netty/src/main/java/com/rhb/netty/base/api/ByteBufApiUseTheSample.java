package com.rhb.netty.base.api;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;
import java.io.File;
import java.nio.channels.FileChannel;

/**
 *  ByteBuf-Api使用样例
 *
 *  Unpooled: 非池化工具类
 *  ByteBufAllocator: 分配器
 *  ByteBufUtil：小型工具类
 *
 * @author renhuibo
 * @date 2021/9/30 10:54
 */
public class ByteBufApiUseTheSample {

  public static void main(String[] args) {
    unpool();
    use();
  }

  public static void create(){
    NioServerSocketChannel channel = new NioServerSocketChannel();
    ByteBufAllocator alloc = channel.alloc();

    ByteBuf directBuffer = alloc.directBuffer();
    ByteBuf heapBuffer = alloc.heapBuffer();
    CompositeByteBuf compositeByteBuf = alloc.compositeBuffer();

    ByteBuf ioBuffer = alloc.ioBuffer();
  }

  public static void unpool(){
    ByteBuf directBuffer = Unpooled.directBuffer();
    ByteBuf buffer = Unpooled.buffer();
    CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();

    byte[] testBuf = "HelloWorld".getBytes();
    ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(testBuf);
    String s = wrappedBuffer.toString(CharsetUtil.UTF_8);
    System.out.println(s);
    ByteBuf copiedBuffer = Unpooled.copiedBuffer(testBuf);
    String s1 = copiedBuffer.toString(CharsetUtil.UTF_8);
    System.out.println(s1);

//    ByteBufUtil.encodeString();
  }

  public static void use(){
    // getXXX、setXXX系列 （不改变索引）
    ByteBuf directBuffer = Unpooled.directBuffer();
    int i = directBuffer.readableBytes();
    directBuffer.resetReaderIndex();
    directBuffer.markReaderIndex();
    directBuffer.markWriterIndex();
    directBuffer.getByte(i);
    directBuffer.setByte(i,i);
    directBuffer.capacity();
    ByteBufAllocator alloc = directBuffer.alloc();
    // ByteBuf -> byte[]
//    byte[] array = directBuffer.array();
    // ByteBuf -> String
    String s = directBuffer.toString(CharsetUtil.UTF_8);
    // String -> ByteBuf
    ByteBuf buf = Unpooled.copiedBuffer("HelloWord".getBytes(CharsetUtil.UTF_8));

    // 丢弃已读部分，将readerIndex设置为0，将已读部分的空间，扩展到可写的空间
    directBuffer.discardReadBytes();

    // slice截取：不会影响原本ByteBuf的读写索引
    ByteBuf slice = buf.slice(0, 5);
    System.out.println(slice.toString(CharsetUtil.UTF_8));

    System.out.println(buf.readerIndex());
    System.out.println(buf.writerIndex());
  }

  public static void io(){
    File file = new File("D:/1.txt");
    DefaultFileRegion defaultFileRegion = new DefaultFileRegion(file,0,file.length());
//    FileChannel fileChannel =
//    defaultFileRegion.transferTo();

  }

}
