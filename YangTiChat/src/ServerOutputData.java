import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.net.Socket;
class ServerOutputData extends MouseAdapter implements KeyListener{

	String field;
	Iterator iterator;
	DataOutputStream output;
	Socket clientSocket;
	
	public void mousePressed(MouseEvent me) {
		field = Server.nickName+":"+MiddleConnectionEvent.server.SFrame.field.getText();
		
		if(!field.trim().equals("")) {
			MiddleConnectionEvent.server.SFrame.text.append("\n\r"+getTime()+field);
			MiddleConnectionEvent.server.SFrame.field.setText("");
			
			iterator = ServerAccept.clients.keySet().iterator();
			
			while(iterator.hasNext()) {
				try {
					clientSocket = (Socket)ServerAccept.clients.get(iterator.next());
					output = new DataOutputStream(clientSocket.getOutputStream());
					output.writeUTF("\n\r"+getTime()+field);
				} catch(Exception e) {
					
				}
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		if(ke.getKeyCode()==KeyEvent.VK_ENTER) {
			field = Server.nickName+":" + MiddleConnectionEvent.server.SFrame.field.getText();
			
			if(!field.trim().equals("")) {
				MiddleConnectionEvent.server.SFrame.text.append("\n\r"+getTime()+field);  //저장된 글을 서버측 화면에 우선 표시
				MiddleConnectionEvent.server.SFrame.field.setText("");	
				
				iterator = ServerAccept.clients.keySet().iterator();
				
				while(iterator.hasNext()) {
					try {
						clientSocket = (Socket)ServerAccept.clients.get(iterator.next());
						output = new DataOutputStream(clientSocket.getOutputStream());
						output.writeUTF("\n\r"+getTime()+field);
					} catch(Exception e) {
						
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		// TODO Auto-generated method stub
		
	}

	public String getTime(){
		String result;
		Date d = new Date();
		SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm:ss");
		result = "["+currentTime.format(d)+"] ";
		return result;
	}
}
