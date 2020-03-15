/**
 * TODO
 * 
 */
package com.aitongyi.rabbitmq.WorkQueue.helloworld;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * ��Ϣ������
 * 
 * @author hushuang
 * 
 */
public class C {

	private final static String QUEUE_NAME = "test_work_queue";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		final Channel channel = connection.createChannel();
		//�������
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.basicQos(1);
		System.out.println("C [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println("C [x] Received '" + message + "'");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					//�ֶ���ִ��Ϣ
					channel.basicAck(envelope.getDeliveryTag(),false);
				}
			}
		};
		//�Զ�Ӧ��ر�
		channel.basicConsume(QUEUE_NAME, false, consumer);
	}
}
