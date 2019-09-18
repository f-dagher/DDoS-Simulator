/*-----------------------------------NODE ASSIGNMENT 2-----------------------------------------------------
 * 
 * 
 * 
 * 
 * 
 * 
 * This class listens and waits for the attack command and then creates x(user indicated amount), on the server
 */
package a2;

import java.io.*;
import java.net.*;

/*
 * Creates multiple threads each of which are clients and attack the server at the same time.
 */
class nodeThread implements Runnable{
	String clientID;
	
	public nodeThread(String clientID){
		this.clientID = clientID; 
	}
	
	//every time a new thread is created, do the following 
	public void run() {
		
		try {
			System.out.println("Process started: " + clientID);
			Socket socket = new Socket(Node.HOST, Node.wantedServerPort);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
   
	        String reader = in.readLine();
	        
	        System.out.println(reader);
	        
	        	        
	        socket.close();
	        out.close();
	        in.close();
		
			
		} catch (IOException ioe) {
			System.err.println("Error in I/O");
	        System.err.println(ioe.getMessage());
	        System.exit(-1);
		}
		
	}
}

public class Node{

	public static int wantedServerPort = Server.PORTNUMBER;
	public static int serverPort = 24606; //student number port
	public static String HOST = "localhost";
	
	public static int flag = 0;
	
	private static Thread thread;	
	
	/*
	 * Listen and wait for the command to attack 
	 */
	public static void listen(){
		
		try {
			ServerSocket serverSocket = new ServerSocket(serverPort);
			System.out.println("Node Server");
			System.out.println("Waiting for command.....");
			
			String cmd = "";
			
				Socket clientSocket = serverSocket.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				
				//wait for the command
				while ((cmd = in.readLine()) != null)
				{
					if(cmd.equals("attack"))
					{	
						// how many attackers?
						String tempCmd = in.readLine();
						while(flag == 0)
						{
							int attacks = Integer.parseInt(tempCmd);
							//create the indicated amount of indicated attack threads
							for (int i = 0; i < attacks; i++) {
								 nodeThread t = new nodeThread("Process" + i);
								 thread = new Thread(t);
								 thread.start();							 
							}
							//close server
							serverSocket.close();
							flag = 1;
						}
						//close server
						serverSocket.close();
						
					}
					
			}
			
		} catch (IOException ioe) {
			System.err.println("Error in I/O");
            System.err.println(ioe.getMessage());
            System.exit(-1);
		}
				
	}
	
	
	public static void main(String[] args)  {
		listen();
	}
	

}
