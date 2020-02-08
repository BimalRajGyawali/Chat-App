package chatapp.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import chatapp.client.model.Client;
import chatapp.server.Server;

public class MessageFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<MessageFrame> frames;
	private static List<String> messages = new ArrayList<String>();
	private Client client;
//	private Server server;

	
	private JPanel contentPane;
	private JPanel panel1;
	private JPanel panel2;
	private JTextArea textArea;
	private JButton sendButton;

	
	public MessageFrame(List<MessageFrame> frames, Client client, Server server) {
		 this.frames = frames;
		 this.client = client;
//		 this.server = server;
         setBounds(100,200,400,300);
         contentPane = new JPanel();
         contentPane.setLayout(new BorderLayout());
         
         panel1 = new JPanel();
         addLabel("You can now chat\n ");

         for(String message : messages) {
        	 addLabel(message);
         }
         
         panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));
         contentPane.add(panel1, BorderLayout.CENTER);
         
         panel2 = new JPanel();
         panel2.setLayout(new FlowLayout());
         panel2.add(getTextArea());
         panel2.add(getButton());
         contentPane.add(panel2, BorderLayout.SOUTH);
         
         add(contentPane);
         
         setTitle("Username :"+client.getUsername());
         setVisible(true);
         setResizable(false);
         setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       }
	
	public List<String> getMessages(){
		return messages;
	}
	private JButton getButton() {
	      if(sendButton == null) {
	    	  sendButton = new JButton("  Send  ");
	    	  sendButton.setBackground(Color.BLUE);
	    	  sendButton.setForeground(Color.WHITE);
	    	  
	    	  sendButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					for(MessageFrame frame : frames) {
						frame.addLabel(client.getUsername()+" : "+textArea.getText());
						messages.add(client.getUsername()+" : "+textArea.getText());
						frame.revalidate();
						frame.repaint();
					}
					textArea.setText("                      ");
				}
			});
	      }
		return sendButton;
	}
	public JTextArea getTextArea() {
		if(textArea == null) {
			textArea = new JTextArea("                      ");
			textArea.setSize(390, 30);
			
			
		}
		return textArea;
	}
	
	public void addLabel(String msg) {
		JLabel label = new JLabel(msg);
		panel1.add(label);
	}

}
