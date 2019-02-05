package filter;

import java.util.ArrayList;
import java.util.LinkedList;

public class ShortBandFIRFilter extends FIRFilter {

    public ShortBandFIRFilter(int length, int passedBandNumber) {
        memory = new LinkedList<>();
        coefficients = new ArrayList<>();
        Complex[] spectrum = new Complex[length*2];
        for (int i = 0; i < length; i++) {
            if (i == passedBandNumber) {
                spectrum[i] = new Complex(1.0, 0.0);
                spectrum[2*length - i - 1] = new Complex(1.0, 0.0);
            } else {
                spectrum[i] = new Complex(0.0, 0.0);
                spectrum[2*length - i - 1] = new Complex(0.0, 0.0);
            }
        }
        Complex[] coefs = FFT.ifft(spectrum);
        for (Complex value : coefs) {
            coefficients.add(value.re());
        }
    }

}
