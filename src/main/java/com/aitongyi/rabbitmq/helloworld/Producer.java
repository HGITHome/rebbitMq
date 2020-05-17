/**
 * TODO
 * 
 */
package com.aitongyi.rabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * ��Ϣ������
 * @author hushuang
 *
 */
public class Producer {

  private final static String QUEUE_NAME = "hello";

  public static void main(String[] argv) throws Exception {
    //�½���������
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    //��������
    Connection connection = factory.newConnection();
    //��������
    Channel channel = connection.createChannel();
    //����һ������
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    //��Ϣ��Ϣ
    String message = "Hello World heyutang hhh!";
    //������Ϣ����������
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
    System.out.println("P [x] Sent '" + message + "'");

    channel.close();
    connection.close();
  }
}