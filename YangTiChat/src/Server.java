import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Server {

	static String nickName;
	static FrameMain SFrame;
	ServerAccept accept;
	ServerInputData input;
	ServerOutputData outputData;
	
	
	Server(String nickName){
		this.nickName = nickName;
		SFrame = new FrameMain(nickName);
		outputData = new ServerOutputData();
		SFrame.send.addMouseListener(outputData);
		SFrame.field.addKeyListener(outputData);
		
		SFrame.frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent me) {
				System.exit(0);
			}
		});
		
		SFrame.frame.setVisible(true);
		accept = new ServerAccept();
		accept.start();
	}
}
