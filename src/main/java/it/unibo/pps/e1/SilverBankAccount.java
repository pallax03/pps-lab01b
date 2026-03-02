package it.unibo.pps.e1;

public class SilverBankAccount implements BankAccount {

    public static final int FEE = 1;
    public static final int MAX_OVERDRAFT = 0;
    private final BankAccount base;

    SilverBankAccount(final BankAccount base) {
        this.base = base;
    }

    public int getBalance() {
        return base.getBalance();
    }

    @Override
    public void deposit(final int amount) {
        this.base.deposit(amount);
    }

    @Override
    public void withdraw(final int amount) {
        if (this.getBalance() < (amount + FEE) - MAX_OVERDRAFT) {
            throw new IllegalStateException();
        }this.base.withdraw(amount + FEE);
    }
}
