package img.imaginary;

import static img.imaginary.NumberConverterUtil.copyNTimes;
import static img.imaginary.NumberConverterUtil.getDigitsFromNumber;
import static img.imaginary.NumberConverterUtil.getNumberLength;

import java.util.ArrayList;
import java.util.List;

public class DivisionFormatter {

    private static final String ONE_INDENT = " ";
    private static final String DASH = "-";

    public String format(int dividend, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("The divisor can't be zero");
        }
        int quotient = dividend / divisor;
        if (quotient == 0) {
            return formatDividendLessDivider(dividend, divisor);
        }
        List<DivisionStep> divisionSteps = createDivisionSteps(dividend, divisor);
        List<String> formattedResultLines = buildBaseLines(divisionSteps);
        DivisionStep secondDivisionStep = divisionSteps.get(1);
        String topLine = buildTopLine(secondDivisionStep, dividend, divisor, quotient);
        formattedResultLines.add(0, topLine);
        int lastIndex = divisionSteps.size() - 1;
        String lastLine = copyNTimes(ONE_INDENT, divisionSteps.get(lastIndex).getIndent())
                + divisionSteps.get(lastIndex).getStepValue();
        formattedResultLines.add(lastLine);
        return String.join(System.lineSeparator(), formattedResultLines);
    }

    private String buildTopLine(DivisionStep lastStep, int dividend, int divisor, int quotient) {
        String firstLine = String.format("_%d|%d%n", dividend, divisor);
        int valueStep = lastStep.getStepValue();
        String indentBeforeValue = copyNTimes(ONE_INDENT, lastStep.getIndent());
        String indentAfterValue = copyNTimes(ONE_INDENT,
                (getNumberLength(dividend) + 1) - lastStep.getIndent() - getNumberLength(valueStep));
        String dashesUnderQuotient = copyNTimes(DASH, getNumberLength(quotient));
        String secondLine = String.format("%s%d%s|%s%n", indentBeforeValue, valueStep, indentAfterValue,
                dashesUnderQuotient);
        String dashesUnderValue = copyNTimes(DASH, getNumberLength(valueStep));
        String thirdLine = String.format("%s%s%s|%d", indentBeforeValue, dashesUnderValue, indentAfterValue, quotient);
        return String.format("%s%s%s", firstLine, secondLine, thirdLine);
    }

    private List<String> buildBaseLines(List<DivisionStep> divisionSteps) {
        List<String> baseLines = new ArrayList<>();
        for (int i = 2; i < divisionSteps.size() - 1; i += 2) {
            String indentValue = copyNTimes(ONE_INDENT, divisionSteps.get(i).getIndent() - 1);
            String indentNext = copyNTimes(ONE_INDENT, divisionSteps.get(i + 1).getIndent());
            int value = divisionSteps.get(i).getStepValue();
            int nextValue = divisionSteps.get(i + 1).getStepValue();
            String dashes = copyNTimes(DASH, getNumberLength(nextValue));
            baseLines.add(String.format("%s_%d", indentValue, value));
            baseLines.add(String.format("%s%d", indentNext, nextValue));
            baseLines.add(String.format("%s%s", indentNext, dashes));
        }
        return baseLines;
    }

    private String formatDividendLessDivider(int dividend, int divider) {
        String spaces = copyNTimes(ONE_INDENT, getNumberLength(dividend));
        return String.format("%d|%d%n%s|-%n%s|0", dividend, divider, spaces, spaces);
    }

    private List<DivisionStep> createDivisionSteps(int dividend, int divisor) {
        List<DivisionStep> divisionSteps = new ArrayList<>();
        int indent = (dividend < 0) ? 2 : 1;
        int absoluteDividend = Math.abs(dividend);
        int absoluteDivisor = Math.abs(divisor);        
        if (absoluteDividend < absoluteDivisor) {
            divisionSteps.add(new DivisionStep(0, 0));
        } else {
            List<Integer> digitsFromDividend = getDigitsFromNumber(dividend);
            divisionSteps = createBaseDivisionSteps(digitsFromDividend, absoluteDivisor, indent);
        }
        return divisionSteps;
    }

    private List<DivisionStep> createBaseDivisionSteps(List<Integer> digitsFromDividend, int absoluteDivisor, int indent) {
        int dividend = digitsFromDividend.get(0);
        List<DivisionStep> baseDivisionSteps = new ArrayList<>();
        for (int i = 0; i < digitsFromDividend.size(); i++) {
            boolean isLastIteration = (i == digitsFromDividend.size() - 1);
            if (dividend < absoluteDivisor) {
                if (isLastIteration) {
                    baseDivisionSteps.add(new DivisionStep(indent, dividend));
                    return baseDivisionSteps;
                }
                indent = (dividend == 0) ? ++indent : indent;
                dividend = (dividend * 10) + digitsFromDividend.get(i + 1);
                continue;
            }
            int remainder = dividend % absoluteDivisor;
            baseDivisionSteps.add(new DivisionStep(indent, dividend));
            indent += getNumberLength(dividend) - getNumberLength(dividend - remainder);
            baseDivisionSteps.add(new DivisionStep(indent, dividend - remainder));
            indent += getNumberLength(dividend - remainder) - getNumberLength(remainder);
            if (isLastIteration) {
                baseDivisionSteps.add(new DivisionStep(indent, remainder));
            } else {
                dividend = (remainder * 10) + digitsFromDividend.get(i + 1);
                indent = (remainder == 0) ? ++indent : indent;
            }
        }
        return baseDivisionSteps;
    }
}