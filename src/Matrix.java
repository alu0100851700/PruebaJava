import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;



public class Matrix extends JPanel implements ActionListener{

	private Casilla[][] botones;
	int ancho;
	int alto;
	int filas, columnas;
	
	public boolean ponerRobot = false;
	public boolean ponerRehen = false;
	
	int[] rehenCoor;	//Coordenada en la que se encuentra el rehen
	
	Matrix(int num_fil, int num_col)
	{
		rehenCoor = new int[2];
		
		setBounds(0,0,700,700);
		this.getMaximumSize();
		setVisible(true);
		filas = num_fil;
		columnas = num_col;
		ancho = this.getWidth();
		alto =  this.getHeight();
		
		//Parámetros GridLayout(num filas, num columnas, hgap(espacio horizontal), vgap(espacio vertical))
		//En este caso utilizamos el otro constructor para evitar espacios entre elementos.
		this.setLayout(new GridLayout(num_fil,num_col));
		
		//Crea la Matriz de Casillas
		botones = new Casilla[filas][columnas];
		for(int i = 0; i < filas; i ++){	
			for(int j = 0; j < columnas; j ++){
				botones[i][j] = new Casilla(ancho/num_fil,alto/num_col);
				botones[i][j].addActionListener(this);
				this.add(botones[i][j]);}}
		
		paintComponents(getGraphics());
		
		
		//Cerramos el constructor
	}
	
	public void reset(){
		for(int i = 0; i < filas; i ++){	
			for(int j = 0; j < columnas; j ++){
				ponerRobot = false;
				ponerRehen = false;
				botones[i][j].reset();	
				}}
	}
	
	public void initAlgoritmo()
	{
		for(int i = 0; i < filas; i ++){	
			for(int j = 0; j < columnas; j ++){
					if(botones[i][j].rehen == true){
						rehenCoor[0] = i;
						rehenCoor[1] = j;
		}}}
		
		for(int i = 0; i < filas; i ++){	
			for(int j = 0; j < columnas; j ++){
					if(!botones[i][j].obstaculo == true){
						botones[i][j].estHeuristica = Math.abs(i-rehenCoor[0]) + Math.abs(j-rehenCoor[1]); 
		}}}
	}
	
	
	public boolean iniciarAlgoritmo()
	{	
		int x=0; int y=0; int v=0; int w=0;
		for(int i = 0; i < filas; i ++){	
			for(int j = 0; j < columnas; j ++){
				if(botones[i][j].robot){
					for(int a=0; a<5 ; a++){
						if (a==0) {x=0; y=0;}
						else if(a==1){v=1; w=0;}
						else if(a==2){v=0; w=1;}
						else if(a==3){v=-1; w=0;}
						else if(a==4){v=0; w=-1;}
						if(botones[i+x][j+y].estHeuristica > botones[i+v][j+w].estHeuristica ){
							x=v;	y=w;
						}
					}
					botones[i][j].actRobot();
					botones[i+x][j+y].ponerRobot = true;
					botones[i+x][j+y].actRobot();
					return true;
				}
	
		}}
	return true;
	}
	
	private boolean robot(){
		for(int i = 0; i < filas; i ++){	
			for(int j = 0; j < columnas; j ++){
					if(botones[i][j].robot == true){
						ponerRobot = false;
						return true;
					}
				}}
		return false;
	}
	
	private boolean rehen(){
		for(int i = 0; i < filas; i ++){	
			for(int j = 0; j < columnas; j ++){
					if(botones[i][j].rehen == true){
						ponerRehen = false;
						return true;
					}
				}}
		return false;
	}

	
	public void actionPerformed(ActionEvent evento) {
		Casilla button = (Casilla) evento.getSource();
		if(ponerRobot==true && !robot()){
		button.ponerRobot = true;
		ponerRobot = false;
		}
		else if(ponerRehen==true && !rehen()){
			button.ponerRehen = true;
			ponerRehen = false;
			}
	}
}	
