package window;

import player.Player;
import synthesizer.*;
import vocoder.IVocoder;
import vocoder.PulseFilteredVocoder;
import vocoder.Sound;
import wavbuffer.WavBuffer;
import wavbuffer.WavWriter;

public class WriteExample
{

	public static void main(String[] args) {
		int sampleRate = 44100;		// Samples per second
		double duration = 0.5;		// Seconds
		String fileName = "testfile";

		long numFrames = (long)(duration * sampleRate);

		IVocoder vocoder = new PulseFilteredVocoder(sampleRate);
		WavBuffer<Double> wavBuffer = new WavBuffer<>();

		for (Note note : new Note[]{
				new Note(NoteName.G, 1), new Note(NoteName.E, 1), new Note(NoteName.E, 1),
				new Note(NoteName.F, 1), new Note(NoteName.D, 1), new Note(NoteName.D, 1),
				new Note(NoteName.C, 1), new Note(NoteName.E, 1), new Note(NoteName.G, 1)}) {
			vocoder.start(note.getFrequency(), Sound.A);
			System.out.println(note);
			for (int i = 0; i < numFrames; i++) {
				double sample = vocoder.getNextSample();
				wavBuffer.addSample(sample, sample);
			}
		}
		vocoder.start(0, Sound.A);
		for (int i = 0; i < numFrames; i++) {
			double sample = vocoder.getNextSample();
			wavBuffer.addSample(sample, sample);
		}

		WavWriter wavWriter = new WavWriter(sampleRate);
		wavWriter.writeFile(wavBuffer, fileName);

		Player.play(fileName + ".wav");
	}

}
