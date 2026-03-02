package it.unibo.pps.e1;

import java.util.function.Function;

public class BronzeBankAccount implements BankAccount {

    public static final Function<Integer, Integer> FEE = amount -> amount < 100 ? 0 : 1;
    public static final int MAX_OVERDRAFT = 0;
    private final BankAccount base;

    public BronzeBankAccount(final BankAccount base) {
        this.base = base;
    }

    @Override
    public int getBalance() {
        return this.base.getBalance();
    }

    @Override
    public void deposit(final int amount) {
        this.base.deposit(amount);
    }

    @Override
    public void withdraw(final int amount) {
        if (this.getBalance() < (amount + FEE.apply(amount)) - MAX_OVERDRAFT) {
            throw new IllegalStateException();
        }
        this.base.withdraw(amount + FEE.apply(amount));
    }
}
