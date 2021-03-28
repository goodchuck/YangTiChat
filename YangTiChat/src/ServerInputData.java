import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

class ServerInputData extends Thread{
	DataInputStream input;
	DataOutputStream output;
	DataOutputStream subOutput;
	String message;
	Socket socket;
	Socket clientSocket;
	String nickName;
	Iterator iterator;
	
	ServerInputData(Socket socket, String nickName){
		this.nickName = nickName;
		this.socket = socket;
		
		try {
			input = new DataInputStream(socket.getInputStream());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		String message="";
		
		while(input!=null) {
			try {
				iterator = ServerAccept.clients.keySet().iterator();
				message = input.readUTF();
				MiddleConnectionEvent.server.SFrame.text.append("\n\r"+getTime()+nickName+":"+message);
				
				while(iterator.hasNext()) {
					clientSocket = (Socket)ServerAccept.clients.get(iterator.next());
					
					output = new DataOutputStream(clientSocket.getOutputStream());
					output.writeUTF("\n\r"+getTime()+nickName+":"+message);
				} 
			}catch(Exception e) {
					
				}
			}
		if(input==null) {
			MiddleConnectionEvent.server.SFrame.text.append("\n\r"+"###"+nickName+"님이 퇴장하셨습니다.");
			ServerAccept.clients.remove(nickName);
		}

		}
	
	public String getTime(){
		String result;
		Date d = new Date();
		SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm:ss");
		result = "["+currentTime.format(d)+"] ";
		return result;
	}
}
