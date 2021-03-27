import java.io.*;
import java.net.*;

class ClientInputData extends Thread{
	DataInputStream input;
	Socket socket;
	
	ClientInputData() {
		socket = Client.getSocket();
		try {
			input = new DataInputStream(socket.getInputStream());
		} catch(Exception e) {}
	}
	
	public void run() {
		String message;
		
		while(input!= null) {
			try {
				message = input.readUTF();
				MiddleConnectionEvent.client.CFrame.text.append(message);
			} catch(Exception e) {
				MiddleConnectionEvent.client.CFrame.text.append("\n\r"+"###"+"호스트가 퇴장하여 대화가 종료되었습니다.");
				MiddleConnectionEvent.client.CFrame.field.setEditable(false);
				MiddleConnectionEvent.client.CFrame.send.setEnabled(false);
				break;
				
			}
		}
		
	}
}
