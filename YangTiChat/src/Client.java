import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.net.*;

public class Client {

	String nickName;
	String IpAdress;
	FrameMain CFrame;
	static Socket socket;
	DataOutputStream output;
	ClientInputData input;
	ClientOutputData outputData;
	
	Client(String nickName, String IpAdress){
		outputData = new ClientOutputData();
		this.nickName = nickName;
		this.IpAdress = IpAdress;
		CFrame = new FrameMain(nickName);
		CFrame.frame.setVisible(true);
		CFrame.send.addMouseListener(outputData);
		CFrame.field.addKeyListener(outputData);
		CFrame.frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent me) {
				try {
					socket.close();
				} catch(Exception e) {
					System.exit(0);
				}
			}
		});
		
		try {
			socket = new Socket(IpAdress,23890);
			output = new DataOutputStream(socket.getOutputStream());
			output.writeUTF(nickName);
			input = new ClientInputData();
			input.start();
		} catch(Exception e) {
			CFrame.text.setText("###서버에 연결할 수 없습니다."+"\n"+"###연결가능한 IP주소인지 확인 후 다시 시도해 주십시오.");
			CFrame.field.setEditable(false);
			CFrame.send.setEnabled(false);
		}
	}
	
	public static Socket getSocket() {
		return socket;
	}
}
