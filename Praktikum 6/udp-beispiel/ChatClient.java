//   Chat-Client

import java.net.*;
import java.io.*;
import java.awt.*;


public class ChatClient

{
    ChatFrame gui;
    String name;

    InetAddress group;
    MulticastSocket socket;

    int port=500;

    public ChatClient(String name)
    {
        this.name = name;

        // GUI erzeugen und Events behandeln
		
		gui = new ChatFrame("Chat mit IP-Multicast");
	 	gui.input.addKeyListener(new EnterListener(this,gui));
	 	gui.addWindowListener(new ExitListener(this));
		
		try
		{
		    socket = new MulticastSocket(port);
			group=InetAddress.getByName("228.6.7.8");
			socket.joinGroup(group);
			gui.output.append("Connected...\n");
			
			while(true)
			{
			    byte[] buffer = new byte[1000];
				DatagramPacket datagram = new DatagramPacket(buffer, buffer.length);
				socket.receive(datagram);
				String message = new String(datagram.getData());
				gui.output.append(message);
			}
		}
		catch(IOException e)
		{
		    e.printStackTrace();
		}

    }
	
	
	public void sendTextToChat(String message)
	{
	    message= name + ": " + message + "\n";
		byte[] buf = (message).getBytes();
		DatagramPacket dg = new DatagramPacket(buf, buf.length, group, port);
		try
		{
		    socket.send(dg);
		}
		catch(IOException ex)
		{
		    System.out.println(ex);
		}
	}
	
	public void disconnect()
	{
	}
	
	public static void main(String[] args)
	{
	    if(args.length!=1)
		throw new RuntimeException("Aufruf: java ChatClient <name>");
		ChatClient client = new ChatClient(args[0]);
	}
}