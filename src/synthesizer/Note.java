package synthesizer;

public class Note {

    public final static double REFERENCE_a1_FREQUENCY = 440;

    public final NoteName noteName;
    public final int octave;

    public Note(NoteName noteName) {
        this.noteName = noteName;
        this.octave = 0;
    }

    public Note(NoteName noteName, int octave) {
        this.noteName = noteName;
        this.octave = octave;
    }

    public double getFrequency() {
        switch (noteName) {
            case Ces:
                return getFrequency(-10);
            case C:
                return getFrequency(-9);
            case Cis:
            case Des:
                return getFrequency(-8);
            case D:
                return getFrequency(-7);
            case Dis:
            case Es:
                return getFrequency(-6);
            case E:
            case Fes:
                return getFrequency(-5);
            case Eis:
            case F:
                return getFrequency(-4);
            case Fis:
            case Ges:
                return getFrequency(-3);
            case G:
                return getFrequency(-2);
            case Gis:
            case As:
                return getFrequency(-1);
            case A:
                return getFrequency(0);
            case Ais:
            case Bes:
                return getFrequency(1);
            case B:
                return getFrequency(2);
            case Bis:
                return getFrequency(3);
        }
        throw new RuntimeException("Note name was not understood!");
    }

    private double getFrequency(int semitones) {
        return REFERENCE_a1_FREQUENCY*shiftFactor(semitones + 12*(octave - 1));
    }

    private double shiftFactor(int semitones) {
        return Math.pow(2, (double)semitones/12);
    }

    public String toString() {
        if (this.octave > 0) {
            return noteName.toString().toLowerCase() + octave;
        } else if (this.octave == 0) {
            return noteName.toString().toLowerCase();
        } else if (this.octave == -1) {
            return noteName.toString();
        } else {
            return noteName.toString() + Math.abs(octave);
        }
    }

}
