package synthesizer;

public class SineSynthesizer implements  ISynthesizer {

    private final double sampleFrequency;

    private double freq;
    private double time = 0;

    public SineSynthesizer(double sampleFrequency) {
        this.sampleFrequency = sampleFrequency;
    }

    public double getNextSample() {
        time += 1/sampleFrequency;
        return Math.sin(2*Math.PI*freq*time);
    }

    public void start(double frequency) {
        double angle = 2*Math.PI*freq*time;
        this.freq = frequency;
        this.time = angle/(2*Math.PI*frequency);
    }

    public void clear() {
        time = 0;
    }

}
