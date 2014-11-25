import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.print.attribute.standard.RequestingUserName;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;



public class Matrix extends JPanel implements ActionListener{

	private JButton [][] botones;
	ImageIcon bomba = new ImageIcon("lava.jpg");

	int ancho;
	int alto;
	int filas, columnas;
	
	
	Matrix(int num_fil, int num_col)
	{
		setBounds(0,50,600,600);
		setVisible(true);
		filas = num_fil;
		columnas = num_col;
		ancho = this.getWidth();
		alto =  this.getHeight();

	
		//Parámetros GridLayout(num filas, num columnas, hgap(espacio horizontal), vgap(espacio vertical))
		//En este caso utilizamos el otro constructor para evitar espacios entre elementos.
		this.setLayout(new GridLayout(num_fil,num_col));
		
		
		botones = new JButton[filas][columnas];
		
		for(int i = 0; i < filas; i ++)
		{
			for(int j = 0; j < columnas; j ++)
			{
				//botones[i][j] = new JButton(new ImageIcon("stone.jpg"));
				//contentpane.add(botones[i][j]);
				botones[i][j] = new JButton();
				ImageIcon img = new ImageIcon("piedra.jpg");
				botones[i][j].setIcon(new ImageIcon(img.getImage().getScaledInstance(ancho/num_fil, alto/num_col,Image.SCALE_SMOOTH)));
				botones[i][j].addActionListener(this);
				this.add(botones[i][j]);
			}
		}
		
		paintComponents(getGraphics());
		
		
		//Cerramos el constructor
	}
	
	
		//Metodo para cambiar la imagen de los iconos.
		//Parametros: posicion fila, posicion columnas.
		public void actionPerformed(ActionEvent evento) {
			JButton button = (JButton) evento.getSource();
			button.setIcon(new ImageIcon(bomba.getImage().getScaledInstance(ancho/filas, alto/columnas,Image.SCALE_SMOOTH)));
		}
	
	
		
	

	public static void main(String[] args) {
		
		int filas = 0;
		int columnas = 0;
		int f_obs, c_obs = 0;
		Scanner in = new Scanner(System.in);
		
		Matrix ventana = new Matrix(filas, columnas);
		
	}

}	