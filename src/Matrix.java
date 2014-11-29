import java.awt.GridLayout;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;



public class Matrix extends JPanel implements ActionListener{

	private Casilla[][] botones;
	int ancho;
	int alto;
	int filas, columnas;
	
	int recorrido = 0;
	
	public boolean ponerRobot = false;
	public boolean ponerRehen = false;
	
	int[] rehenCoor;	//Coordenada en la que se encuentra el rehen
	
	// Para la posici�n del robot
	int [] robotPos = new int[2];
	///////////////
	
	static int MINIMO = 999999999;
	
	
	
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
		
		//Par�metros GridLayout(num filas, num columnas, hgap(espacio horizontal), vgap(espacio vertical))
		//En este caso utilizamos el otro constructor para evitar espacios entre elementos.
		this.setLayout(new GridLayout(num_fil,num_col));
		
		//Crea la Matriz de Casillas
		botones = new Casilla[filas][columnas];
		for(int i = 0; i < filas; i ++){	
			for(int j = 0; j < columnas; j ++){
				botones[i][j] = new Casilla(ancho/num_fil,alto/num_col);
				botones[i][j].addActionListener(this);
				botones[i][j].x = i;	// Posici�n X de cada casilla
				botones[i][j].y = j;	// Posici�n Y de cada casilla
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
						botones[i][j].estHeuristica = 0;
		}}}
		
		for(int i = 0; i < filas; i ++){	
			for(int j = 0; j < columnas; j ++){
					if(!botones[i][j].obstaculo == true && !botones[i][j].rehen == true){
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
						else if(a==1 && i!=filas-1){v=1; w=0;}
						else if(a==2 && j!=columnas-1){v=0; w=1;}
						else if(a==3 && i!=0){v=-1; w=0;}
						else if(a==4 && j!=0){v=0; w=-1;}
						if(botones[i+x][j+y].estHeuristica > botones[i+v][j+w].estHeuristica && botones[i+v][j+w].estHeuristica >= 0){
							x=v;	y=w;
							if(botones[i+v][j+w].estHeuristica == 0){
								return false;
							}
						}
					}
					botones[i][j].actRobot();
					botones[i][j].actVisitado();
					botones[i+x][j+y].ponerRobot = true;
					botones[i+x][j+y].actRobot();
					botones[i][j].estHeuristica = recorrido + botones[i][j].estHeuristica;
					recorrido++;
				}
	
		}}
	return true;
	}

	private boolean robot(){
		for(int i = 0; i < filas; i ++){	
			for(int j = 0; j < columnas; j ++){
					if(botones[i][j].robot == true){
						ponerRobot = false;
						
						// Pos robot
						robotPos[0] = i;
						robotPos[1] = j;
						///////////
						
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
	
	/*	ALGORITMO A* --->> Hasta ahora no funciona pero creo que 
	 * 					   no va mal encaminado
	 *
	 * 
	 * int hDeX()		// h*(x)
	 
	{
		int HX = recorrido + Math.abs(robotPos[0]-rehenCoor[0]) + Math.abs(robotPos[1]-rehenCoor[1]);
		return HX;
	}

	public boolean Aestrella()
	{
		int [][] lista_abierta;
		int [][] lista_cerrada;
		int posMinimo = 0;	// Para escoger el item de la lista abierta
		int numCandidatos = 0;	// Candidatos para la lista abierta
		int iterSolucion = 0; // Index para el camino que alberga la soluci�n en la lista cerrada
		boolean solucion = false;	// Cerrara el bucle en caso de encontrar la solucion
		
		* La primera de las filas ser� para la 
		 * coordenada X, la segunda para la 
		 * coordenada Y y la tercera para el valor
		 * acumulado en el recorrido. El segundo 
		 * valor, representar� la casilla, el bloque
		 * formado por lo anterior.
		 *
		lista_abierta = new int[3][filas*columnas];
		lista_cerrada = new int[3][filas*columnas];
		
		// Inicializaci�n:
		lista_abierta[0][numCandidatos] = robotPos[0];	// ahora como primer elemento
		lista_abierta[1][numCandidatos] = robotPos[1];	// situamos las cordenadas del robot(S)
		lista_abierta[2][numCandidatos] = 0;	// Coste inicial es 0.
		numCandidatos++;
		
		
		// La lista cerrada en una primera etapa se encuentra vac�a.
		// Fases
		while(!solucion)
		{
			// En la lista abierta meteremos todos aquellos candidatos
			// con su respectivo coste asociado
			
			//Casilla a la izquierda de la actual
			if ((( robotPos[1] - 1) >= 0) && ((robotPos[1] - 1) <= columnas))
			{
					lista_abierta[0][numCandidatos] = robotPos[0];
					lista_abierta[1][numCandidatos] = robotPos[1] - 1;
					// Faltar�a actualizar el tercer campo: Coste
					numCandidatos++;
			}
			//Casilla a la derecha dela actual
			if (((robotPos[1] + 1) >= 0) && ((robotPos[1] + 1) <= columnas))
			{
				lista_abierta[0][numCandidatos] = robotPos[0];	
				lista_abierta[1][numCandidatos] = robotPos[1] + 1;
				// Faltar�a actualizar el tercer campo: Coste
				numCandidatos++;
			}
			//Casilla superior a la actual
			if (((robotPos[0] - 1) >= 0) && ((robotPos[0] - 1) <= filas))
			{
					lista_abierta[0][numCandidatos] = robotPos[0] - 1;
					lista_abierta[1][numCandidatos] = robotPos[1];
					// Faltar�a actualizar el tercer campo: Coste
					numCandidatos++;
			}
			//Casilla inferior a la actual
			if (((robotPos[0] + 1) >= 0) && ((robotPos[0] + 1) <= filas))
			{
					lista_abierta[0][numCandidatos] = robotPos[0] + 1;
					lista_abierta[1][numCandidatos] = robotPos[1];
					// Faltar�a actualizar el tercer campo: Coste
					numCandidatos++;
			}
			
			// Retiramos y activamos la casilla como ya visitada
			botones[robotPos[0]][robotPos[1]].actRobot();
			botones[robotPos[0]][robotPos[1]].actVisitado();
			
			// Mientras haya elementos en la lista_abierta buscamos el menor
			while(lista_abierta.length-1 > 0)
			{
				for( int iter = 0; iter < lista_abierta.length; iter ++)
				{
					if(lista_abierta[3][iter] < MINIMO )
					{
						MINIMO = lista_abierta[3][iter];
						posMinimo = iter;
					}
				}
				lista_cerrada[0][iterSolucion] = lista_abierta[0][posMinimo];	// Guardamos el menor de los
				lista_cerrada[1][iterSolucion] = lista_abierta[1][posMinimo];	// valores que escogeremos 
				lista_cerrada[2][iterSolucion] = lista_abierta[2][posMinimo];	// como item en la lista_cerrada
				
				*Comprobamos que la posicion recien introducida en 
				 * la lista de cerrados no coincida con la solucion,
				 * en caso de hacerlo salimos del bucle( condicion
				 * de parada)
				 *
				// Realizamos el "movimiento" visualmente
				botones[lista_cerrada[0][iterSolucion]][lista_cerrada[1][iterSolucion]].ponerRobot = true;
				botones[lista_cerrada[0][iterSolucion]][lista_cerrada[1][iterSolucion]].actRobot();
				botones[robotPos[0]][robotPos[1]].estHeuristica = hDeX();
				recorrido++;
				if((lista_cerrada[0][iterSolucion] == rehenCoor[0]) 
					&& (lista_cerrada[1][iterSolucion] == rehenCoor[1]))
					{
						solucion = true;
					}
				iterSolucion++;
			}
		}
		// En caso de encontrar una soluci�n 
		// el metodo devolver� verdadero
		if(solucion)
			return true;
		return false;
	}*/
	/****** Repasar el bucle necesario despues de introducir un item en lista_cerrada, si no es la soluci�n deber�a
	 * 		seguir a partir de ese, eso FALTA********************************************************************/
	
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
