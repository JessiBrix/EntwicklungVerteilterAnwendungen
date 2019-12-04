
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Date;



public class JmsProducer extends JmsUtility 

{
   // Anwendungsspezifische Größen
	
	protected int messageSize = 255;
    protected long sleepTime = 0L;   
    protected boolean verbose = true;
    protected long messageID = 0;

    
    // Attribute aus dem Producer-Interface

    long timeToLive = 0;
    

    // JMS-Objekte 
    private Connection connection;
    private Session session;
    private MessageProducer producer;

    
    // Referenz auf die Oberfläche, damit dort etwas eingetragen werden kann
    
    private JmsProducerGui dieGui;
    
    
    public JmsProducer(JmsProducerGui dieGui)
    {
    	this.dieGui = dieGui;
    }


    
    protected void runTool(JmsProducer tool) 
    {
 
 /*   	
    	// Die Attribute sind in ToolSupport definiert.

//    	tool.clientID = null;
        this.url="tcp://172.22.144.157:61616";
        this.url="tcp://localhost:61616";
        this.durable = true;
        this.topic = true;  // true=Pub/Sub, false=P2P
        this.subject = "default";
//      tool.messageCount = 10;
//      tool.messageSize = 255;
//      tool.clientID = args[6];
//      tool.timeToLive = 0L;
//      tool.sleepTime = 10L;
//      tool.transacted = true";
*/
        tool.run();
    }

    public void run() {
        try {
            connection = createConnection();
            session = createSession(connection);
            producer = createProducer(session);

            System.out.println("Bereit");
        }
            catch (Exception e) {
                System.out.println("Caught: " + e);
                e.printStackTrace();
        }
    }
    public void doClose() 
    {
    	try
    	{
            close(connection, session);
        }
        catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }

    protected MessageProducer createProducer(Session session) throws JMSException 
    {
        MessageProducer producer = session.createProducer(destination);
        if (durable) 
        {
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        }
        else 
        {
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        }
        if( timeToLive!=0 )
            producer.setTimeToLive(timeToLive);
        return producer;
    }

    protected void sendMessage(String messageContent)  
    {
    	String strMessage;
        StringBuffer buffer = new StringBuffer(messageSize);
        String messageHeader = "\n\nNT: " + messageID + " dt=" + new Date()+"\n";
    	buffer.append(messageHeader);
        messageID++;

        if (   messageContent.equals("") 
        	|| messageContent.compareToIgnoreCase("quit") == 0 
        	|| messageContent.compareToIgnoreCase("exit") == 0)
        {
        }
        else
        
        buffer.append(messageContent);
        
        if (buffer.length() > messageSize) 
        {
            strMessage = buffer.substring(0, messageSize);
        }
        for (int i = buffer.length(); i < messageSize; i++) {
            buffer.append(' ');
        }
        strMessage = buffer.toString();

        	
        	if(strMessage!=null)
        	{
        		try
        		{
        		    TextMessage message = session.createTextMessage(strMessage);
                    producer.send(message);
                    if(transacted) 
                    {
                        session.commit();
                        Thread.sleep(sleepTime);
                    }
        		}
                catch(Exception e)
                {
                	System.out.println(e);
                }
        	}
    }


}
