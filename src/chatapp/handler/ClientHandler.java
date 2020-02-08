package chatapp.handler;

import java.util.ArrayList;
import java.util.List;

import chatapp.client.model.Client;
import chatapp.client.view.MessageFrame;
import chatapp.server.Server;

public class ClientHandler extends Thread{
      private Server server;
      private Client client;
      private static List<MessageFrame> messageFrames = new ArrayList<MessageFrame>();
      
      public ClientHandler(Server server, Client client) {
         this.server = server;
         this.client = client;
      }
      
      public List<MessageFrame> getMessageFrames(){
    	  return messageFrames;
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
       MessageFrame messageFrame = new MessageFrame(messageFrames,client,server);
       
       List<ClientHandler> handlers = server.getHandlers();
       
       for(ClientHandler handler : handlers) {
    	   if(handler != this) {
    		   messageFrame.addLabel(handler.getClient().getUsername()+" is online");
    	   }
       }
       
       for(MessageFrame frame : messageFrames) {
    	   frame.addLabel(client.getUsername()+" is online");
    	   frame.revalidate();
    	   frame.repaint();
       }
       messageFrames.add(messageFrame);
     }

}
