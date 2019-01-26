package wavbuffer;

public class StereoSample<C> {

    public final C left;
    public final C right;

    public StereoSample(C left, C right) {
        this.left = left;
        this.right = right;
    }

}
