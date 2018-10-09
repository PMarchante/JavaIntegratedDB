package MovieDB;

import javax.swing.*;

public class APP extends JFrame {
	
	private JTextArea dataDisplay;
	private JComboBox comboBox;
	private JButton submitBtn;
	private JTextField yearTextInput;
	private JTextField titleTextInput;
	private JScrollPane scrollPane;
	private JLabel yearReleased;
	private JLabel movieTitle;
	private JPanel jpanel;
	
	public static void main (String[] args) {
		
		Runnable gui = () -> {
			APP frame = new APP();
			frame.setTitle("Movie list with embedded DB");
			frame.setContentPane(new APP().jpanel);
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			
		};//end runnable
		gui.run();
	}//end main
}//end APP
