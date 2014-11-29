/*import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;


public class Aestrella {

	Casilla [] lista_abierta;
	Casilla [] lista_cerrada;

	public Aestrella()
	{
		// Contendrá la posición del menor de la lista
		int minimo_abierta = 1000000;
		
		// Etapa de inicializacion
		lista_abierta.add(Matrix.this.robotPos);
		 
		lista_cerrada = null;
		
		// Para escoger el menor de la lista
		for ( int abierta = 0; abierta < lista_abierta.size(); abierta++)
		{
			if( lista_abierta.elementAt(abierta))
			{
				minimo_abierta = lista_abierta.get(abierta)[3];
			}
		}
		
		for(int i = 0; i < filas; i ++){	
			for(int j = 0; j < columnas; j ++)
			{
				if(botones[i][j].robot)
				{
					coordX = i;
					coordY = j;
				}
			}
		/////////////////////////////////////////
		
			for( int x = 0; x < filas; x ++){	
				for(int y = 0; y < columnas; y ++)
				{
					if(botones[coordX+1][coordY].estHeuristica >))
					{
						coordX = i;
						coordY = j;
					}
				}
			}
		}
	}
*/