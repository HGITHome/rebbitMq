/**
 * TODO
 * 
 */
package com.aitongyi.rabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * 消息生产者
 * @author hushuang
 *
 */
public class Producer {

  private final static String QUEUE_NAME = "hello";

  public static void main(String[] argv) throws Exception {
    //新建渠道工厂
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    //创建链接
    Connection connection = factory.newConnection();
    //创建渠道
    Channel channel = connection.createChannel();
    //声明一个队列
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    //消息信息
    String message = "Hello World heyutang hhh!";
    //推送消息到生产队列
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
    System.out.println("P [x] Sent '" + message + "'");

    channel.close();
    connection.close();
  }
}
