package modelo;

public class Enemigo extends Objeto{
	
	private int tipo;

	public Enemigo(String imagen, int posx, int posy, int ancho, int altura, int velocidad, int tipo) {
		super(imagen, posx, posy, ancho, altura, velocidad);
		this.tipo = tipo;
	}
	



}