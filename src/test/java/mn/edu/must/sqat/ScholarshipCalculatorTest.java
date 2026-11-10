package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScholarshipCalculatorTest {
    private final ScholarshipCalculator calculator = new ScholarshipCalculator();

    @Test void letterGradeA() { assertEquals("A", calculator.letterGrade(95)); }
    @Test void letterGradeB() { assertEquals("B", calculator.letterGrade(85)); }
    @Test void gpaTwoGrades() { assertEquals(3.5, calculator.gpa(new double[]{95, 85})); }
    @Test void scholarshipTopTier() { assertEquals(100, calculator.scholarship(3.9, 16, false)); }
    @Test void scholarshipMiddleTier() { assertEquals(70, calculator.scholarship(3.5, 13, false)); }

    @Test void letterGradeBelowRangeThrows() {
        assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(-1));
    }
    @Test void letterGradeAboveRangeThrows() {
        assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(101));
    }
    @Test void letterGradeC() { assertEquals("C", calculator.letterGrade(75)); }
    @Test void letterGradeD() { assertEquals("D", calculator.letterGrade(65)); }
    @Test void letterGradeF() { assertEquals("F", calculator.letterGrade(55)); }
    @Test void gpaCoversAllGradePoints() {
        assertEquals(2.0, calculator.gpa(new double[]{95, 85, 75, 65, 55}));
    }
    @Test void gpaNullThrows() {
        assertThrows(IllegalArgumentException.class, () -> calculator.gpa(null));
    }
    @Test void gpaEmptyThrows() {
        assertThrows(IllegalArgumentException.class, () -> calculator.gpa(new double[]{}));
    }
    @Test void gpaInvalidScoreThrowsFromLetterGrade() {
        assertThrows(IllegalArgumentException.class, () -> calculator.gpa(new double[]{-1}));
    }
    @Test void scholarshipInvalidLowGpaThrows() {
        assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(-0.1, 16, false));
    }
    @Test void scholarshipInvalidHighGpaThrows() {
        assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(4.1, 16, false));
    }
    @Test void scholarshipInvalidCreditsThrows() {
        assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(3.9, -1, false));
    }
    @Test void scholarshipViolationGetsZero() {
        assertEquals(0, calculator.scholarship(4.0, 20, true));
    }
    @Test void scholarshipTopTierNeedsBothCriteria() {
        assertEquals(100, calculator.scholarship(3.9, 16, false));
        assertEquals(70, calculator.scholarship(3.9, 14, false));
        assertEquals(70, calculator.scholarship(3.7, 16, false));
    }
    @Test void scholarshipMiddleAndLowerTiers() {
        assertEquals(70, calculator.scholarship(3.5, 13, false));
        assertEquals(50, calculator.scholarship(3.5, 10, false));
        assertEquals(50, calculator.scholarship(3.3, 13, false));
        assertEquals(50, calculator.scholarship(3.0, 0, false));
        assertEquals(0, calculator.scholarship(2.9, 13, false));
    }
}
