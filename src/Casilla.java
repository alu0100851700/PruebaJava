import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Casilla extends JButton {
	
	ImageIcon bomba = new ImageIcon("lava.jpg");
	ImageIcon img = new ImageIcon("piedra.jpg");
	ImageIcon stone = new ImageIcon("stone.jpg");
	private int a=0;
	private int ancho, alto;
	/**
	 * Create the Button.
	 */
	
	public Casilla(int anch, int alt) {
		ancho = anch;	alto = alt;
		this.setIcon(new ImageIcon(img.getImage().getScaledInstance(ancho, alto,Image.SCALE_SMOOTH)));
	}

	public void actualizar(){
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
	}
		//Metodo para cambiar la imagen de los iconos.
		//Parametros: posicion fila, posicion columnas.


}
