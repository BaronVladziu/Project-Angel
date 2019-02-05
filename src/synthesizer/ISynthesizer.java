package synthesizer;

public interface ISynthesizer {

    double getNextSample();
    void start(double frequency);
    void clear();

}
