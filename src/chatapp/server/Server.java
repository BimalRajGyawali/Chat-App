package chatapp.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
      private ServerSocket serverSocket ;
      private int port;
      private DataInputStream in;
      private DataOutputStream out;
      
      public Server(int port) {
		this.port = port;
	}
      
      public void start() {
    	  try {
			   serverSocket = new ServerSocket(port);
			   System.out.println("Server Started.......");


    		  while(true) {
    				Socket clientSocket = serverSocket.accept();
    				System.out.println("Connected...........");
    				in = new DataInputStream(clientSocket.getInputStream());
    				out = new DataOutputStream(clientSocket.getOutputStream());
    				String username = in.readUTF();
    				out.writeUTF(username);    				
    		 }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
}
