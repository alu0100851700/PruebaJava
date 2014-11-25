import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;



public class Matrix extends JPanel implements ActionListener{

	private Casilla[][] botones;
	int ancho;
	int alto;
	int filas, columnas;
	
	
	Matrix(int num_fil, int num_col)
	{
		setBounds(0,0,700,700);
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
	
	
		//Metodo para cambiar la imagen de los iconos.
		//Parametros: posicion fila, posicion columnas.
	public void actionPerformed(ActionEvent evento) {
		Casilla button = (Casilla) evento.getSource();
		button.actualizar();	
	}

}	