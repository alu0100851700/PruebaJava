import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;


public class ToolBarInit implements ActionListener{

	private JTextField text_fila;	private JLabel lbl_fila;
	private JTextField text_col;	private JLabel lbl_col;
	
	private JToolBar barra;
	
	private JButton botonBorrar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the application.
	 */
	public ToolBarInit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		barra = new JToolBar();
		barra.setBounds(100, 100, 450, 300);
		
		text_fila = new JTextField();
		text_col = new JTextField();
		
		botonBorrar = new JButton();	botonBorrar.setText("Borrar");	botonBorrar.addActionListener(this);
		
		lbl_fila = new JLabel();	lbl_fila.setText("Filas:");
		lbl_col = new JLabel();		lbl_col.setText("Columnas:");
		
		barra.setBounds(10,10,300,30);
		frame.setLayout(null);
		
		barra.add(lbl_fila);	barra.add(text_fila);
		barra.add(lbl_col);		barra.add(text_col);
		barra.add(botonBorrar);
	}

}
