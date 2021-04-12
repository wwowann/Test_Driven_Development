import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CreditCalculator {
    private double creditAmount;
    private double interestRate;
    private int creditTerm;
    private final String[] monthNames = {"Январь",
            "Февраль", "Март", "Апрель", "Май", "Июнь",
            "Июль", "Август", "Сентябрь", "Октябрь",
            "Ноябрь", "Декабрь"};

    public void setCreditAmount(long creditAmount) {
        this.creditAmount = creditAmount;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setCreditTerm(int creditTerm) {
        this.creditTerm = creditTerm;
    }

    public List<PaymentSchedule> calculationAnnuityPayment(double creditAmount,
                                                           List<PaymentSchedule> paymentScheduleList) {
        if (creditTerm==0){ throw new ArithmeticException("Делить на ноль нельзя");}
        double balanceEndMomth_Sn2 = creditAmount;
        double interestPerMonth_i = interestRate / 100 / creditTerm;//емесячная процентная ставка
        double ammountAnnuitedPayment_P = creditAmount * (interestPerMonth_i + (interestPerMonth_i /
                (Math.pow(1 + interestPerMonth_i, creditTerm) - 1)));//ежемесячный аннуитетный платеж
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String dateString;
        for (int i = 0; i < creditTerm; i++) {
            dateString = calendar.get(Calendar.YEAR) +
                    " " + monthNames[calendar.get(Calendar.MONTH)];
            double interestForAnnuitedPayment_In = balanceEndMomth_Sn2 * interestPerMonth_i;//расчет процентов по ануитетным платежам
            double paidCreditInAnnuitedPayment_S = ammountAnnuitedPayment_P - interestForAnnuitedPayment_In;//расчет
            // доли тела кредита в аннуитетных платежах
            paymentScheduleList.add(new PaymentSchedule(i + 1, dateString,
                    Math.round(ammountAnnuitedPayment_P), Math.round(interestForAnnuitedPayment_In),
                    Math.round(paidCreditInAnnuitedPayment_S), Math.round(balanceEndMomth_Sn2)));
            calendar.add(Calendar.MONTH, 1);
            balanceEndMomth_Sn2 -= paidCreditInAnnuitedPayment_S;    //расчет долга на конец месяца
        }
        return paymentScheduleList;
    }

    public List<PaymentSchedule> calculationDifferentiatedPayment(
            int creditTerm, double creditAmount,
            List<PaymentSchedule> paymentScheduleList) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        double ammountCredit_S = creditAmount;//размер кредита
        String dateString;
        double ammountPaid_St = ammountCredit_S / creditTerm;//аннуитетный платеж
        for (int i = 0; i < creditTerm; i++) {
            dateString = calendar.get(Calendar.YEAR) +
                    " " + monthNames[calendar.get(Calendar.MONTH)];
            double ammountBalanceOfDept_Sn = ammountCredit_S - ammountPaid_St;//остаток задолженности по кредиту
            double interestPerMonth_In = ammountBalanceOfDept_Sn * interestRate / 100 / creditTerm;//рассчет процентов в дифф.платеже
            double ammountDifferentionPayment_P = ammountPaid_St + interestPerMonth_In; // дифференцированный платеж
            paymentScheduleList.add(new PaymentSchedule(i + 1, dateString,
                    Math.round(ammountDifferentionPayment_P), Math.round(interestPerMonth_In),
                    Math.round(ammountPaid_St), Math.round(ammountBalanceOfDept_Sn)));
            calendar.add(Calendar.MONTH, 1);
            ammountCredit_S = ammountBalanceOfDept_Sn;
        }
        return paymentScheduleList;
    }


    public double getCreditAmount() {
        return creditAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getCreditTerm() {
        return creditTerm;
    }
}


