package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class BankAccountTest {

    @Test
    void createsAccountWithValidBalance() {
        BankAccount account = new BankAccount(100);

        assertEquals(100, account.getBalance());
    }

    @Test
    void createsAccountWithZeroBalance() {
        BankAccount account = new BankAccount(0);

        assertEquals(0, account.getBalance());
    }

    @Test
    void throwsForNegativeInitialBalance() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(-1));
    }

    @Test
    void depositsMoney() {
        BankAccount account = new BankAccount(100);

        account.deposit(50);

        assertEquals(150, account.getBalance());
    }

    @Test
    void throwsForZeroDeposit() {
        BankAccount account = new BankAccount(100);

        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
    }

    @Test
    void throwsForNegativeDeposit() {
        BankAccount account = new BankAccount(100);

        assertThrows(IllegalArgumentException.class, () -> account.deposit(-10));
    }

    @Test
    void withdrawsMoney() {
        BankAccount account = new BankAccount(100);

        account.withdraw(40);

        assertEquals(60, account.getBalance());
    }

    @Test
    void withdrawsAllMoney() {
        BankAccount account = new BankAccount(100);

        account.withdraw(100);

        assertEquals(0, account.getBalance());
    }

    @Test
    void throwsForZeroWithdraw() {
        BankAccount account = new BankAccount(100);

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
    }

    @Test
    void throwsForNegativeWithdraw() {
        BankAccount account = new BankAccount(100);

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-10));
    }

    @Test
    void throwsForWithdrawExceedingBalance() {
        BankAccount account = new BankAccount(100);

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(101));
    }
}