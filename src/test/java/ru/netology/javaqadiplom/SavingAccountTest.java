package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SavingAccountTest {

    @Test
    public void shouldThrowExceptionWhenMinBalanceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    2_000,
                    -1_000,
                    10_000,
                    5
            );
        });

    }

    @Test
    public void shouldThrowExceptionWhenInitialBalanceLessThanMin() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    0,
                    500,
                    10_000,
                    5
            );
        });

    }

    @Test
    public void shouldThrowExceptionWhenMinBalanceOverMax() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    2_000,
                    12_000,
                    10_000,
                    5
            );
        });
    }

    @Test
    public void shouldThrowExceptionWhenInitialBalanceOverMax() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    12_000,
                    1_000,
                    10_000,
                    5
            );
        });
    }

    @Test
    public void shouldThrowExceptionWhenRateIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    2_000,
                    1_000,
                    10_000,
                    -5
            );
        });

    }

    @Test
    public void shouldPayWhenEqualsMinBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(1_000);

        Assertions.assertEquals(2_000 - 1_000, account.getBalance());
    }

    @Test
    public void shouldPayLessThanMinBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(1_500);

        Assertions.assertEquals(2000, account.getBalance());
    }

    @Test
    public void shouldPayOverMinBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(500);

        Assertions.assertEquals(2000 - 500, account.getBalance());
    }

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void shouldAddOverMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(9_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldAddEqualsMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(8_000);

        Assertions.assertEquals(2_000 + 8_000, account.getBalance());
    }

    @Test
    public void shouldCalculatePercentOnTheBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                15
        );

        Assertions.assertEquals(300, account.yearChange());
    }

    @Test
    public void shouldCalculatePercentOnTheSmallBalance() {
        SavingAccount account = new SavingAccount(
                150,
                100,
                10_000,
                15
        );

        Assertions.assertEquals(22, account.yearChange());
    }
    
}
