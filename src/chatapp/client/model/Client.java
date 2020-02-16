package chatapp.client.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import chatapp.handler.ClientHandler;

public class Client {
	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private String username;
	private ClientHandler handler;
	
	public Client(String username,Socket socket,DataInputStream inputStream,DataOutputStream outputStream){
		this.username = username;
		this.socket = socket;
		this.inputStream = inputStream;
		this.outputStream = outputStream;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public DataInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(DataInputStream in) {
		this.inputStream = in;
	}

	public DataOutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(DataOutputStream out) {
		this.outputStream = out;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ClientHandler getHandler() {
		return handler;
	}

	public void setHandler(ClientHandler handler) {
		this.handler = handler;
	}
	
	

}
