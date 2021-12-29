package img.imaginary;

public class DivisionStep {
    private final int stepValue;
    private final int indent;

    public DivisionStep(int indent, int stepValue) {
        this.stepValue = stepValue;
        this.indent = indent;
    }

    public int getStepValue() {
        return stepValue;
    }

    public int getIndent() {
        return indent;
    } 
}
