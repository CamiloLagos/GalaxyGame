package modelo;

public class Jugador extends Objeto {
	private String nick;
	private long puntaje;
	public Jugador(String imagen, int posx, int posy, int ancho, int altura,int velocidad, String nick, long puntaje) {
		super(imagen, posx, posy, ancho, altura,velocidad);
		this.nick = nick;
		this.puntaje = puntaje;
		
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public long getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(long puntaje) {
		this.puntaje = puntaje;
	}
	public void irDerecha() {
		super.setPosx(super.getPosx()+ super.getVelocidad());
	}
	public void irIzquierda() {
		super.setPosx(super.getPosx()- super.getVelocidad());
	}
	
}
