package wavbuffer;

import java.util.LinkedList;
import java.util.List;

public class WavBuffer<C> {

    private List<C> left = new LinkedList<>();
    private List<C> right = new LinkedList<>();

    public void addSample(C left, C right) {
        this.left.add(left);
        this.right.add(right);
    }

    public void addSample(C sample) {
        this.left.add(sample);
        this.right.add(sample);
    }

    public StereoSample<C> popSample() {
        C left = this.left.get(0);
        C right = this.right.get(0);
        this.left.remove(0);
        this.right.remove(0);
        return new StereoSample<>(left, right);
    }

    public final List<C> getLeftChannel() {
        return this.left;
    }

    public final List<C> getRightChannel() {
        return this.right;
    }

    public int size() {
        return this.left.size();
    }

}
