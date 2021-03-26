import java.awt.*;

import javax.swing.*;
import java.awt.event.*;

class Main extends Frame{
Image img = null;
JPanel subground;
static JTextField nickName;
static JTextField IpAdress;
static JButton serverCheck;
static JButton clientCheck;
static JButton connect;
static JPanel panel;
static Boolean isServer = true;
static Main mc;

public Main(String title) {
	super(title);
	addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent we) {
			System.exit(0);
		}
	});
	
	Toolkit tk = Toolkit.getDefaultToolkit();
	img = tk.getImage(".\\image\\background.png");
	
	nickName = new JTextField();
	IpAdress = new JTextField();
	serverCheck = new JButton("방장");
	clientCheck = new JButton("클라");
	connect = new JButton("연결하기");
	panel = new JPanel();
	
	IpAdress.setLocation(115,320);
	nickName.setLocation(115,260);
	serverCheck.setLocation(115,290);
	clientCheck.setLocation(190,290);
	connect.setLocation(115,350);
	panel.setBounds(0,0,350,500);	
	
	IpAdress.setSize(142,20);
	nickName.setSize(142,20);
	serverCheck.setSize(65,20);
	clientCheck.setSize(65,20);
	connect.setSize(142,20);
	panel.setSize(350,500);
	
	nickName.setToolTipText("대화에 사용할 별명을 입력합니다..");
	clientCheck.setToolTipText("손님으로 접속");
	serverCheck.setToolTipText("방장으로 접속");
	IpAdress.setToolTipText("손님으로 접속하시려면 호스트 IP주소를 입력해주세요");
	IpAdress.setEditable(false);
	
	nickName.setName("nickName");
	clientCheck.setName("clientCheck");
	serverCheck.setName("serverCheck");
	IpAdress.setName("IpAdress");
	connect.setName("connect");
	
	clientCheck.addMouseListener(new MiddleConnectionEvent());		//이벤트 등록
	serverCheck.addMouseListener(new MiddleConnectionEvent());
	connect.addMouseListener(new MiddleConnectionEvent());
	
	panel.setLayout(null);
	setLayout(null);
	panel.add(IpAdress);
	panel.add(nickName);
	panel.add(serverCheck);
	panel.add(clientCheck);
	panel.add(connect);
	panel.repaint();
	add(panel);
	
	Dimension screenSize = tk.getScreenSize();
	
	setSize(350,500);
	setLocation(screenSize.width/2 - 350/2,screenSize.height/2-500/2);
	setResizable(false);
	setVisible(true);
	nickName.setFocusable(true);
}

public void paint(Graphics g) {
	g.drawImage(img, 0, 0, this);
	IpAdress.repaint();
	nickName.repaint();
	serverCheck.repaint();
	clientCheck.repaint();
	connect.repaint();
	
	if(IpAdress.getText().equalsIgnoreCase("loopback")){
		IpAdress.setEditable(false);
		connect.setText("테스트 모드");
	}
}

public void setVisible() {
	mc.setVisible(false);
	mc.dispose();
}

public static void main(String[] args){
	Main multiChat = new Main("MultiChat");
	mc = multiChat;						//인스턴스를 static 변수에 넣어두고
}

public static Main getInstance(){  //MiddleConnectionEvent에서 호출시 생성한 인스턴스를 넘겨준다
	return mc;
}
}
