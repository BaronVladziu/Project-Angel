package vocoder;

import filter.FIRFilter;
import filter.ShortBandFIRFilter;
import synthesizer.ISynthesizer;
import synthesizer.PulseSynthesizer;

public class PulseFilteredVocoder implements IVocoder {

    private ISynthesizer synth;
    private FIRFilter filter = new ShortBandFIRFilter(64, 10);

    public PulseFilteredVocoder(double sampleFrequency) {
        synth = new PulseSynthesizer(sampleFrequency);
    }

    public void start(double frequency, Sound sound) {
        synth.start(frequency);
    }

    public double getNextSample() {
        return filter.filter(synth.getNextSample());
    }

    public void clear() {
        synth.clear();
        filter.clear();
    }

}
