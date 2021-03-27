import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataOutputStream;
import java.net.Socket;

class ClientOutputData extends MouseAdapter implements KeyListener{
	String field;
	DataOutputStream output;
	Socket socket;
	
	public void mousePressed(MouseEvent me) {
		field = MiddleConnectionEvent.client.CFrame.field.getText();
		
		if(!field.trim().equals("")) {
			MiddleConnectionEvent.client.CFrame.field.setText("");
			try {
				socket = Client.getSocket();
				output = new DataOutputStream(socket.getOutputStream());
				output.writeUTF(field);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void keyPressed(KeyEvent ke) {
		if(ke.getKeyCode() == KeyEvent.VK_ENTER) {
			field = MiddleConnectionEvent.client.CFrame.field.getText();
			
			if(!field.trim().equals("")) {
				MiddleConnectionEvent.client.CFrame.field.setText("");
				try {
					socket = Client.getSocket();
					output = new DataOutputStream(socket.getOutputStream());
					output.writeUTF(field);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
}
