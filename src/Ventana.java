import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;


public class Ventana extends JFrame implements ActionListener{
	private ToolBarInit barra;
	private Matrix matriz;
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
		//Si se hace click en el bot�n crear matriz
		if( evt.getSource() == barra.botonCrear){
		matriz = new Matrix(Integer.parseInt(barra.text_fila.getText()),Integer.parseInt(barra.text_col.getText()));
		this.setLayout(new BorderLayout());
		this.add(matriz, BorderLayout.CENTER);
		this.pack();
		barra.setVisible(false);
		}
	}
	
	/**
	 * Initialize the contents of the this.
	 */
	private void initialize() {
		barra = new ToolBarInit();	barra.botonCrear.addActionListener(this);
		this.setLayout(new BorderLayout());
		this.add(barra, BorderLayout.PAGE_START);
		
		this.pack();				//Ajusta el tama�o de la ventana a los elementos
		
		this.setResizable(true);	//Se cambiara a false para no permitir que modifiquen el tama�o de la ventana
		
		this.setBounds(100, 100, 400, 68);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
