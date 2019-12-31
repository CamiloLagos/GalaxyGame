package tools;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer {

	public static void play(File file) {

		try {

			Clip sound = AudioSystem.getClip();
			sound.open(AudioSystem.getAudioInputStream(file));
			sound.start();

			new Thread() {

				@Override
				public void run() {

					try {
						Thread.sleep(sound.getMicrosecondLength() / 1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			};

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}

}