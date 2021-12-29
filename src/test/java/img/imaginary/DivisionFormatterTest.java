package img.imaginary;

import org.junit.jupiter.api.Test;

import img.imaginary.DivisionFormatter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DivisionFormatterTest {

    @Test
    void format_ShouldReturnLineFormattedAsLongDivision_WhenTwoPositiveNumbers() {
        DivisionFormatter formatter = new DivisionFormatter();
        String expected = (String.join(System.lineSeparator(),
                "_7701|7", 
                " 7   |----", 
                " -   |1100",
                " _7", 
                "  7", 
                "  -", 
                "    1"));
        String actual = formatter.format(7701, 7); 
        assertEquals(expected, actual);        
    }

    @Test
    void format_ShouldReturnLineFormattedAsLongDivision_WhenDividendIsPositiveDivisorIsNegative() {
        DivisionFormatter formatter = new DivisionFormatter();
        String expected = (String.join(System.lineSeparator(),
                "_100|-9",
                "  9 |---",
                "  - |-11",
                " _10",
                "   9",
                "   -",
                "   1"));
        String actual = formatter.format(100, -9); 
        assertEquals(expected, actual);        
    }
    
    @Test
    void format_ShouldReturnLineFormattedAsLongDivision_WhenRemainderEqualsZero() {
        DivisionFormatter formatter = new DivisionFormatter();
        String expected = (String.join(System.lineSeparator(),
                "_20|4", 
                " 20|-",
                " --|5", 
                "  0"));
        String actual = formatter.format(20, 4); 
        assertEquals(expected, actual);    
    }

    @Test
    void format_ShouldReturnLineFormattedAsLongDivision_WhenAllValuesEqualOne() {
        DivisionFormatter formatter = new DivisionFormatter();
        String expected = (String.join(System.lineSeparator(),
                "_1|1",
                " 1|-",
                " -|1",
                " 0"));
        String actual = formatter.format(1, 1); 
        assertEquals(expected, actual);    
    }

    @Test
    void format_ShouldReturnLineFormattedAsLongDivision_WhenDividendEqualsZero() {
        DivisionFormatter formatter = new DivisionFormatter();
        String expected = (String.join(System.lineSeparator(),
                "0|100", 
                " |-",
                " |0"));
        String actual = formatter.format(0, 100); 
        assertEquals(expected, actual);    
    }
    
    @Test
    void format_ShouldReturnLineFormattedAsLongDivision_WhenTwoNegativeNumbers() { 
        DivisionFormatter formatter = new DivisionFormatter();
        String expected = (String.join(System.lineSeparator(),
                "_-9007|-19",
                "  76  |---",
                "  --  |474",
                " _140",
                "  133",
                "  ---",
                "   _77",
                "    76",
                "    --",
                "     1"));
        String actual = formatter.format(-9007, -19); 
        assertEquals(expected, actual);    
    }

    @Test
    void format_ShouldThrowIllegalArgumentException_WhenDivisorEqualsZero() {
        DivisionFormatter formatter = new DivisionFormatter();
        assertThrows(IllegalArgumentException.class, () -> formatter.format(12, 0));       
    }
    
    @Test
    void format_ShouldReturnLineFormattedAsLongDivision_WhenDividendIsNegative() {
        DivisionFormatter formatter = new DivisionFormatter();
        String expected = (String.join(System.lineSeparator(),
                "_-9001|1",
                "  9   |-----",
                "  -   |-9001",
                "    _1",
                "     1",
                "     -",
                "     0"));
        String actual = formatter.format(-9001, 1); 
        assertEquals(expected, actual);    
    }   
}

