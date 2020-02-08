package chatapp.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import chatapp.client.Client;
import chatapp.handler.ClientHandler;

public class Server {
      private ServerSocket serverSocket ;
      private int port;
      private DataInputStream in;
      private DataOutputStream out;
      
      private List<ClientHandler> handlers = new ArrayList<>();
      
      public Server(int port) {
		this.port = port;
	}
      public List<ClientHandler> getHandlers(){
    	  return handlers;
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
    				Client client = new Client(username, clientSocket, in, out);
    				ClientHandler handler = new ClientHandler(this, client);
    				handlers.add(handler);
    				handler.start();
    				
    		 }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
}
