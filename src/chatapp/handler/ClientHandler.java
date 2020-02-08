package chatapp.handler;

import java.io.IOException;

import chatapp.client.Client;
import chatapp.server.Server;

public class ClientHandler extends Thread{
      private Server server;
      private Client client;
      
      public ClientHandler(Server server, Client client) {
         this.server = server;
         this.client = client;
      }

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
      
	@Override
	public void run() {
		try {
			client.getOutputStream().writeUTF(client.getUsername());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
