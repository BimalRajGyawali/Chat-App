package chatapp.client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LogIn extends JFrame{
   /**
	 * 
	 */
   private static final long serialVersionUID = 1L;
   private JTextField userTextField;
   private JButton chatButton;
   private JLabel textLabel;
   private JPanel panel;
   
   public LogIn(){
	   setResizable(false);
	   setBounds(100,200,400,300);
	   userTextField = getUserTextField();
	   chatButton = getChatButton();
	   textLabel = getTextLabel();
	   panel = new JPanel();
	   panel.setLayout(null);
	   panel.add(textLabel);
	   panel.add(userTextField);
	   panel.add(chatButton);
	   add(panel);
	   
	  
  }

private JLabel getTextLabel() {
	if(textLabel == null) {
		textLabel = new JLabel("Enter Username and chat");
		textLabel.setBounds(80, 30, 250, 30);
	}
	return textLabel;
}

private JButton getChatButton() {
	if(chatButton == null) {
		chatButton = new JButton("  Chat  ");
		chatButton.setBackground(Color.BLUE);
		chatButton.setForeground(Color.WHITE);
		chatButton.setBounds(80, 100, 250, 30);
		
		chatButton.addActionListener(new ActionListener() {
			DataOutputStream out;
			DataInputStream in;
			Socket clientSocket;
			int port = 6006;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String username = userTextField.getText();
				if(username.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Username is empty");
					return;
				}
				try {
					clientSocket = new Socket("localhost",port);
					out = new DataOutputStream(clientSocket.getOutputStream());
					in = new DataInputStream(clientSocket.getInputStream());
					out.writeUTF(username);
					
					String name = in.readUTF();
					JOptionPane.showMessageDialog(null, name);
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					try {
						out.close();
						clientSocket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}
	return chatButton;
}

private JTextField getUserTextField() {
	if(userTextField == null) {
		userTextField = new JTextField();
		userTextField.setBounds(80,60,250,30);
	}
	return userTextField;
}
}
