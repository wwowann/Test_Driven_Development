import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyCreditCalculatorTest {
    CreditCalculator creditCalculator;
    @BeforeEach
    public void setUp(){
       creditCalculator = new CreditCalculator();

    }

    @Test
    public void depositAmountShouldBiPositive() {//размер вклада
        assertTrue(creditCalculator.getDepositAmount() > 0);
    }

    @Test
    public void creditTermShouldBiPositive() {//срок кредита
        assertTrue(creditCalculator.getCreditTerm() > 0);
    }

    @Test
    public void interestRateShouldBiPositive() {//размер процентной ставки
        assertTrue(creditCalculator.getInterestRate() > 0);
    }

    @Test
    public void calculationAnnuityPayment() {//расчет аннуитентного платежа
        long value = creditCalculator.calculationAnnuityPayment(creditCalculator.getDepositAmount(),
                creditCalculator.getCreditTerm(), creditCalculator.getInterestRate());
        assertEquals(1000, value);
    }

    @Test
    public void calculationAnnuityCoefficient() {
        long value = creditCalculator.calculationAnnuityCoefficient(creditCalculator.getInterestRate(),
                creditCalculator.getCreditTerm());
        assertEquals(1, value);
    }


}
