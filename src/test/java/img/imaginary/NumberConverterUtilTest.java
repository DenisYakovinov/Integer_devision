package img.imaginary;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class NumberConverterUtilTest {

    @Test
    void getDigitsFromNumber_ShouldReturnListOfDigits_WhenPositiveNumber() {
        List<Integer> expected = Arrays.asList(1, 6, 7, 1, 1);
        List<Integer> actual = NumberConverterUtil.getDigitsFromNumber(16711);
        assertIterableEquals(expected, actual);
    }

    @Test
    void getDigitsFromNumber_ShouldReturnListWithOnlyZero_WhenZero() {
        List<Integer> expected = Arrays.asList(0);
        List<Integer> actual = NumberConverterUtil.getDigitsFromNumber(0);
        assertIterableEquals(expected, actual);
    }
    
    @Test
    void getDigitsFromNumber_ShouldReturnListOfDigitsOnly_WhenNegativeNumber() {
        List<Integer> expected = Arrays.asList(1, 6, 7, 1, 1);
        List<Integer> actual = NumberConverterUtil.getDigitsFromNumber(-16711);
        assertIterableEquals(expected, actual);
    }

    @Test
    void getNumberLength_ShouldReturnNumberLength_WhenPositiveNumber() {
        int expected = 5;
        int actual = NumberConverterUtil.getNumberLength(98712);
        assertEquals(expected, actual);
    }

    @Test
    void getNumberLength_ShouldReturnNumberLengthIncludingMinus_WhenNegativeNumber() {
        int expected = 6;
        int actual = NumberConverterUtil.getNumberLength(-98712);
        assertEquals(expected, actual);
    }

    @Test
    void getNumberLength_ShouldReturnNumberLength_WhenZero() {
        int expected = 1;
        int actual = NumberConverterUtil.getNumberLength(0);
        assertEquals(expected, actual);
    }
    
    @Test
    void copyNtimes_ShouldReturnLineOfSymbolsOfLengthN_WhenAnySymbolAndPositiveNumber() {
        String expected ="-----";
        String actual = NumberConverterUtil.copyNTimes("-", 5);
        assertEquals(expected, actual);
    }
}
