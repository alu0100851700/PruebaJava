import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;


public class Ventana extends JFrame implements ActionListener{
	
	private ToolBarInit barraInicial;
	private ToolBarRobot barraRobot;
	private Matrix matriz;


	// Constructor
	public Ventana()  {
		barraInicial = new ToolBarInit();	
		barraRobot = new ToolBarRobot();
		
		barraInicial.botonCrear.addActionListener(this);
		barraRobot.crearRobot.addActionListener(this);
		
		this.setLayout(new BorderLayout());
		this.add(barraInicial, BorderLayout.PAGE_START);
		
		this.pack();				//Ajusta el tama�o de la ventana a los elementos
		
		this.setResizable(true);	//Se cambiara a false para no permitir que modifiquen el tama�o de la ventana
		
		//this.setBounds(100, 100, 400, 68);
		this.setExtendedState(this.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public void actionPerformed( ActionEvent evt ) {
		//Si se hace click en el boton crear matriz
		if( evt.getSource() == barraInicial.botonCrear){
		matriz = new Matrix(Integer.parseInt(barraInicial.text_fila.getText()),Integer.parseInt(barraInicial.text_col.getText()));
		this.setLayout(new BorderLayout());
		this.add(matriz, BorderLayout.CENTER);
		this.pack();
		barraInicial.setVisible(false);
		this.add(barraRobot, BorderLayout.NORTH);
		}
		if( evt.getSource() == barraRobot.crearRobot )
		{
			matriz.ponerRobot = true;
		}
	}
	
	
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

}
