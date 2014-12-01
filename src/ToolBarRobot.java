import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;


public class ToolBarRobot extends JToolBar{

	public JButton crearRobot;
	public JButton crearRehen;
	public JButton reset;
	public JButton iniciar; 
	
	
	// Constructor
	public ToolBarRobot() {
		this.setBounds(100, 100, 450, 300);
		
		crearRobot = new JButton();		
		crearRobot.setText("Poner Robot");
		
		crearRehen = new JButton();		
		crearRehen.setText("Poner Rehen");
		
		reset = new JButton();		
		reset.setText("Resetear tablero");
		
		iniciar = new JButton();		
		iniciar.setText("Iniciar");
		
		setBounds(10,10,300,30);
		add(crearRobot);
		add(crearRehen);
		add(reset);
		add(iniciar);
	}
}
