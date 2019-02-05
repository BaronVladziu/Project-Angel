package filter;

import java.util.List;

public abstract class FIRFilter {

    protected List<Double> coefficients;
    protected List<Double> memory;

    public void clear() {
        memory.clear();
    }

    public double filter(double sample) {
        memory.add(0, sample);
        if (memory.size() > coefficients.size()) {
            memory.remove(memory.size() - 1);
        }
        return convolve();
    }

    protected double convolve() {
        double result = 0;
        for (int i = 0; i < memory.size(); i++) {
            result += coefficients.get(i) * memory.get(i);
        }
        return result;
    }

    protected FIRFilter add(FIRFilter filter) {
        FIRFilter result = new FIRFilter(){};
        if (this.coefficients.size() == filter.coefficients.size()) {
            for (int i = 0; i < memory.size(); i++) {
                result.coefficients.add(this.coefficients.get(i) + filter.coefficients.get(i));
            }
        }
        else {
            throw new RuntimeException("Cannot add filters of different order!");
        }
        return result;
    }

}
