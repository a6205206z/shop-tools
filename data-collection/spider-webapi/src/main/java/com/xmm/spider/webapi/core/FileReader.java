package com.xmm.spider.webapi.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Xingmima.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 *
 * @author Huke
 * @version com.xmm.spider.webapi.core.FileReader.java, v 0.1
 * @date 2016年8月30日 下午3:26:57
 */
public class FileReader {
    private static String ENCODE = "UTF-8";
    private static final Logger LOGGER = LoggerFactory.getLogger(FileReader.class);

    /**
     * Read string.
     *
     * @param file the file
     * @return the string
     */
    public static String Read(File file){
        StringBuffer content = null;
        RandomAccessFile fs = null;
        FileChannel channel = null;
        if(file.exists()){
            try {
                fs = new RandomAccessFile(file,"r");
                channel = fs.getChannel();

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                content = new StringBuffer();
                int i = channel.read(buffer);
                while(i>0){
                    content.append(getString(buffer));
                    i = channel.read(buffer);
                }
            }catch(Exception e){
                LOGGER.error(e.toString());
                content = new StringBuffer();
                content.append(e.toString());
            }finally {
                if(channel !=null){
                    try {
                        channel.close();
                        fs.close();
                    } catch (IOException e) {
                        LOGGER.error(e.toString());
                        content = new StringBuffer();
                        content.append(e.toString());
                    }
                }
            }
        }
        return content.toString();
    }

    private static String getString(ByteBuffer buffer) {
        Charset cs = Charset.forName(ENCODE);
        buffer.flip();
        CharBuffer charBuffer = cs.decode(buffer);
        buffer.flip();
        return charBuffer.toString();
    }
}
