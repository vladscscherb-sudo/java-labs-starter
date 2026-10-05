package edu.course.lab02;

public class BankAccount {

    private int balance;

    public BankAccount(int initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("initial balance must not be negative");

        }
        this.balance = initialBalance;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be positive");
        }
        this.balance = this.balance + amount;
    }

    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("insufficient funds");
        }
        this.balance = this.balance - amount;
    }
}
