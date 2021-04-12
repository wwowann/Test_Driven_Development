import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MyCreditCalculatorTest {
    CreditCalculator creditCalculator;
    ArrayList<PaymentSchedule> paymentScheduleList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        creditCalculator = new CreditCalculator();
    }

    @Test
    public void getPaymentScheduleListNotNull() {
        paymentScheduleList.add(new PaymentSchedule(1, "апрель 2021", 22,
                34, 2, 1));
        assertEquals(1, paymentScheduleList.size());
    }

    @Test
    public void listCalculationAnnuityPaymentNotEqualsListCalculationDifferentiatedPayment() {
        paymentScheduleList.add(new PaymentSchedule(1, "апрель 2021", 22,
                34, 2, 1));
        assertEquals(creditCalculator.calculationAnnuityPayment(
                200000, paymentScheduleList),
                creditCalculator.calculationDifferentiatedPayment(
                        12, 200000, paymentScheduleList));
    }

    @Test
    public void returnObjectNotNull() {
        assertNotNull(creditCalculator.calculationAnnuityPayment(
                200000, paymentScheduleList));
    }

    @Test
    public void returnTypeObjectNotEquals() {
        assertNotEquals(PaymentSchedule.class, creditCalculator.calculationAnnuityPayment(
                200000, paymentScheduleList).getClass());
    }

    @Test
    public void returnTypeObjectEquals() {
        assertEquals(creditCalculator.calculationDifferentiatedPayment(
                12, 200000, paymentScheduleList).getClass(),
                creditCalculator.calculationAnnuityPayment(
                        200000, paymentScheduleList).getClass());
    }

    @Test
    public void interestPerMonthShouldArithmeticException() {//проверка деления на 0
        paymentScheduleList.add(new PaymentSchedule(1, "апрель 2021", 22,
                34, 2, 1));
        creditCalculator.setCreditTerm(0);
        assertThrows(ArithmeticException.class, () ->
                creditCalculator.calculationAnnuityPayment(1000000, paymentScheduleList));
    }

    @Test
    public void creditTermShouldBiPositive() {//срок кредита
        creditCalculator.setCreditTerm(12);
        assertTrue(
                creditCalculator.getCreditTerm() > 0);
    }

    @Test
    public void interestRateShouldBiPositive() {//размер процентной ставки

        creditCalculator.setInterestRate(0.8);
        assertTrue(
                creditCalculator.getInterestRate() > 0);
    }


}
