package synthesizer;

public class PulseSynthesizer implements ISynthesizer {

    private final double sampleFrequency;

    private double freq;
    private double time = 0;

    public PulseSynthesizer(double sampleFrequency) {
        this.sampleFrequency = sampleFrequency;
    }

    public double getNextSample() {
        time += 1/sampleFrequency;
        if (time*freq > 1) {
            time -= 1/freq;
            return 1;
        } else {
            return 0;
        }
    }

    public void start(double frequency) {
        this.freq = frequency;
    }

    public void clear() {
        time = 0;
    }

}
