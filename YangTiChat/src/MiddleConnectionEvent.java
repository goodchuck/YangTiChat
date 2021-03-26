import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.*;

public class MiddleConnectionEvent extends MouseAdapter{
	static Client client;
	static Server server;
	static boolean isClient;
	
	public void mouserPressed(MouseEvent me) {
		String name = me.getComponent().getName();
		if(name.equals("serverCheck")) {
			Main.IpAdress.setEditable(false);
			Main.isServer = true;
		} else if(name.equals("clientCheck")) {
			Main.IpAdress.setEditable(true);
			Main.isServer = false;
		} else if(name.equals("connect")) {
			if(Main.nickName.getText().trim().equals("") && !Main.IpAdress.getText().equals("loopback")) {
				JOptionPane.showMessageDialog(Main.panel, "닉네임을 입력해주세요.");//닉네임 입력여부 검사
			} else if(Main.isServer) {
				System.out.println("서버로 접속");
				isClient = false;
				Main.getInstance().setVisible();
				runServer(Main.nickName.getText());
			} else if(!Main.isServer) {
				if(Main.IpAdress.getText().equals("")) {
					JOptionPane.showMessageDialog(Main.panel, "IP주소를 입력해주세요.");
				} else {
					System.out.println("클라이언트로 접속");
					isClient = true;
					Main.getInstance().setVisible();
					runClient(Main.nickName.getText(),Main.IpAdress.getText());
				}
			}
		}
	}
	
	void runServer(String nickName) {
		Server server = new Server(nickName);
		this.server = server;
	}
	
	void runClient(String nickName, String IpAdress) {
		Client client = new Client(nickName,IpAdress);
		this.client = client;
	}
}

