package cn.hisin.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class ActiveMqQueueTest {
	
	/*
	 * 测试queue方式发送和接收消息
	 */
	@Test
	public void TestQueueProducer() throws Exception{
		// 第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号
		//brokerURL服务器的ip及端口号
		ConnectionFactory connectionFactory = new  ActiveMQConnectionFactory("tcp://192.168.25.134:61616");
		//拿到连接
		Connection connection = connectionFactory.createConnection();
		//开启连接
		connection.start();
		//创建会话
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("test_query");
		//创建发送者
		MessageProducer producer = session.createProducer(queue);
		//写入消息
//		TextMessage textMessage = new ActiveMQTextMessage();
//		textMessage.setText("Hello,World, ActiveMQ!!!");
		//第二种方式
		TextMessage textMessage2 = session.createTextMessage("Hello,World, Hisin!!!");
		producer.send(textMessage2);
		producer.close();
		session.close();
		connection.close();
		
	}
	@Test
	public void TestQueueConsumer() throws Exception{
		// 第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号
		//brokerURL服务器的ip及端口号
		ConnectionFactory connectionFactory = new  ActiveMQConnectionFactory("tcp://192.168.25.134:61616");
		//拿到连接
		Connection connection = connectionFactory.createConnection();
		//开启连接
		connection.start();
		//创建会话
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Message message = session.createMessage();
		Queue queue = session.createQueue("test_query2");
		//创建接收者
		MessageConsumer consumer = session.createConsumer(queue);
		
			//接收消息监听是否消息发送
		
			consumer.setMessageListener(new MessageListener() {
				
				@Override
				public void onMessage(Message message) {
					try {
							TextMessage textMessage = (TextMessage)message;
							String text = textMessage.getText();
							//输出消息
							System.out.println(text);
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		System.in.read();
		consumer.close();
		session.close();
		connection.close();
		
	}
}
