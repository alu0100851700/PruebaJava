import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import java.util.Timer;
import java.util.TimerTask;

public class Ventana extends JFrame implements ActionListener{
	
	private ToolBarInit barraInicial;
	private ToolBarRobot barraRobot;
	private Matrix matriz;

	Timer timer;
    

	// Constructor
	public Ventana()  {
		barraInicial = new ToolBarInit();	
		barraRobot = new ToolBarRobot();
		
		barraInicial.botonCrear.addActionListener(this);
		barraRobot.crearRobot.addActionListener(this);
		barraRobot.crearRehen.addActionListener(this);
		barraRobot.reset.addActionListener(this);
		barraRobot.iniciar.addActionListener(this);
		
		timer = new Timer();
		
		setLayout(new BorderLayout());
		add(barraInicial, BorderLayout.PAGE_START);
		
		pack();				//Ajusta el tama�o de la ventana a los elementos
		
		setResizable(true);	//Se cambiara a false para no permitir que modifiquen el tama�o de la ventana
		
		setBounds(100, 100, 400, 68);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public void actionPerformed( ActionEvent evt ) {
		//Si se hace click en el boton crear matriz
		if( evt.getSource() == barraInicial.botonCrear){
		matriz = new Matrix(Integer.parseInt(barraInicial.text_fila.getText()),Integer.parseInt(barraInicial.text_col.getText()));
		setLayout(new BorderLayout());
		add(matriz, BorderLayout.CENTER);
		pack();
		barraInicial.setVisible(false);
		add(barraRobot, BorderLayout.NORTH);
		}
		if( evt.getSource() == barraRobot.crearRobot )
		{
			matriz.ponerRobot = true;
		}
		if( evt.getSource() == barraRobot.crearRehen )
		{
			matriz.ponerRehen = true;
		}
		if( evt.getSource() == barraRobot.reset )
		{
			matriz.reset();
		}
		if( evt.getSource() == barraRobot.iniciar )
		{
				matriz.initAlgoritmo();
				timer.schedule(task,0,250);
		}
	}
	
	TimerTask task = new TimerTask() {
        int tic=0;

        @Override
        public void run()
        {
        	boolean s = matriz.iniciarAlgoritmo();
        	if (!s) {
        		timer.cancel();
        		task.cancel();
        		timer.purge();
        		return;
        	}
        }
        };
	
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
