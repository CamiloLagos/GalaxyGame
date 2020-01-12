package interfaz;

import javax.swing.*;

import hilos.HiloAbstract;
import hilos.HiloAlternarDisparoEnemigo;
import hilos.HiloDesplegarEnemigos;
import hilos.HiloDisparoEnemigo;
import hilos.HiloDisparoJugador;
import hilos.HiloMovimientoEnemigos;
import hilos.HiloMovimientoJugador;
import hilos.HiloRevivirJugador;
import modelo.Explosion;
import modelo.Juego;
import tools.GameManager;


import java.awt.*;
import java.util.LinkedList;

public class Game extends JFrame {

	private Juego juego;
	private PanelOpciones opciones;
	private LinkedList<HiloAbstract> threads;

	public Game() {

		GameManager.loadResources();
		
		setTitle("Galaxy Game");
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(GameManager.WIDTH, GameManager.HEIGHT);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setIconImage(GameManager.imagenes.get("icono"));

		threads = new LinkedList<HiloAbstract>();

		juego = new Juego();

		crearProcesos(juego);

		opciones = new PanelOpciones(this);

		setContentPane(opciones);

		setLocationRelativeTo(null);

		// Cargar recurso de explosion.
		getJuego().getExplosiones().add(new Explosion(0, 0, juego));
		getJuego().getExplosiones().getLast().start();

	}

	public void crearProcesos(Juego juego) {

		threads.add(new HiloMovimientoJugador(juego));
		threads.add(new HiloDisparoJugador(juego));
		threads.add(new HiloRevivirJugador(juego));
		threads.add(new HiloMovimientoEnemigos(juego));
		threads.add(new HiloAlternarDisparoEnemigo(juego));
		threads.add(new HiloDisparoEnemigo(juego));
		threads.add(new HiloDesplegarEnemigos(juego, 10));

	}

	public LinkedList<HiloAbstract> getThreads() {
		return threads;
	}

	public void setThreads(LinkedList<HiloAbstract> threads) {
		this.threads = threads;
	}

	public Juego getJuego() {

		return juego;

	}

	public void refresh() {

		invalidate();
		revalidate();
		repaint();

	}

}
