package tools;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class ScreenResolution {

	// Screen
	public static final int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static final int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final int HEIGHT_GAME = WIDTH / 2;
	public static final int WIDTH_GAME = HEIGHT_GAME;

	// Resources
	// Here all measures are calculated with a 1920x1080 display resolution as a
	// reference.
	public static final int SCALE_PLAYER = 324; // (1920*1080)/(80*80). 80 is the width and the height of the original
												// spaceship in 1920*1080.
	public static final int WIDTH_PLAYER = (int) Math.sqrt((HEIGHT * WIDTH) / SCALE_PLAYER);
	public static final int HEIGHT_PLAYER = WIDTH_PLAYER;

	public static final int SCALE_WIDTH_SHOT = 1920 / 30;
	public static final int SCALE_HEIGHT_SHOT = 1080 / 70;
	public static final int WIDTH_SHOT = WIDTH / SCALE_WIDTH_SHOT;
	public static final int HEIGHT_SHOT = HEIGHT / SCALE_HEIGHT_SHOT;

	private BufferedImage imageToResize;

	public ScreenResolution() {
		
		createResizeResources();

	}

	public void createResizeResources() {

		File file = new File("./resources");
		String s = "";
		System.out.println(Arrays.toString(file.listFiles()));
		File[] folders = file.listFiles();

		for (int i = 0; i < folders.length; i++) {

			File[] subFolder = folders[i].listFiles();

			for (int j = 0; j < subFolder.length; j++) {

				String[] temp = subFolder[j].toString().split("\\\\");

				System.out.println(subFolder[j].toString());

				System.out.println(Arrays.toString(temp));
				s = "";
				for (int k = 0; k < temp.length; k++) {

					if (k == temp.length - 1) {

						s +="R"+temp[k];

					} else {

						s += temp[k] + "/";
					}

				}

				System.out.println(s);

			}

		}

	}
	
	public static void main(String[] args) {
		
		ScreenResolution s = new ScreenResolution();
		
	}

}