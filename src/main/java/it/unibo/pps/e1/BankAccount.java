package it.unibo.pps.e1;

public interface BankAccount {


    int getBalance();
    void deposit(final int amount);
    void withdraw(final int amount);

}
