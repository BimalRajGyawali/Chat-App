package chatapp.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import chatapp.client.model.Client;
import chatapp.handler.ClientHandler;
import chatapp.server.Server;

public class MessageFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<MessageFrame> frames;
	private static List<String> messages = new ArrayList<String>();
	private Client client;
	private Server server;

	
	private JPanel contentPane;
	private JPanel panel1;
	private JPanel panel2;
	private JTextArea textArea;
	private JButton sendButton;
	private JPanel panel3;

	
	public MessageFrame(List<MessageFrame> frames, Client client, Server server) {
		 this.frames = frames;
		 this.client = client;
		 this.server = server;
         setBounds(100,200,400,300);
         contentPane = new JPanel();
         contentPane.setLayout(new BorderLayout());
         
         panel1 = new JPanel();
         addLabel("You can now chat\n ");

         for(String message : messages) {
        	 addLabel(message);
         }
         JScrollPane scrollPane = new JScrollPane(panel1);
         
         panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));
         contentPane.add(scrollPane, BorderLayout.CENTER);
         
         panel2 = new JPanel();
         panel2.setLayout(new FlowLayout());
         panel2.add(getTextArea());
         panel2.add(getButton());
         contentPane.add(panel2, BorderLayout.SOUTH);
         
         panel3 = new JPanel();
         JLabel label = new JLabel("Online Members");
         label.setFont(new Font("Arial", Font.BOLD, 12));
         panel3.add(label);
         panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
         JScrollPane scrollPane2 = new JScrollPane(panel3);
         contentPane.add(scrollPane2,BorderLayout.EAST);
         
         add(contentPane);
         
         setTitle("Username :"+client.getUsername());
         setVisible(true);
         setResizable(false);
         setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
          
         addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				int result = JOptionPane.showConfirmDialog(null, "Do you want to log out ?");
				if(result == JOptionPane.YES_OPTION) {
					handleLogOut();
					dispose();
					
				}
				
				
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				
				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	
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
						frame.revalidate();
						frame.repaint();
					}
					messages.add(client.getUsername()+" : "+textArea.getText());
					textArea.setText("");
				}
			});
	      }
		return sendButton;
	}
	public JTextArea getTextArea() {
		if(textArea == null) {
			textArea = new JTextArea("");
			textArea.setColumns(20);
			textArea.setMaximumSize(new Dimension(50, 50));
			
			
		}
		return textArea;
	}
	
	public void addLabel(String msg) {
		JLabel label = new JLabel(msg);
		label.setSize(80, 20);
		panel1.add(label);
		repaint();
		revalidate();
		
	}

	public void addToOnlineList(String username) {
		JLabel label = new JLabel(username);
		panel3.add(label);
	}
    
	public void handleLogOut() {
        
        	for(MessageFrame frame : frames) {
        		frame.addLabel(client.getUsername()+" is offline");
        		frame.revalidate();
   			    frame.repaint();
        	}
        	List<ClientHandler> handlers = server.getHandlers();
        	handlers.remove(client.getHandler());
        
        	
        	
        	
        	 for(MessageFrame frame :frames) {
        		 frame.panel3.removeAll();
             	 frame.panel3.repaint();
             	 frame.panel3.revalidate();
             	 frame.addToOnlineList("Online Members");
             	 
        		  for(ClientHandler handler : handlers) {
        			  if(handler.getClient().equals(frame.client)) {
        				  frame.addToOnlineList("You");
        			  }else {
        				  frame.addToOnlineList(handler.getClient().getUsername());
        		  }
        	 }
        		  frame.revalidate();
    			  frame.repaint();
             
            
               }
        	
        	
        }
	
	}



