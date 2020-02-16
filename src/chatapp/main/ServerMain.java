package chatapp.main;

import chatapp.server.Server;

public class ServerMain {
   public static void main(String[] args) {
	   Server server = new Server(6008);
	   server.start();
}
}
