package it.unibo.pps.e1;

public class GoldBankAccount implements BankAccount {

    public static int FEE = 0;
    public static int MAX_OVERDRAFT = 500;
    private final BankAccount base;

    public GoldBankAccount(final BankAccount base) {
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
    public void withdraw(int amount) {
        if (this.getBalance() < (amount + FEE) - MAX_OVERDRAFT) {
            throw new IllegalStateException();
        }
        this.base.withdraw(amount + FEE);
    }
}
