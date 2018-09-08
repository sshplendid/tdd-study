package tdd.chap01.account;



import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


/**
 *
 * 실습 목표
 * 1. TDD의 진행방식을 익힌다.
 * 2. 시간을 절약해줄 IDE의 기능을 익힌다.
 * 3. TDD를 진행할 때 발생할 수 있는 몇가지 고려사항을 살펴본다.
 *
 * 실습예제: 은행계좌 클래스 만들기
 *
 */
public class AccountTest {
    private Account account;
    // TODO 예상 복리 이자 (추가개발)

    @Before
    public void setUp() {
        account = new Account(10000);
    }

    @Test
    public void testAccount() {
        // TODO 은행계좌 클래스 만들기
        assertThat(account, notNullValue());
    }


    @Test
    public void testGetBalance() {
        // TODO 계좌 잔고 조회
        // 시나리오: 10000원으로 계좌 생성 -> 잔고 조회결과 일치

        assertEquals(10000, account.getBalance());

        account = new Account(1000);
        assertEquals(1000, account.getBalance());

        account = new Account(0);
        assertEquals(0, account.getBalance());
    }

    @Test
    public void testDeposit() {
        // TODO 입금
        account.deposit(1000);

        assertEquals(11000, account.getBalance());

    }

    @Test
    public void testWithdraw() {
        // TODO 출금
        account.withdraw(1000);

        assertEquals(9000, account.getBalance());
    }

    @Test(expected = ShowMeTheMoneyException.class)
    public void testWithdraw_잔고보다큰금액을_인출시도하면_예외가발생한다() {
        account.withdraw(20000);
        assertThat(account.getBalance(), greaterThan(0));
    }
}

