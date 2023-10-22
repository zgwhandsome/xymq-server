package com.xymq_cli.listener;

import java.util.EventListener;

/**
 * @author 张国伟
 * @date 2022年07月11日 15:18
 */
public interface MessageListener extends EventListener {
     /**
       * 获取消息
       */
    public void getMessage(MessageData data);
}