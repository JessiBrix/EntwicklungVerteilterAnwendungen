// Datei: Uhrzeit.java

public class Uhrzeit implements Runnable
{
	private volatile Thread t;
    String name;

	public Uhrzeit(String name)
	{
		this.name = name;
	}

	public void start()
	{
		t = new Thread(this);
        t.setName(name);
		t.start();
	}

	public void stop()
	{
		t = null;
	}

	public void run()
	{
		while (Thread.currentThread() == t)
		{
			System.out.println(t.getName() + "  " + new java.util.Date());
			try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException e) { }
		}
	}

	public static void main(String[] args) throws java.io.IOException
	{
		Uhrzeit zeit1 = new Uhrzeit("Thread1");
		zeit1.start();
		Uhrzeit zeit2 = new Uhrzeit("Thread2");
		zeit2.start();

		System.in.read();	// blockiert bis RETURN
		zeit1.stop();
		zeit2.stop();
        System.out.println(Thread.MAX_PRIORITY);
        System.out.println(Thread.MIN_PRIORITY);
        System.out.println(Thread.NORM_PRIORITY);
		System.out.println("ENDE");
	}
}