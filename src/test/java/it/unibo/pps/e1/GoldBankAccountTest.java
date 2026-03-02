package it.unibo.pps.e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GoldBankAccountTest {

    private BankAccount account;

    @BeforeEach
    void init(){
        final BankAccount base = new CoreBankAccount();
        this.account = new GoldBankAccount(base);
    }

    @Test
    public void testInitiallyEmpty() {
        assertEquals(0, this.account.getBalance());
    }

    @Test
    public void testCanDeposit() {
        this.account.deposit(1000);
        assertEquals(1000, this.account.getBalance());
    }

    @Test
    public void testCanWithdraw() {
        final int deposit = 1000;
        final int withdraw = 200;
        this.account.deposit(deposit);
        this.account.withdraw(withdraw);
        assertEquals(deposit - withdraw, this.account.getBalance());
    }

    @Test
    public void testCannotWithdrawMoreThanAvailable(){
        assertThrows(IllegalStateException.class, () -> this.account.withdraw(1000));
    }

    @Test
    public void testOverdraftWithdrawMoreThanAvailable() {
        this.account.withdraw(GoldBankAccount.MAX_OVERDRAFT);
        assertEquals(-GoldBankAccount.MAX_OVERDRAFT, this.account.getBalance());
        assertThrows(IllegalStateException.class, () -> this.account.withdraw(1));
    }

}
