import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Ventana extends JFrame implements ActionListener{
	private ToolBarInit barra;
	private Matrix matriz;
	private JButton Boton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					Ventana window = new Ventana();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();
	}

	
	public void actionPerformed( ActionEvent evt ) {
		//Si se hace click en el botón crear matriz
		if( evt.getSource() == barra.botonCrear){
		matriz = new Matrix(Integer.parseInt(barra.text_fila.getText()),Integer.parseInt(barra.text_col.getText()));
		this.add(matriz, BorderLayout.CENTER);
		barra.setVisible(false);
		}
	}
	
	/**
	 * Initialize the contents of the this.
	 */
	private void initialize() {
		barra = new ToolBarInit();	barra.botonCrear.addActionListener(this);
		this.add(barra, BorderLayout.PAGE_START);
		
		//this.pack();				//Ajusta el tamaño de la ventana a los elementos
		this.setLayout(null);
		this.setResizable(true);	//Se cambiara a false para no permitir que modifiquen el tamaño de la ventana
		
		this.setBounds(100, 100, 616, 639);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
