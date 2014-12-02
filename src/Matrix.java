import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Vector;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Matrix extends JPanel implements ActionListener{

	private Casilla[][] botones;
	int ancho;
	int alto;
	int filas, columnas;
	
	
	public boolean ponerRobot = false;
	public boolean ponerRehen = false;
	
	int[] rehenCoor;	//Coordenada en la que se encuentra el rehen
	
	// Para la posici�n del robot
	int [] robotPos = new int[2];
	///////////////
	
	public int INFINITO = -1;
	int [][] lista_abierta;
	int [][] lista_cerrada;
	int posMinimo = 0;	// Para escoger el item de la lista abierta
	int numCandidatos = 0;	// Candidatos para la lista abierta
	int iterSolucion = 0; // Index para el camino que alberga la soluci�n en la lista cerrada
	boolean solucion = false;	// Cerrara el bucle en caso de encontrar la solucion
	
	boolean noSolucion = false;
	boolean [][] Mejora;
	
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
		
		//lista_abierta = new int[3][filas*columnas];
		//lista_cerrada = new int[3][filas*columnas];
		
		Mejora = new boolean[filas][columnas];
		lista_abierta = new int[3][9999999];
		lista_cerrada = new int[3][9999999];
		
		//Par�metros GridLayout(num filas, num columnas, hgap(espacio horizontal), vgap(espacio vertical))
		//En este caso utilizamos el otro constructor para evitar espacios entre elementos.
		this.setLayout(new GridLayout(num_fil,num_col));
		
		/*this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.RELATIVE;
		*/
		
		//TAMA�O.setSize(10,10);
		//Crea la Matriz de Casillas
		botones = new Casilla[filas][columnas];
		for(int i = 0; i < filas; i ++){	
			for(int j = 0; j < columnas; j ++){
				botones[i][j] = new Casilla(ancho/num_fil,alto/num_col);
				botones[i][j].addActionListener(this);
				botones[i][j].x = i;	// Posici�n X de cada casilla
				botones[i][j].y = j;	// Posici�n Y de cada casilla
				this.add(botones[i][j]);
				//this.add(botones[i][j], c);
				}}
		
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
		for(int i=0; i < filas; i++)
			for(int j=0; j<columnas; j++)
				Mejora[i][j] = false;
		// La primera de las filas ser� para la 
		//coordenada X, la segunda para la 
		// coordenada Y y la tercera para el valor
		// acumulado en el recorrido. El segundo 
		// valor, representar� la casilla, el bloque
		// formado por lo anterior.
		 
		for(int i=0; i < lista_abierta[2].length-1; i++)
		{
			lista_abierta[2][i] = INFINITO;
		}
		
		for(int i = 0; i < filas; i ++){	
			for(int j = 0; j < columnas; j ++){
					if(botones[i][j].rehen == true){
						rehenCoor[0] = i;
						rehenCoor[1] = j;
						botones[i][j].estHeuristica = 0;
		}}}
		
		for(int i = 0; i < filas; i ++){	
			for(int j = 0; j < columnas; j ++){
					if(!botones[i][j].obstaculo && !botones[i][j].rehen){
						//botones[i][j].estHeuristica = Math.abs(i-rehenCoor[0]) + Math.abs(j-rehenCoor[1]); 
						botones[i][j].estHeuristica = Math.abs(i-rehenCoor[0]) + Math.abs(j-rehenCoor[1]) + Math.abs(Math.abs(i-rehenCoor[0]) - Math.abs(j-rehenCoor[1]));
		}}}

		for(int i = 0; i < filas; i ++){	
			for(int j = 0; j < columnas; j ++){
					if(botones[i][j].robot == true){
						robotPos[0] = i;
						robotPos[1] = j;
					}
				}}
		
		// Inicializaci�n:
				lista_abierta[0][numCandidatos] = robotPos[0];	// ahora como primer elemento
				lista_abierta[1][numCandidatos] = robotPos[1];	// situamos las cordenadas del robot(S)
				lista_abierta[2][numCandidatos] = hDeX(robotPos[0],robotPos[1]);	// Coste inicial de la casilla del robot.
				numCandidatos = 1;
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

	/*	ALGORITMO A* --->> Hasta ahora no funciona pero creo que 
	 					   no va mal encaminado
	 */
	
	// Funcion que devolver� la estima heur�stica
	int hDeX(int i, int j)		// h*(x)
	 
	{
		int HX = botones[i][j].estHeuristica;
		return HX;
	}

	
	
	
	public boolean Aestrella()
	{
		// La lista cerrada en una primera etapa se encuentra vac�a.
		// Fases
			// En la lista abierta meteremos todos aquellos candidatos
			// con su respectivo coste asociado
			Mejora[robotPos[0]][robotPos[1]] = false;
			//Casilla a la izquierda de la actual
			if ((( robotPos[1] - 1) >= 0) && ((robotPos[1] - 1) < columnas)
					&& !botones[robotPos[0]][robotPos[1] - 1].obstaculo)
			{
					lista_abierta[0][numCandidatos] = robotPos[0];
					lista_abierta[1][numCandidatos] = robotPos[1] - 1;
					lista_abierta[2][numCandidatos] = hDeX(robotPos[0],robotPos[1] - 1);
					numCandidatos++;
					if(botones[robotPos[0]][robotPos[1] - 1].visitado == true)
						Mejora[robotPos[0]][robotPos[1]] = Mejora[robotPos[0]][robotPos[1]] || false;
					else
						Mejora[robotPos[0]][robotPos[1]] = true;
			}
			//Casilla a la derecha dela actual
			if (((robotPos[1] + 1) >= 0) && ((robotPos[1] + 1) < columnas)
					&& !botones[robotPos[0]][robotPos[1] + 1].obstaculo)
			{
					lista_abierta[0][numCandidatos] = robotPos[0];	
					lista_abierta[1][numCandidatos] = robotPos[1] + 1;
					lista_abierta[2][numCandidatos] = hDeX(robotPos[0],robotPos[1] + 1);
					numCandidatos++;
					if(botones[robotPos[0]][robotPos[1] + 1].visitado == true)
						Mejora[robotPos[0]][robotPos[1]] = Mejora[robotPos[0]][robotPos[1]] || false;
					else
						Mejora[robotPos[0]][robotPos[1] + 1] = true;
			}
			//Casilla superior a la actual
			if (((robotPos[0] - 1) >= 0) && ((robotPos[0] - 1) < filas)
					&& !botones[robotPos[0] - 1][robotPos[1]].obstaculo)
			{
					lista_abierta[0][numCandidatos] = robotPos[0] - 1;
					lista_abierta[1][numCandidatos] = robotPos[1];
					lista_abierta[2][numCandidatos] = hDeX(robotPos[0] - 1,robotPos[1]);
					numCandidatos++;
					if(botones[robotPos[0] - 1][robotPos[1]].visitado == true)
						Mejora[robotPos[0]][robotPos[1]] = Mejora[robotPos[0]][robotPos[1]] || false;
					else
						Mejora[robotPos[0] - 1][robotPos[1]] = true;
			}
			//Casilla inferior a la actual
			if (((robotPos[0] + 1) >= 0) && ((robotPos[0] + 1) < filas)
					&& !botones[robotPos[0] + 1][robotPos[1]].obstaculo)
			{
					lista_abierta[0][numCandidatos] = robotPos[0] + 1;
					lista_abierta[1][numCandidatos] = robotPos[1];
					lista_abierta[2][numCandidatos] = hDeX(robotPos[0] + 1,robotPos[1]);
					numCandidatos++;
					if(botones[robotPos[0]][robotPos[1]].visitado == true)
						Mejora[robotPos[0]][robotPos[1]] = Mejora[robotPos[0]][robotPos[1]] || false;
					else
						Mejora[robotPos[0]][robotPos[1]] = true;
			}
			noSolucion = true;
			for(int i=0; i<filas; i++)
				for( int j=0; j<columnas; j++)
						if(Mejora[i][j])	noSolucion = false;
			
			int min = 99999999;
			// Mientras no hayamos comprobado todos los elementos en la lista_abierta buscamos el menor
			for( int iter = 0; iter < numCandidatos; iter ++)
			{
				if(lista_abierta[2][iter] <= min && lista_abierta[2][iter] > INFINITO &&
						!botones[lista_abierta[0][iter]][lista_abierta[1][iter]].robot)	
				{
					min = lista_abierta[2][iter];
					posMinimo = iter;	// Sacamos el elemento de menor peso
				}
			}
			numCandidatos = 0;
			
			lista_cerrada[0][iterSolucion] = lista_abierta[0][posMinimo];	// Guardamos el menor de los
			lista_cerrada[1][iterSolucion] = lista_abierta[1][posMinimo];	// valores que escogeremos 
			lista_cerrada[2][iterSolucion] = lista_abierta[2][posMinimo];	// como item en la lista_cerrada
			
			lista_abierta[2][posMinimo] = 999999999;
			
			
			//Comprobamos que la posicion recien introducida en 
			// la lista de cerrados no coincida con la solucion,
			// en caso de hacerlo salimos del bucle( condicion
			// de parada)
			// Poner casillas adyacentes en rehenCoor
			if((lista_cerrada[0][iterSolucion] == rehenCoor[0]) 
				&& (lista_cerrada[1][iterSolucion] == rehenCoor[1]) || noSolucion)
				{
				if(noSolucion)
				{
					JFrame error = new JFrame("ERROR");
					JLabel text_error = new JLabel();
					JPanel pane = new JPanel();
					text_error.setForeground(Color.red);
					pane.add(text_error);
					pane.setBackground(Color.LIGHT_GRAY);
					text_error.setText("No existe camino posible");
					error.add(pane);
					
					error.pack();
					pane.setBounds(100, 100, 80, 30);
					error.setLocationRelativeTo(null);
					
					error.setVisible(true);
					repaint();
				}
					return false;
				}
			else{
			
				
				
			// Retiramos y activamos la casilla como ya visitada
			botones[robotPos[0]][robotPos[1]].actRobot();
			botones[robotPos[0]][robotPos[1]].actVisitado();
			botones[robotPos[0]][robotPos[1]].numVisitas++;
			
			botones[robotPos[0]][robotPos[1]].estHeuristica = 1 + hDeX(lista_cerrada[0][iterSolucion],lista_cerrada[1][iterSolucion]);
			// Realizamos el "movimiento" visualmente
			botones[lista_cerrada[0][iterSolucion]][lista_cerrada[1][iterSolucion]].ponerRobot = true;
			botones[lista_cerrada[0][iterSolucion]][lista_cerrada[1][iterSolucion]].actRobot();
			robotPos[0] = lista_cerrada[0][iterSolucion];
			robotPos[1] = lista_cerrada[1][iterSolucion];
			iterSolucion++;
			}
				

		// En caso de encontrar una soluci�n 
		// el metodo devolver� verdadero
		return true;
	}
	
	/** Funcion void que situara los obstaculos en el caso de escoger colocacion random **/
	public void funcionRandom(int saturacion)
	{
		// Inclu�as la llamada a reset(), pero creo que no es necesaria
		// por si al user se le da por poner el robot y reh�n primero
		// cosa que veo bastante l�gica.
		int x,y;
		int numObstaculos = (int) (saturacion*0.01*filas*columnas);
		while(numObstaculos > 0)
		{
			x = (int)(Math.random()*filas);
			y = (int)(Math.random()*columnas);
			if(!botones[x][y].obstaculo && !botones[x][y].robot 
					&& !botones[x][y].rehen){
				botones[x][y].actObstaculo();
				numObstaculos--;
			}
			else
				if(botones[x][y].obstaculo && botones[x][y].robot 
					&& botones[x][y].rehen)
				{
					numObstaculos++;
				}
		}
	}
	
	
	void reset_listaAbierta()
	{
		for(int i=0; i < lista_abierta.length; i++){
			lista_abierta[0][i] = -1;
			lista_abierta[1][i] = -1;
			lista_abierta[2][i] = -1;
		}
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
	
	//int max = 999999999;
}	
