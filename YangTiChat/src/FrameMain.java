import java.awt.BorderLayout;
import java.net.*;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.List;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.*;

class FrameMain {
	JFrame frame;
	JPanel panelCenter;
	JPanel panelNorth;
	JPanel panelSouth;
	
	JTextField field;
	JButton send;
	
	JScrollPane scroll;
	JTextArea text;
	
	JLabel countLabel;
	JLabel nullLabel;
	JLabel dayLabel;
	InetAddress ip;
	List list;
	
	FrameMain(String nickName){
		frame = new JFrame(nickName+"님 즐거운 하루되세요.");
		
		panelCenter = new JPanel(new BorderLayout());
		panelNorth = new JPanel(new BorderLayout());
		panelSouth = new JPanel(new BorderLayout());
		
		text = new JTextArea();
		text.setLineWrap(true);// 자동 줄바꿈
		text.setEditable(false);
		
		scroll = new JScrollPane(text,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		field = new JTextField();
		send = new JButton("전송");
		try {
			ip = InetAddress.getLocalHost();
		} catch(Exception e) {
		}
		
		countLabel = new JLabel(ip+"아이피로 접속하셨습니다.");
		dayLabel = new JLabel("ConnectionTime : " + getToday()+" ");
		nullLabel = new JLabel(" ");
		list = new List();
		list.add(nickName);
		list.setFocusable(false);
		send.setName("send");
		
		panelSouth.add(field,"Center");
		panelSouth.add(send,"East");
		
		panelNorth.add(countLabel,"North");
		panelNorth.add(list,"Center");
		panelNorth.add(dayLabel,"West");
		panelNorth.add(nullLabel,"East");
		
		panelCenter.setSize(343,380);
		panelCenter.add(scroll);
		panelCenter.setLocation(0,50);
		
		panelNorth.setSize(342,40);
		panelNorth.setLocation(0,0);
		
		panelSouth.setSize(342,43);
		panelSouth.setLocation(0,430);
		
		frame.add(panelNorth,"North");
		frame.add(panelCenter,"Center");
		frame.add(panelSouth,"South");
		frame.setLayout(null);;
		frame.setSize(350,500);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		frame.setLocation(screenSize.width/2 - 350/2, screenSize.height/2-500/2);
		frame.setResizable(false);
			
	}
	
	public void paint(Graphics g) {
		frame.repaint();
		countLabel.repaint();
	}
	
	public String getToday() {
		String result;
		Date d = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyy.MM.dd");
		SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
		
		result = today.format(d) + " " + time.format(d);
		return result;
	}
}
