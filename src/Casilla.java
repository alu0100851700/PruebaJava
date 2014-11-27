import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.plaf.synth.SynthButtonUI;


public class Casilla extends JButton implements ActionListener{
	
	ImageIcon mina = new ImageIcon("mina.jpg");
	ImageIcon arena = new ImageIcon("arena.jpg");
	ImageIcon rehen1 = new ImageIcon("rehen1.jpg");
	ImageIcon robot1 = new ImageIcon("robot1.jpg");
	ImageIcon huella = new ImageIcon("huella.jpg");
	private int ancho, alto;
	
	//Estima Heuristica
	public int estHeuristica = -1;
	
	// Variables de control
	public boolean obstaculo = false;
	public boolean robot = false;
	public boolean rehen = false;
	private boolean visitado = false;

	public boolean ponerRobot = false;
	public boolean ponerRehen = false;
	// Constructor
	public Casilla(int anch, int alt) {
		addActionListener(this);
		
		ancho = anch;	alto = alt;
		
		Dimension newDimension =  new Dimension(ancho,alto);

	    setPreferredSize(newDimension);

	   
		setIcon(new ImageIcon(arena.getImage().getScaledInstance(ancho, alto,Image.SCALE_SMOOTH)));
		
	}
	
	public void reset()
	{
		obstaculo = false;
		robot = false;
		rehen = false;
		visitado = false;
		estHeuristica = -1;
		setIcon(new ImageIcon(arena.getImage().getScaledInstance(ancho, alto,Image.SCALE_SMOOTH)));
	}

	public void actObstaculo()
	{
		if( obstaculo == false && robot == false && rehen == false)
		{
		setIcon(new ImageIcon(mina.getImage().getScaledInstance(ancho, alto,Image.SCALE_SMOOTH)));
		obstaculo = true;
		}
		else if( obstaculo == false && robot == true )
			{
				setIcon(new ImageIcon(arena.getImage().getScaledInstance(ancho, alto,Image.SCALE_SMOOTH)));
				robot = false;
			}
		else if( obstaculo == false && rehen == true )
		{
			setIcon(new ImageIcon(arena.getImage().getScaledInstance(ancho, alto,Image.SCALE_SMOOTH)));
			rehen = false;
		}
		else if( obstaculo == true )
		{
			setIcon(new ImageIcon(arena.getImage().getScaledInstance(ancho, alto,Image.SCALE_SMOOTH)));
			obstaculo = false;
		}
	}
	
	public void actRobot()
	{
		if(( robot == false ) && ( ponerRobot == true ))
		{
		setIcon(new ImageIcon(robot1.getImage().getScaledInstance(ancho, alto,Image.SCALE_SMOOTH)));
		robot = true;
		ponerRobot = false;
		}
		else if(( robot == true )&& ( ponerRobot == false ))
			{
				setIcon(new ImageIcon(arena.getImage().getScaledInstance(ancho, alto,Image.SCALE_SMOOTH)));
				robot = false;
			}
	}

	
	public void actRehen()
	{
		if(( rehen == false ) && ( ponerRehen == true ))
		{
		setIcon(new ImageIcon(rehen1.getImage().getScaledInstance(ancho, alto,Image.SCALE_SMOOTH)));
		rehen = true;
		ponerRehen = false;
		}
		else if(( rehen == true )&& ( ponerRehen == false ))
			{
				setIcon(new ImageIcon(arena.getImage().getScaledInstance(ancho, alto,Image.SCALE_SMOOTH)));
				rehen = false;
			}
	}
	
	public void actVisitado()
	{
		setIcon(new ImageIcon(huella.getImage().getScaledInstance(ancho, alto,Image.SCALE_SMOOTH)));
		visitado = true;
	}
	
	
	// "Escucha"
	public void actionPerformed(ActionEvent evento) {
		if ( ponerRobot == false && ponerRehen == false)
			actObstaculo();
		else if ( ponerRobot == true && ponerRehen == false)
			actRobot();
		else if ( ponerRehen == true && ponerRobot == false)
			actRehen();
		
	}
}
