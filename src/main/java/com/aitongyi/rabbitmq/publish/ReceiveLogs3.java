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

public class ReceiveLogs3 {
	private static final String EXCHANGE_NAME = "logs";

	private static final String TASK_QUEUE_NAME = "test_queue_name";
	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
        //声明队列
		channel.queueDeclare(TASK_QUEUE_NAME, false, false, false, null);
        //将队列名和交换机绑定
		channel.queueBind(TASK_QUEUE_NAME, EXCHANGE_NAME, "");

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + message + "'");
			}
		};
		channel.basicConsume(TASK_QUEUE_NAME, true, consumer);
	}
}
