/**
 * TODO
 * 
 */
package com.aitongyi.rabbitmq.publish;

/**
 * @author hushuang
 *
 */
import com.rabbitmq.client.*;

import java.io.IOException;

public class ReceiveLogs1 {
	private static final String EXCHANGE_NAME = "logs";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

//		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        //默认生成一个队列
		String queueName = channel.queueDeclare().getQueue();
		System.out.println("queueName:" + queueName);
		//将队列绑定在交换机上
		channel.queueBind(queueName, EXCHANGE_NAME, "");

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + message + "'");
			}
		};
		channel.basicConsume(queueName, true, consumer);
	}
}
