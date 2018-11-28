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
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

/*
 * 测试ActiveMQ Topic方式
 */
public class ActiveMqTopicTest {
	
	@Test
	public void producerTopicTest() throws Exception{
		// 第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号
		//brokerURL服务器的ip及端口号
		ConnectionFactory connectionFactory = new  ActiveMQConnectionFactory("tcp://192.168.25.134:61616");
		//拿到连接
		Connection connection = connectionFactory.createConnection();
		//开启连接
		connection.start();
		//创建会话
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		
		Topic topic = session.createTopic("topic-test");
		MessageProducer producer = session.createProducer(topic);
		TextMessage textMessage = session.createTextMessage("Hello,World,Test,Topic!!");
		String jmsType = textMessage.getJMSType();
		System.out.println("jmsType=="+jmsType);
		//创建发送者
		producer.send(textMessage);
		// 第九步：关闭资源。
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
		Topic topic = session.createTopic("topic-test");
		//创建接收者
		MessageConsumer consumer = session.createConsumer(topic);
		
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
