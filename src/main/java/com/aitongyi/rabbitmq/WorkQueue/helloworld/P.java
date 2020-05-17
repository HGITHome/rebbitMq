/**
 * TODO
 * 
 */
package com.aitongyi.rabbitmq.WorkQueue.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 消息生产者
 * @author heyutang
 *
 */
public class P {

  private final static String QUEUE_NAME = "test_work_queue";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    //定义一个队列
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    //每个消费者发送确认消息之前，消息队列不发送下一个消息到消费者，一次只能处理一个消息
      channel.basicQos(1);
      for(int i = 0 ; i < 50 ; i ++) {
          String message = "Hello World heyutang hhh " + i;
          channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
          Thread.sleep(10);
      }

    channel.close();
    connection.close();
  }
}
