package img.imaginary;

import java.util.ArrayList;
import java.util.List;

public final class NumberConverterUtil {

    private NumberConverterUtil() {
    }

    public static List<Integer> getDigitsFromNumber(int number) {
        List<Integer> digits = new ArrayList<>();
        if (number == 0) {
            digits.add(0);
        } else {
            number = Math.abs(number);
            while (number >= 1) {
                int lastDigit = (number % 10);
                digits.add(0, lastDigit);
                number /= 10;
            }
        }
        return digits;
    }

    public static int getNumberLength(int number) {
        int negative = (number < 0) ? 2 : 1;
        if (number == 0) {
            return 1;
        }
        number = Math.abs(number);
        return (int) Math.log10(number) + negative;
    }

    public static String copyNTimes(String symbol, int quantity) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < quantity; i++) {
            builder.append(symbol);
        }
        return builder.toString();
    }
}
