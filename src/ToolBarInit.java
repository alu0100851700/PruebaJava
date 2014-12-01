import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;


public class ToolBarInit extends JToolBar{

	JTextField text_fila;	private JLabel lbl_fila;	int fila;
	JTextField text_col;	private JLabel lbl_col;		int col;
	public JButton botonCrear;
	
	

	// Constructor
	public ToolBarInit() {
		this.setBounds(100, 100, 450, 300);
		
		text_fila = new JTextField();
		text_col = new JTextField();
		
		botonCrear = new JButton();	botonCrear.setText("Crear");
		lbl_fila = new JLabel();	lbl_fila.setText("Filas: ");
		lbl_col = new JLabel();		lbl_col.setText("Columnas: ");
		
		
		
		
		this.setBounds(10,10,300,30);
		this.add(lbl_fila);		this.add(text_fila);
		this.add(lbl_col);		this.add(text_col);
		
		
	
		this.add(botonCrear);
	}
}
