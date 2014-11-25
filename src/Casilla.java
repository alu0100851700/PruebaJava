import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Casilla extends JButton implements ActionListener{
	
	ImageIcon bomba = new ImageIcon("lava.jpg");
	ImageIcon img = new ImageIcon("piedra.jpg");
	ImageIcon stone = new ImageIcon("stone.jpg");
	private int a=0;
	private int ancho, alto;
	
	// Variables de control
	private boolean obstaculo = false;
	private boolean robot = false;
	private boolean rehen = false;
	private boolean visitado = false;

	public boolean ponerRobot = false;
	
	// Constructor
	public Casilla(int anch, int alt) {
		this.addActionListener(this);
		ancho = anch;	alto = alt;
		this.setIcon(new ImageIcon(img.getImage().getScaledInstance(ancho, alto,Image.SCALE_SMOOTH)));
	}

	/*public void actualizar(){
		if(a%3 == 0){
			this.setIcon(new ImageIcon(bomba.getImage().getScaledInstance(ancho, alto,Image.SCALE_SMOOTH)));
			a++;
		}
		else if(a%3 == 1){
			this.setIcon(new ImageIcon(stone.getImage().getScaledInstance(ancho, alto,Image.SCALE_SMOOTH)));
			a++;
		}
		else if(a%3 == 2){
			this.setIcon(new ImageIcon(img.getImage().getScaledInstance(ancho, alto,Image.SCALE_SMOOTH)));
			a++;
		}
	}*/
		//Metodo para cambiar la imagen de los iconos.
		//Parametros: posicion fila, posicion columnas.
	
	
	
	public void actObstaculo()
	{
		if( obstaculo == false && robot == false)
		{
		setIcon(new ImageIcon(bomba.getImage().getScaledInstance(ancho, alto,Image.SCALE_SMOOTH)));
		obstaculo = true;
		}
		else if( obstaculo == false && robot == true )
			{
				setIcon(new ImageIcon(img.getImage().getScaledInstance(ancho, alto,Image.SCALE_SMOOTH)));
				obstaculo = false;
				robot = false;
			}
		else if( obstaculo == true )
		{
			setIcon(new ImageIcon(img.getImage().getScaledInstance(ancho, alto,Image.SCALE_SMOOTH)));
			obstaculo = false;
		}
	}
	
	public void actRobot()
	{
		if(( robot == false ) && ( ponerRobot == true ))
		{
		setIcon(new ImageIcon(stone.getImage().getScaledInstance(ancho, alto,Image.SCALE_SMOOTH)));
		robot = true;
		ponerRobot = false;
		}
		else if(( robot == true )&& ( ponerRobot == false ))
			{
				setIcon(new ImageIcon(img.getImage().getScaledInstance(ancho, alto,Image.SCALE_SMOOTH)));
				robot = false;
				ponerRobot = true;
			}
	}
	
	
	
	// "Escucha"
	public void actionPerformed(ActionEvent evento) {
		JButton button = (JButton) evento.getSource();
		if ( ponerRobot == false )
			actObstaculo();
		else if ( ponerRobot == true )
			actRobot();
		
	}
}
