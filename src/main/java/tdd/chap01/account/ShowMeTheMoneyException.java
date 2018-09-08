package tdd.chap01.account;

public class ShowMeTheMoneyException extends ArithmeticException {
    public ShowMeTheMoneyException() {
        super("계좌 잔액은 0보다 커야한다.");
    }
}
