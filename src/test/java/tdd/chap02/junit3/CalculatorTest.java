package tdd.chap02.junit3;

import junit.framework.TestCase;

public class CalculatorTest extends TestCase {

    private Calculator calculator;

    public CalculatorTest() {
//        System.out.println("construct new CalculatorTest Instance.");
    }
    public void setUp() {
        System.out.println("Test started");
        calculator = new Calculator();
    }

    public void tearDown() {
        System.out.println("Test finished");
    }

    public void testConstructor() {
//        assertNotNull(calculator);
    }

    public void testSum() {
        assertEquals(3, calculator.sum(1, 2));
        assertEquals(2, calculator.sum(1, 1));
    }

    public void testSum_doubleType() {
        assertEquals(2.0001, calculator.sum(1.00005, 1.00005));
        assertEquals(1.0000001, calculator.sum(0.50000005, 0.50000005));
    }

    public void testDiv() {
        assertEquals(0d, calculator.div(0, 2));
        assertEquals(2d, calculator.div(4, 2));
        assertEquals(1d, calculator.div(4, 4));
    }

    public void testDiv_나누기를할때_소수둘째자리까지의오차만_허용한다() {
        assertEquals(0.33, calculator.div(1,3), 0.01);
        assertEquals(0.2, calculator.div(1,5), 0.01);
    }

    public void testDiv_분모가_0이면_Infinity를리턴한다() {
        assertTrue(Double.isInfinite(calculator.div(1,0)));
    }

    public void testSameInstance() {
        Calculator clone = calculator;
        assertSame(clone, calculator);
    }

    public void testMultiply() {
        assertEquals(4, calculator.mul(2,2));
//        fail("아직 기능구현을 안했다!"); // TODO Calculator.mul 구현하기
    }
}
