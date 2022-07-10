package com.xymq.core;

import com.xymq.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author 黎勇炫
 * @date 2022年07月10日 14:48
 */
@Component
public class LevelDbStorageHelper implements StorageHelper{

    @Autowired
    private LevelDb levelDb;

    /**
     * 存储消息到队列消息容器
     * @param queueContainer 队列消息容器
     * @param message        消息对象
     * @return void
     * @author 黎勇炫
     * @create 2022/7/10
     * @email 1677685900@qq.com
     */
    @Override
    public void storeQueueMessage(ConcurrentHashMap<String, LinkedBlockingDeque<Message>> queueContainer, Message message) {
        if (queueContainer.containsKey(message.getDestination())) {
            //判断是否已经存在该队列
            if (!message.getIsTopPriority()) {
                //插队消息，放入队列头
                queueContainer.get(message.getDestination()).offer(message);
            } else {
                queueContainer.get(message.getDestination()).offerFirst(message);
            }
        } else {
            //创建容器
            queueContainer.put(message.getDestination(), new LinkedBlockingDeque<Message>());
            if (!message.getIsTopPriority()) {
                queueContainer.get(message.getDestination()).offer(message);
            } else {
                queueContainer.get(message.getDestination()).offerFirst(message);
            }
        }
        // 保存到数据库
        levelDb.putMessage(message.getMessageId(), message);
    }

    /**
     * 存储消息到主题消息容器
     *
     * @param topicContainer 主题消息容器
     * @param message        消息对象
     * @return void
     * @author 黎勇炫
     * @create 2022/7/10
     * @email 1677685900@qq.com
     */
    @Override
    public void storeTopicMessage(ConcurrentHashMap<String, LinkedBlockingDeque<Message>> topicContainer, Message message) {

    }

    /**
     * 存储消息到延时队列容器
     *
     * @param delayQueueMap 延时队列容器
     * @param message       消息对象
     * @return void
     * @author 黎勇炫
     * @create 2022/7/10
     * @email 1677685900@qq.com
     */
    @Override
    public void storeDelayMessage(ConcurrentHashMap<String, DelayQueue<Message>> delayQueueMap, Message message) {

    }

    /**
     * 存储消息到延时主题消息容器
     *
     * @param delayTopicMap 主题消息容器
     * @param message       消息对象
     * @return void
     * @author 黎勇炫
     * @create 2022/7/10
     * @email 1677685900@qq.com
     */
    @Override
    public void storeDelayTopicMessage(ConcurrentHashMap<String, DelayQueue<Message>> delayTopicMap, Message message) {

    }
}