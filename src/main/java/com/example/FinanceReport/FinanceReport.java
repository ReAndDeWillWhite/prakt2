package com.example.FinanceReport;

import com.example.Payment.Payment;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import static com.example.FinanceReportProcessor.FinanceReportProcessor.fullNameBeginsWithLetter;
import static com.example.FinanceReportProcessor.FinanceReportProcessor.smallerPayments;

public class FinanceReport {

    private Payment payment;
    private Payment [] payments;
    private String fullAuthor;
    private Date reportDate;

    //main
    public static void main(String[] args) {
        Payment[] payments = {
            new Payment("Иванов Иван Иванович", 15, 03, 2024, 15000),
            new Payment("Петров Петр Петрович", 22, 04, 2024, 20000),
            new Payment("Сидорова Сидорова Сидоровна", 05, 05, 2024, 12000),
            new Payment("Кузнецов Кузьма Кузьмич", 20, 12, 2024, 30000)
        };
        Date reportDate = new Date();
        FinanceReport report = new FinanceReport(payments, "Кирилл Кирилович Кирилов", reportDate);

        System.out.println("Количество платежей: " + report.getAmountOfPayments());

        FinanceReport fullNameBeginsWithLetter = fullNameBeginsWithLetter(report, payments, 'И');
        System.out.println("\nПервая буква Фамилии 'И':\n" + fullNameBeginsWithLetter);

        FinanceReport smallerPayments = smallerPayments(report, payments, 20000);
        System.out.println("\nПлатежи, у которых сумма меньше\n" + smallerPayments);

        System.out.println("Платеж " + report.getIndexOfPayments(1));

        Payment newPayment = new Payment("Сидоров Сидор Сидорович", 20, 3, 2024, 75000);
        report.setIndexOfPayment(0, newPayment);
        System.out.println("Платеж " + report.getIndexOfPayments(0));
    }
    //main end

    public FinanceReport(Payment[] payments, String fullAftor, Date reportDate) {
        this.payments = Arrays.copyOf(payments, payments.length);
        this.fullAuthor = fullAftor;
        this.reportDate = reportDate;
    }

    public FinanceReport(FinanceReport other) {
        this(Arrays.copyOf(other.payments, other.payments.length), other.fullAuthor, other.reportDate);
    }


    //get
    public Payment[] getPayments() {
        return Arrays.copyOf(payments, payments.length);
    }

    public int getAmountOfPayments () {
        return payments.length;
    }

    public Payment getIndexOfPayments(int i)  {
        if (i < 0 || i>= payments.length){
            throw new IndexOutOfBoundsException("Индекс платежа находится вне допустимого диапазона.");
        } else {
            return payments [i];
        }

    }

    public String getFullNameAftor() {

        return fullAuthor;
    }

    public Date getReportDate() {

        return reportDate;
    }
    //get end

    //set
    public void setPayments(Payment[] payments) {
        this.payments = payments;
    }


    public void setIndexOfPayment(int i, Payment payment) {
        if (i < 0 || i >= payments.length) {
            throw new IndexOutOfBoundsException("Индекс платежа находится вне допустимого диапазона.");
        }
        if(payment == null){
            throw new NullPointerException("Платеж не может быть null");
        }else {
            payments [i] = payment;
        }
    }

    public void setFullNameAuthor(String fullAuthor) {

        this.fullAuthor = fullAuthor;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }
    //set end

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[Автор: %s, дата: %s , Платежи: [", getFullNameAftor(), sdf.format(reportDate)));
        for (Payment p : payments) {
            sb.append(String.format("Плательщик: %s, дата: %d.%d.%d сумма: %d руб. %d коп.\n", p.getName(), p.getDay(), p.getMonth(), p.getYear(), p.getSum() / 100, p.getSum()));
        }
            sb.append("]]");
        return sb.toString();
        }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FinanceReport that = (FinanceReport) o;
        return Objects.equals(payment, that.payment) && Objects.deepEquals(payments, that.payments) && Objects.equals(fullAuthor, that.fullAuthor) && Objects.equals(reportDate, that.reportDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payment, Arrays.hashCode(payments), fullAuthor, reportDate);
    }
}
