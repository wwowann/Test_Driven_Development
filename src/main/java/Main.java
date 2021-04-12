import java.util.*;

public class Main {
    public static void main(String[] args) {
        CreditCalculator creditCalculator = new CreditCalculator();
        Scanner scan = new Scanner(System.in);
        List<PaymentSchedule> paymentScheduleList = new ArrayList<>();
        System.out.println("Введите сумму кредита (положительное целое число");
        creditCalculator.setCreditAmount(Long.parseLong(scan.nextLine()));
        System.out.println("Введите срок кредита (в месяцах)");
        creditCalculator.setCreditTerm(Integer.parseInt(scan.nextLine()));
        System.out.println("Введите процентную ставку");
        creditCalculator.setInterestRate(Integer.parseInt(scan.nextLine()));
        System.out.println("Введите вид платежа: 1 - аннуитетный, 2 - дифференцированный");
        int value = Integer.parseInt(scan.nextLine());
        if (value == 1) {
            paymentScheduleList = creditCalculator.calculationAnnuityPayment(
                    creditCalculator.getCreditAmount(), paymentScheduleList);

        } else {
            paymentScheduleList = creditCalculator.calculationDifferentiatedPayment(
                    creditCalculator.getCreditTerm(),
                    creditCalculator.getCreditAmount(),
                    paymentScheduleList);
        }
        System.out.printf("%s%10s%22s%13s%16s%10s\n", "N п/п", "дата", "ежем.платеж",
                "проценты", "тело кредита", "остаток");
        System.out.println("_______________________________________________________________________________");
        for (PaymentSchedule pay : paymentScheduleList) {
            System.out.printf("№%-8d%-13s%14.1f%14.1f%14.1f%14.1f\n", pay.getNumber(), pay.getDate(),
                    pay.getDifferentionPaid(), pay.getInterestPerMonth(),
                    pay.getPaid(), pay.getAmmountBalanceOfDept_Sn());
        }
    }
}

