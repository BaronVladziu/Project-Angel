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

    public void writeFile(WavBuffer<Integer> wavBuffer, String fileName) {
        try {
            WavFile wavFile = WavFile.newWavFile(new File(fileName + ".wav"), 2,
                    wavBuffer.size(), 16, sampleRate);

            long[][] buffer = new long[2][100];
            long frameCounter = 0;

            while (frameCounter < wavBuffer.size()) {
                long remaining = wavFile.getFramesRemaining();
                int toWrite = (remaining > 100) ? 100 : (int) remaining;
                for (int s = 0 ; s < toWrite ; s++, frameCounter++) {
                    StereoSample<Integer> stereoSample = wavBuffer.popSample();
                    buffer[0][s] = (long)(stereoSample.left);
                    buffer[1][s] = (long)(stereoSample.right);
//                    System.out.println(buffer[0][s] + " , " + buffer[1][s]);
                }
                wavFile.writeFrames(buffer, toWrite);
            }

            wavFile.close();
        } catch (IOException | WavFileException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
