import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;


public class ToolBarRobot extends JToolBar{

	public JButton crearRobot;
	public JButton crearRehen;

	// Constructor
	public ToolBarRobot() {
		this.setBounds(100, 100, 450, 300);
		
		crearRobot = new JButton();		
		crearRobot.setText("Poner Robot");
		
		crearRehen = new JButton();		
		crearRehen.setText("Poner Rehen");
		
		setBounds(10,10,300,30);
		add(crearRobot);
		add(crearRehen);
	}
}