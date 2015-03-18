import javax.swing.JFrame;

/**
 * @author Yassin Shahid - 12016316
 * Sets up the frame
 */
public class Controller {
	
	public static void main(String[] args) 
	{
		//Frame construction
		aFrame f = new aFrame();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(300,300);
//		f.setResizable(false);
	}
}
