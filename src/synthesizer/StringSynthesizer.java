package synthesizer;

import java.util.ArrayList;

public class StringSynthesizer implements ISynthesizer {

    private final double sampleFrequency;
    private final double tuningFactor = 3.1348;
    private final int stateSize = 10000;

    private ArrayList<Double> prevState = new ArrayList<>();
    private ArrayList<Double> actState = new ArrayList<>();
    private double actFrequency = 0;

    public StringSynthesizer(double sampleFrequency) {
        this.sampleFrequency = sampleFrequency;
        for (int i = 0; i < stateSize; i++) {
            this.prevState.add(0.0);
            this.actState.add(0.0);
        }
    }

    public double getNextSample() {
        int n = this.actState.size() - 1;
        double a = tuningFactor*tuningFactor*actFrequency*actFrequency/sampleFrequency/sampleFrequency;
        //Create next state
        ArrayList<Double> nextState = new ArrayList<>(n+1);
        nextState.add(2*a*actState.get(1) - prevState.get(0) + (2-2*a)*actState.get(0));
        for (int i = 1; i < n; i++) {
            nextState.add(a*actState.get(i+1) + a*actState.get(i-1) - prevState.get(i) + (2-2*a)*actState.get(i));
        }
        nextState.add(2*a*actState.get(n-1) - prevState.get(n) + (2-2*a)*actState.get(n));
        //Calculate max abs and mean
        double maxAbs = 0.0;
        double mean = 0.0;
        for (double sample : nextState) {
            double sampleAbs = Math.abs(sample);
            if (sampleAbs > Math.abs(maxAbs)) {
                maxAbs = sample;
            }
            mean += sample;
        }
        mean /= stateSize;
        //Update state
        for (int i = 0; i < nextState.size(); i++) {
            nextState.set(i, nextState.get(i) - mean);
        }
        maxAbs -= mean;
        if (Math.abs(maxAbs) > 1.0) {
            for (int i = 0; i < nextState.size(); i++) {
                nextState.set(i, nextState.get(i) / Math.abs(maxAbs));
            }
        }
        //Add to memory
        prevState = actState;
        actState = nextState;

//        for (int i = 0; i < n+1; i++) {
//            System.out.print(actState.get(i) + " , ");
//        }
//        System.out.println("end");

        return prevState.get(0);
    }

    public void start(double frequency) {
        actFrequency = frequency;
//        for (int i = 1; i < stateSize; i++) {
//            this.actState.set(i, Math.random()/stateSize);
//        }
        this.actState.set(1, 1.0);
    }

    public void clear() {
        actFrequency = 0;
        for (int i = 0; i < stateSize; i++) {
            this.prevState.set(i, 0.0);
            this.actState.set(i, 0.0);
        }
    }

}
