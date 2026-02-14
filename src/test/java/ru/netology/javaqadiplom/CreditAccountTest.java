package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldThrowExceptionWhenInitialBalanceNegative() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(-100, 3_000, 15);
        });
    }

    @Test
    public void shouldThrowExceptionWhenCreditLimitNegative() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(0, -3_000, 15);
        });
    }

    @Test
    public void shouldThrowExceptionWhenRateNegative() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(0, 3_000, -15);
        });
    }

    @Test
    public void shouldPayWhenAmountPositiveAndWithinCreditLimit() {

        CreditAccount account = new CreditAccount(1_000, 5_000, 15);


        boolean result = account.pay(500);


        Assertions.assertTrue(result);
        Assertions.assertEquals(500, account.getBalance());
    }

    @Test
    public void shouldPayWhenAmountPositiveAndBalanceGoesNegativeWithinLimit() {

        CreditAccount account = new CreditAccount(1_000, 5_000, 15);

        boolean result = account.pay(3_000);

        Assertions.assertTrue(result);
        Assertions.assertEquals(-2_000, account.getBalance());
    }

    @Test
    public void shouldPayWhenAmountPositiveAndBalanceExactlyAtCreditLimit() {

        CreditAccount account = new CreditAccount(1_000, 5_000, 15);

        boolean result = account.pay(6_000);

        Assertions.assertTrue(result);
        Assertions.assertEquals(-5_000, account.getBalance());
    }

    @Test
    public void shouldNotPayWhenAmountExceedsCreditLimit() {

        CreditAccount account = new CreditAccount(1_000, 5_000, 15);
        int initialBalance = account.getBalance();

        boolean result = account.pay(7_000);

        // Операция должна быть отклонена, баланс не должен измениться
        Assertions.assertFalse(result);
        Assertions.assertEquals(initialBalance, account.getBalance());
    }


    @Test
    public void shouldNotPayWhenAmountZero() {
        CreditAccount account = new CreditAccount(1_000, 5_000, 15);
        int initialBalance = account.getBalance();

        boolean result = account.pay(0);

        Assertions.assertFalse(result);
        Assertions.assertEquals(initialBalance, account.getBalance());
    }

    @Test
    public void shouldNotPayWhenAmountNegative() {
        CreditAccount account = new CreditAccount(1_000, 5_000, 15);
        int initialBalance = account.getBalance();

        boolean result = account.pay(-500);

        Assertions.assertFalse(result);
        Assertions.assertEquals(initialBalance, account.getBalance());
    }

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }
    @Test
    public void shouldNotAddWhenAmountZero() {
        CreditAccount account = new CreditAccount(1_000, 5_000, 15);
        int initialBalance = account.getBalance();

        boolean result = account.add(0);

        Assertions.assertFalse(result);
        Assertions.assertEquals(initialBalance, account.getBalance());
    }

    @Test
    public void shouldNotAddWhenAmountNegative() {
        CreditAccount account = new CreditAccount(1_000, 5_000, 15);
        int initialBalance = account.getBalance();

        boolean result = account.add(-500);

        Assertions.assertFalse(result);
        Assertions.assertEquals(initialBalance, account.getBalance());
    }

    @Test
    public void shouldReturnZeroYearChangeWhenBalancePositive() {

        CreditAccount account = new CreditAccount(2_000, 5_000, 15);

        int result = account.yearChange();

        Assertions.assertEquals(0, result);
    }

    @Test
    public void shouldReturnZeroYearChangeWhenBalanceZero() {

        CreditAccount account = new CreditAccount(0, 5_000, 15);

        int result = account.yearChange();

        Assertions.assertEquals(0, result);
    }

    @Test
    public void shouldCalculateYearChangeWhenBalanceNegative() {

        CreditAccount account = new CreditAccount(-2_000, 5_000, 15);

        int result = account.yearChange();

        Assertions.assertEquals(-300, result);
    }

    @Test
    public void shouldCalculateYearChangeWhenBalanceNegativeAndSmallAmount() {

        CreditAccount account = new CreditAccount(-50, 5_000, 15);

        int result = account.yearChange();

        Assertions.assertEquals(-7, result);
    }


}
