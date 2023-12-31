package com.xymq_common.protocol;

import com.alibaba.fastjson.JSON;
import com.xymq_common.message.Message;

import java.nio.charset.StandardCharsets;

/**
 * @author 张国伟
 * @date 2022年07月09日 17:55
 */
public class MessageUtils {

    /**
     * 将消息内容转换成字节数组
     * @param message 消息对象
     * @return byte[]
     * @author 张国伟
     * @create 2022/7/9
     * @email 1677685900@qq.com
     */
    public static byte[] change(Message message){
        return JSON.toJSONString(message).getBytes(StandardCharsets.UTF_8);
    }

    /**
     * 将字节数组转换成message对象
     * @param bytes
     * @return com.xymq.message.Message
     * @author 张国伟
     * @create 2022/7/9
     * @email 1677685900@qq.com
     */
    public static Message reverse(byte[] bytes){
        return JSON.parseObject(new String(bytes,StandardCharsets.UTF_8),Message.class);
    }

    public static Protocol message2Protocol(Message message){
        byte[] content = JSON.toJSONString(message).getBytes(StandardCharsets.UTF_8);
        Protocol protocol = new Protocol(content.length,content);
        return protocol;
    }
}
