import java.util.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
import java.text.SimpleDateFormat;

class ServerAccept extends Thread{

	static HashMap clients = new HashMap();
	
	ServerSocket serverSocket;
	Socket socket;
	Socket clientSocket;
	DataInputStream inputName;
	String clientName;
	Iterator iterator;
	DataOutputStream output;
	
	public void ServerAccept() {
		Collections.synchronizedMap(clients); //동기화 처리
	}
	
	public void run() {
		try {
			MiddleConnectionEvent.server.SFrame.text.append("\n\r"+"###호스트로 접속하셨습니다.");
			MiddleConnectionEvent.server.SFrame.text.append("\n\r");
			MiddleConnectionEvent.server.SFrame.text.append("\n\r"+"###대화참여자를 기다립니다.");
			MiddleConnectionEvent.server.SFrame.text.append("\n\r");
			while(true) {
				serverSocket = new ServerSocket(23890);
				
				socket = serverSocket.accept();
				inputName = new DataInputStream(socket.getInputStream());
				clientName = inputName.readUTF();
				System.out.println(clientName);
				MiddleConnectionEvent.server.SFrame.text.append("\n\r"+"###"+getTime()+clientName+"님이 들어오셨습니다.");
				
				new ServerInputData(socket, clientName).start();
				if(!clients.isEmpty()) {
					iterator = clients.keySet().iterator();
					while(iterator.hasNext()){
						clientSocket = (Socket)ServerAccept.clients.get(iterator.next());
						output = new DataOutputStream(clientSocket.getOutputStream());
						System.out.println(output);
						output.writeUTF("###"+getTime()+clientName+"님이 들어오셨습니다.");
					}
				}
				
				clients.put(clientName,  socket);
				serverSocket.close();
			}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	public String getTime() {
		String result;
		Date d = new Date();
		SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm:ss");
		result =  "["+currentTime.format(d)+"] ";
		return result;
	}
	}

