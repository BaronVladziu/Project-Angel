package wavbuffer;

import wavfile.WavFile;
import wavfile.WavFileException;

import java.io.File;
import java.io.IOException;

public class WavWriter {

    private int sampleRate;

    public WavWriter(int sampleRate) {
        this.sampleRate = sampleRate;
    }

    public void writeFile(WavBuffer<Double> wavBuffer, String fileName) {
        try {
            WavFile wavFile = WavFile.newWavFile(new File(fileName + ".wav"), 2,
                    wavBuffer.size(), 16, sampleRate);

            double[][] buffer = new double[2][10000];

            while (wavBuffer.size() > 0) {
                long remaining = wavFile.getFramesRemaining();
                long toWrite = (remaining > 10000) ? 10000 : remaining;
                for (int s = 0 ; s < toWrite ; s++) {
                    StereoSample<Double> stereoSample = wavBuffer.popSample();
                    buffer[0][s] = stereoSample.left;
                    buffer[1][s] = stereoSample.right;
//                    System.out.println(buffer[0][s] + " , " + buffer[1][s]);
                }
                wavFile.writeFrames(buffer, (int)toWrite);
            }

            wavFile.close();
        } catch (IOException | WavFileException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
