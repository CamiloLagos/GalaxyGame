package modelo;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import tools.GameManager;

/**
 * Representa al jugador del juego.
 * 
 * @author juanchovelezpro
 *
 */
public class Jugador extends GameObject {

	/**
	 * La altura del {@code Jugador}.
	 * <p>
	 * Valor: {@value #HEIGHT}
	 * </p>
	 */
	public static final int HEIGHT = 80;

	/**
	 * El ancho del {@code Jugador}.
	 * 
	 * <p>
	 * Valor: {@value #WIDTH}
	 * </p>
	 */
	public static final int WIDTH = 80;

	/**
	 * Limite horizontal derecho para evitar que el {@code Jugador} se salga de la
	 * vision del juego.
	 */
	public static final int X_RIGHT_LIMIT = GameManager.WIDTH_GAME - WIDTH;

	/**
	 * Limite horizontal izquierod para evitar que el {@code Jugador} se salga de la
	 * vision del juego.
	 * <p>
	 * Valor: {@value #X_LEFT_LIMIT}
	 * </p>
	 */
	public static final int X_LEFT_LIMIT = 0;

	/**
	 * Limite superior al cual un disparo del {@code Jugador} puede llegar.
	 * <p>
	 * Valor: {@value #SHOT_LIMIT}
	 * </p>
	 */
	public static final int SHOT_LIMIT = -70;

	/**
	 * Coordenada x en la que un disparo del {@code Jugador} se produce.
	 * <p>
	 * Valor: {@value #SHOT_OFFSET_X}
	 * </p>
	 */
	public static final int SHOT_OFFSET_X = 25;

	/**
	 * Coordenada y en la que un disparo del {@code Jugador} se produce.
	 * <p>
	 * Valor: {@value #SHOT_OFFSET_Y}
	 * </p>
	 */
	public static final int SHOT_OFFSET_Y = -35;

	/**
	 * Coordenada x en la que aparece el {@code Jugador}.
	 */
	public static final int SPAWN_X = GameManager.WIDTH_GAME / 2 - Jugador.WIDTH / 2;

	/**
	 * Coordenada y en la que aparece el {@code Jugador}.
	 */
	public static final int SPAWN_Y = GameManager.HEIGHT_GAME - Jugador.HEIGHT;

	/**
	 * Coordenada x a la que se traslada el {@code Jugador} cuando "muere".
	 * <p>
	 * Valor: {@value #X_DEATH}
	 * </p>
	 */
	public static final int X_DEATH = -100;

	/**
	 * El nick del {@code Jugador}.
	 */
	private String nick;

	/**
	 * El puntaje del {@code Jugador}.
	 */
	private long puntaje;

	/**
	 * Una lista de disparos del {@code Jugador}.
	 */
	private LinkedList<Disparo> disparos;

	/**
	 * El juego al que pertenece el {@code Jugador}.
	 */
	private Juego juego;

	/**
	 * La cantidad de vidas que posee el {@code Jugador}.
	 */
	private int vidas;

	/**
	 * La vida del {@code Jugador}.
	 */
	private int vida;

	/**
	 * La cantidad de {@link #vida} del {@code Jugador}.
	 */
	private int salud;

	/**
	 * El da�o que posee el {@code Jugador}.
	 */
	private int damage;

	/**
	 * Permite verificar si el jugador esta vivo. Si es {@code true} entonces el
	 * jugador esta vivo, {@code false} en caso contrario.
	 */
	private boolean vivo;

	/**
	 * Permite verificar si el jugador ha disparado anteriormente. Se usa con el fin
	 * de no permitirle al {@code Jugador} disparar continuamente manteniendo
	 * presionada la tecla de disparo. Si es {@code true} ha disparado,
	 * {@code false} en caso contrario.
	 */
	private boolean recargaDisparo;

	/**
	 * Permite verificar si el jugador es invulnerable al da�o de un
	 * {@code Enemigo}.
	 */
	private boolean invulnerable;

	/**
	 * La lista de potenciadores del {@code Jugador}.
	 */
	private LinkedList<Potenciador> powerUps;

	/**
	 * Constructor que crea un {@code Jugador}. Todas los demas parametros del
	 * {@code Jugador} son diferentes de {@code null}
	 * 
	 * @param juego El {@code Juego} al que pertenece el {@code Jugador}.
	 */
	public Jugador(Juego juego) {

		super(SPAWN_X, SPAWN_Y, juego);
		setSkin(GameManager.imagenes.get("nave1"));
		setAltura(HEIGHT);
		setAncho(WIDTH);

		disparos = new LinkedList<>();
		powerUps = new LinkedList<>();

		nick = "";
		puntaje = 0;
		vidas = 3;
		vida = 100;
		salud = vida;
		damage = 2;
		vivo = true;
		recargaDisparo = false;
		invulnerable = false;

		this.juego = juego;

	}

	/**
	 * Retorna si el {@code Jugador} es invulnerable al da�o de un {@code Enemigo}
	 * 
	 * @return {@code true} si el {@code Jugador} es invulnerable, {@code false} en
	 *         caso contrario.
	 */
	public boolean isInvulnerable() {
		return invulnerable;
	}

	/**
	 * Modifica la invulnerabilidad al da�o del {@code Jugador}
	 * 
	 * @param invulnerable La nueva invulnerabilidad del {@code Jugador},
	 *                     {@code true} es invulnerable, {@code false} es no
	 *                     invulnerable.
	 */
	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
	}

	/**
	 * Retorna si el juego debe recargar el disparo. (Ha disparado anteriormente y
	 * no ha soltado la tecla de disparar).
	 * 
	 * @return {@code true} si el jugador debe recargar el disparo, {@code false},
	 *         en caso contrario.
	 */
	public boolean isRecargaDisparo() {
		return recargaDisparo;
	}

	/**
	 * Modifica la recarga del disparo del {@code Jugador}
	 * 
	 * @param recargaDisparo La nueva recarga de disparo, {@code true}, debe
	 *                       recargar, {@code false}, no debe recargar.
	 */
	public void setRecargaDisparo(boolean recargaDisparo) {
		this.recargaDisparo = recargaDisparo;
	}

	/**
	 * Retorna si el {@code Jugador} se encuentra vivo.
	 * 
	 * @return {@code true} si el {@code Jugador} se encuentra vivo, {@code false}
	 *         en caso contrario.
	 */
	public boolean isVivo() {
		return vivo;
	}

	/**
	 * Modifica la condicion de estar vivo del {@code Jugador}.
	 * 
	 * @param vivo La nueva condicion del {@code Jugador}, {@code true} estar vivo,
	 *             {@code false} no estar vivo.
	 */
	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	/**
	 * Retorna la cantidad de vidas que tiene el {@code Jugador}
	 * 
	 * @return La cantidad de vidas que tiene el {@code Jugador}.
	 */
	public int getVidas() {
		return vidas;
	}

	/**
	 * Modifica la cantidad de vidas del {@code Jugador}.
	 * 
	 * @param vidas La nueva cantidad de vidas del {@code Jugador}.
	 */
	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	/**
	 * Retorna la vida del {@code Jugador}.
	 * 
	 * @return La vida del {@code Jugador}.
	 */
	public int getVida() {
		return vida;
	}

	/**
	 * Modifica la vida del {@code Jugador}.
	 * 
	 * @param vida La nueva vida del {@code Jugador}.
	 */
	public void setVida(int vida) {
		this.vida = vida;
	}

	/**
	 * Retorna la cantidad de vida del {@code Jugador}.
	 * 
	 * @return La cantidad de vida del {@code Jugador}.
	 */
	public int getSalud() {
		return salud;
	}

	/**
	 * Modifica la cantidad de vida del {@code Jugador}.
	 * 
	 * @param salud La nueva cantidad del {@code Jugador}.
	 */
	public void setSalud(int salud) {
		this.salud = salud;
	}

	/**
	 * Retorna el da�o que posee el {@code Jugador}.
	 * 
	 * @return El da�o que posee el {@code Jugador}.
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * Modifica el da�o del {@code Jugador}.
	 * 
	 * @param damage El nuevo da�o del {@code Jugador}.
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * Retorna el nick del {@code Jugador}.
	 * 
	 * @return El nick del {@code Jugador}.
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * Modifica el nick del {@code Jugador}.
	 * 
	 * @param nick El nuevo nick del {@code Jugador}.
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * Retorna el puntaje del {@code Jugador}.
	 * 
	 * @return El puntaje del {@code Jugador}.
	 */
	public long getPuntaje() {
		return puntaje;
	}

	/**
	 * Modifica el puntaje del {@code Jugador}.
	 * 
	 * @param puntaje El nuevo puntaje del {@code Jugador}.
	 */
	public void setPuntaje(long puntaje) {
		this.puntaje = puntaje;
	}

	/**
	 * Se encarga de actualizar las coordenadas (x,y) del {@code Jugador} para su
	 * movimiento. Para realizar el movimiento se usa el {@code Thread}
	 * {@link HiloMovimientoJugador} que se encarga de realizar constantemente la
	 * actualizacion de las coordenadas (x,y) del {@code Jugador}.
	 */
	public void mover() {

		if (salud <= 0) {

			morir();

		}

		if (vivo) {
			if (super.getX() >= X_RIGHT_LIMIT) {
				super.setX(X_RIGHT_LIMIT);
			} else if (super.getX() <= X_LEFT_LIMIT) {
				super.setX(X_LEFT_LIMIT);
			}

			super.setX(super.getX() + super.getVelX());
		}
	}

	/**
	 * Se encarga de revivir al {@code Jugador} en las coordenadas (x,y) spawn.
	 * 
	 * @see #SPAWN_X
	 * @see #SPAWN_Y
	 * @see #setVivo(boolean)
	 */
	public void revivir() {

		setX(SPAWN_X);
		setY(SPAWN_Y);
		setSalud(vida);
		setVivo(true);

	}

	/**
	 * Se encarga de hacer "morir" al {@code Jugador} trasladandolo a la coordenada
	 * {@code X_DEATH}.
	 * <p>
	 * Le establece la velocidad en el eje X a -> 0 y le quita una vida al
	 * {@code Jugador}.
	 * </p>
	 * 
	 * @see #X_DEATH
	 * @see #setVivo(boolean)
	 * @see #setVidas(int)
	 */
	public void morir() {

		setX(X_DEATH);
		setVelX(0);
		setVivo(false);
		setVidas(vidas - 1);

	}

	/**
	 * Retorna la lista de disparos del {@code Jugador}.
	 * 
	 * @return La lista de disparos del {@code Jugador}
	 * @see #disparos
	 * @see Disparo
	 */
	public LinkedList<Disparo> getDisparos() {
		return disparos;
	}

	/**
	 * Modifica la lista de disparos del {@code Jugador}.
	 * 
	 * @param disparos La nueva lista de disparos del {@code Jugador}.
	 */
	public void setDisparos(LinkedList<Disparo> disparos) {
		this.disparos = disparos;
	}

	/**
	 * Retorna la lista de potenciadores del {@code Jugador}.
	 * 
	 * @return La lista de potenciadores del {@code Jugador}.
	 */
	public LinkedList<Potenciador> getPowerUps() {
		return powerUps;
	}

	/**
	 * Modifica la lista de potenciadores del {@code Jugador}.
	 * 
	 * @param powerUps La nueva lista de potenciadores del {@code Jugador}.
	 */
	public void setPowerUps(LinkedList<Potenciador> powerUps) {
		this.powerUps = powerUps;
	}

	/**
	 * Agrega un {@code Potenciador} a la lista de potenciadores del
	 * {@code Jugador}.
	 * 
	 * @param powerUp El {@code Potenciador} a agregar.
	 */
	public void agregarPowerUp(Potenciador powerUp) {

		powerUps.add(powerUp);

	}

	/**
	 * Elimina un {@code Potenciador} de la lista de potenciadores del
	 * {@code Jugador}.
	 * 
	 * @param powerUp El {@code Potenciador} a eliminar.
	 */
	public void eliminarPowerUp(Potenciador powerUp) {

		powerUps.add(powerUp);

	}

	/**
	 * Agrega un {@code Disparo} a la lista de disparos del {@code Jugador}.
	 * 
	 * @see #disparos
	 */
	public void agregarDisparo() {
		disparos.add(new Disparo(4, super.getX() + SHOT_OFFSET_X, super.getY() + SHOT_OFFSET_Y, 0, 15, juego));
	}

	/**
	 * Elimina un {@code Disparo} de la lista de disparos del {@code Jugador}.
	 * <p>
	 * Con el fin de optimizar el rendimiento del juego, aquellos disparos que
	 * superen el limite de la vision del jugador se eliminan.
	 * </p>
	 * 
	 * @param d El {@code Disparo} a eliminar.
	 * @see #SHOT_LIMIT
	 */
	public void eliminarDisparo(Disparo d) {
		disparos.remove(d);
	}

	/**
	 * Se encarga de efectuar las acciones de cada uno de los disparos del
	 * {@code Jugador}.
	 * 
	 * <p>
	 * Cada vez que se agregue un disparo a la lista de disparos del {@code Jugador}
	 * es porque el {@code Jugador} ha disparado, entonces este metodo se encarga de
	 * cada uno de los disparos que se encuentren en la lista se muevan.
	 * </p>
	 * 
	 * <p>
	 * Este metodo se llama constantemente en el {@code Thread}
	 * {@link HiloDisparoJugador} de forma que las acciones de un {@code Disparo} se
	 * actualicen, tales como colisiones, movimiento y limites.
	 * </p>
	 */
	public void disparar() {

		Disparo disparoTemporal = null;

		for (int i = 0; i < disparos.size(); i++) {
			disparoTemporal = disparos.get(i);

			disparoTemporal.avanzarDisparo();

			if (Fisica.colision(disparoTemporal, juego.getEnemigos(), juego)) {

				juego.getExplosiones().add(new Explosion(disparoTemporal.getX(), disparoTemporal.getY(), juego));
				juego.getExplosiones().getLast().start();

				eliminarDisparo(disparoTemporal);

			}

			if (disparoTemporal.getY() < SHOT_LIMIT)
				eliminarDisparo(disparoTemporal);

		}
	}

	@Override
	public void render(Graphics g) {

		g.drawImage(getSkin(), getX(), getY(), null);

		if (GameManager.TEST) {

			GameManager.renderBounds(g, this);
		}
		for (int i = 0; i < disparos.size(); i++) {

			Disparo temp = disparos.get(i);

			temp.render(g);

		}

	}

	@Override
	public Rectangle getVision() {

		return null;
	}

}
