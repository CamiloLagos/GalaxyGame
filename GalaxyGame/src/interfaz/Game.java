package interfaz;

import javax.swing.*;

import hilos.HiloAlternarDisparoEnemigo;
import hilos.HiloDisparoEnemigo;
import hilos.HiloDisparoJugador;
import hilos.HiloMovimientoEnemigos;
import hilos.HiloMovimientoJugador;
import modelo.Juego;

import java.awt.*;

public class Game extends JFrame {

	private Juego juego;
	private PanelOpciones opciones;
	private HiloDisparoJugador hiloDisparoJugador;
	private HiloMovimientoJugador hiloMovimientoJugador;
	private HiloMovimientoEnemigos hiloMovimientoEnemigos;
	private HiloDisparoEnemigo hiloDisparoEnemigo;
	private HiloAlternarDisparoEnemigo hiloAlternarDisparoEnemigo;

	public Game() {

		setTitle("Galaxy Game");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(850, 850);
		setResizable(false);
		
		ImageIcon icon = new ImageIcon(Game.class.getClassLoader().getResource("iconos/icon2.jpg"));
		setIconImage(icon.getImage());

		juego = new Juego();

		hiloMovimientoJugador = new HiloMovimientoJugador(juego);
		hiloDisparoJugador = new HiloDisparoJugador(juego);
		hiloMovimientoEnemigos = new HiloMovimientoEnemigos(juego);
		hiloAlternarDisparoEnemigo = new HiloAlternarDisparoEnemigo(juego);
		hiloDisparoEnemigo = new HiloDisparoEnemigo(juego);

		opciones = new PanelOpciones(this);

		add(opciones);

		setLocationRelativeTo(null);

		setVisible(true);

	}

	public HiloMovimientoJugador getHiloMovimientoJugador() {
		return hiloMovimientoJugador;
	}

	public HiloAlternarDisparoEnemigo getHiloAlternarDisparoEnemigo() {
		return hiloAlternarDisparoEnemigo;
	}

	public HiloDisparoEnemigo getHiloDisparoEnemigo() {
		return hiloDisparoEnemigo;
	}

	public HiloMovimientoEnemigos getHiloMovimientoEnemigos() {
		return hiloMovimientoEnemigos;
	}

	public Juego getJuego() {

		return juego;

	}

	public HiloDisparoJugador getHiloDisparoJugador() {

		return hiloDisparoJugador;

	}

	public void refresh() {

		invalidate();
		revalidate();
		repaint();

	}

}
