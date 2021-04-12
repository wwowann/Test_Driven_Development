import java.util.Date;

public class PaymentSchedule {
    private final int number;
    private final String date;
    private final double differentionPaid;
    private final double interestPerMonth;
    private final double paid;
    private final double ammountBalanceOfDept_Sn;

    public PaymentSchedule(int number, String date, double differentionPaid, double interestPerMonth, double paid, double ammountBalanceOfDept_Sn) {
        this.number = number;
        this.date = date;
        this.differentionPaid = differentionPaid;
        this.interestPerMonth = interestPerMonth;
        this.paid = paid;
        this.ammountBalanceOfDept_Sn = ammountBalanceOfDept_Sn;
    }

    public int getNumber() {
        return number;
    }

    public String getDate() {
        return date;
    }

    public double getDifferentionPaid() {
        return differentionPaid;
    }

    public double getInterestPerMonth() {
        return interestPerMonth;
    }

    public double getPaid() {
        return paid;
    }

    public double getAmmountBalanceOfDept_Sn() {
        return ammountBalanceOfDept_Sn;
    }

    @Override
    public String toString() {
        return  number + date +
                differentionPaid +
                interestPerMonth + paid +
                ammountBalanceOfDept_Sn +
                "\n";
    }
}
