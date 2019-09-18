/*
 * The victim server. Sends the current time (in ms) back to the client.
 * Uses multiple threads to allow concurrent connections
 */


import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/*
 * Creates multiple threads for each connection
 */
class serverThread implements Runnable{
	private Socket clientSocket;
	
	
	serverThread(Socket clientSocket)
	{
		this.clientSocket = clientSocket;
	}
	
	/*
	 * Send back the time to the client. Runs for every thread
	 */
	public void run() {
		Logger logger = Logger.getLogger("My Log");
		

		try {
			System.out.println("Connection from: " + clientSocket.getInetAddress().getHostAddress() + " at " + new Date().toString());			
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			Date date = new Date();
			out.print(Long.toString(date.getTime()));
			
			Thread.sleep(10000);//wait 10s
			
			// log the events
	        logger.addHandler(Server.fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        Server.fh.setFormatter(formatter);
	        
	        logger.info(Long.toString(date.getTime())); 
	        
	        out.close();
	        clientSocket.close();	        
			
			//System.out.println("Thread ID:" + Thread.currentThread().getName() + " Date: " + new Date().getTime());
									
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

public class Server {
	
	final static int PORTNUMBER = 65534;
	private static ServerSocket serverSocket;
    private static serverThread serverThread;
    private static Thread thread;
    
    public static FileHandler fh;
    
/*/
 * wait for a connection and once a connection has been made, create a new thread to do the intended calculations
 */
	public static void main(String[] args) {
	
			try 
			{
				//create the file only once
				fh = new FileHandler("server.log");
				
				System.out.println("Welcome to the Server");
				int portNumber = PORTNUMBER;  

				//Setup a connection
				serverSocket = new ServerSocket(portNumber);
				
				//listen for a connection, and create a new thread for theat connection
				while(true)
				{
					serverThread = new serverThread(serverSocket.accept());
					thread = new Thread(serverThread);
					thread.start();
				}
				
			} catch (IOException ioe) {
				System.err.println("Error in I/O");
	            System.err.println(ioe.getMessage());
	            System.exit(-1);
			}
		}

	

}
