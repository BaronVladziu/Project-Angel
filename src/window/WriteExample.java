package window;

import player.Player;
import wavbuffer.WavBuffer;
import wavbuffer.WavWriter;
import wavfile.WavFile;

import java.io.File;

public class WriteExample
{

	public static void main(String[] args) {
		int sampleRate = 44100;		// Samples per second
		double duration = 5.0;		// Seconds
		String fileName = "testfile";

		long numFrames = (long)(duration * sampleRate);
		WavBuffer<Integer> wavBuffer = new WavBuffer<>();
		for (int i = 0; i < numFrames; i++) {
			wavBuffer.addSample((int)(Math.sin(2.0 * Math.PI * 400 * i / sampleRate) * (Short.MAX_VALUE*0.9)),
					(int)(Math.sin(2.0 * Math.PI * 500 * i / sampleRate) * (Short.MAX_VALUE*0.9)));
		}

		WavWriter wavWriter = new WavWriter(44100);
		wavWriter.writeFile(wavBuffer, fileName);

		Player.play(fileName + ".wav");
	}

}
