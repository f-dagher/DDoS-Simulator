/*
 * This program is in charge of commencing the attacks on the Server.
 */



package a2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Coordinator {
	public static int flag = 0;
	public static void main(String[] args) {
		try 
		{	
			Socket socket = new Socket(Node.HOST, Node.serverPort);
			//Setup streams in order to read and write to the server 
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	        
	        System.out.println("Welcome to the Coordinator. Let the nodes know when to attack with command attack");
	        
	        Scanner sc = new Scanner(System.in);
	        
	        /*
	         * If the command is attack, wait for the user input and see how many attacks they want.
	         */
	        
	        String cmd = "";
	        while(!(cmd.equals("attack")))
	        {
	        	cmd = sc.nextLine();
	        	out.println(cmd);
	        }
	        
	        System.out.println("How many nodes should attack?");
	        
	        cmd = "";
	        while(flag == 0)
	        {
	        	cmd = sc.nextLine();
	        	out.println(cmd);
	        	flag = 1;
	        }
	        
	        
	        //close all streams
	        socket.close();
	        in.close();
	        out.close();
	        stdIn.close();
	        sc.close();

	        
		} catch (IOException ioe) {
			System.err.println("Error in I/O");
            System.err.println(ioe.getMessage());
            System.exit(-1);
		}	

	}

}
