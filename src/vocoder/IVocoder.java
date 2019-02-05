package vocoder;

public interface IVocoder {

    double getNextSample();
    void start(double frequency, Sound sound);
    void clear();

}
