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
	
	// Para capturar el nivel de saturacion en %
	JTextField text_saturacion_rdm;	private JLabel lbl_saturacion_rdm;	int saturacion_rdm;
	public JButton botonRandom;
	
	// Constructor
	public ToolBarRobot() {
		setBounds(100, 100, 450, 300);
		
		crearRobot = new JButton();		
		crearRobot.setText("Poner Robot");
		
		crearRehen = new JButton();		
		crearRehen.setText("Poner Rehen");
		
		reset = new JButton();		
		reset.setText("Resetear tablero");
		
		iniciar = new JButton();		
		iniciar.setText("Iniciar");
		
		text_saturacion_rdm = new JTextField();
		lbl_saturacion_rdm = new JLabel();	lbl_saturacion_rdm.setText("Saturacion:");
		botonRandom = new JButton();	botonRandom.setText("Colocar obstaculos");
		
		add(lbl_saturacion_rdm);	add(text_saturacion_rdm);
		add(botonRandom);
		
		setBounds(10,10,300,30);
		add(crearRobot);
		add(crearRehen);
		add(reset);
		add(iniciar);
	}
}
