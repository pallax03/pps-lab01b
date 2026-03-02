package it.unibo.pps.e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BronzeBankAccountTest {

    private BankAccount account;

    @BeforeEach
    void init(){
        final CoreBankAccount base = new CoreBankAccount();
        this.account = new BronzeBankAccount(base);
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
    public void testCanWithdrawWithFee() {
        int deposit = 1000;
        int withdraw = 200;
        this.account.deposit(deposit);
        this.account.withdraw(withdraw);
        assertEquals(deposit - withdraw - BronzeBankAccount.FEE.apply(withdraw), this.account.getBalance());
    }

    @Test
    public void testCanWithdrawWithoutFee() {
        int deposit = 1000;
        int withdraw = 50;
        this.account.deposit(deposit);
        this.account.withdraw(withdraw);
        assertEquals(deposit - withdraw - BronzeBankAccount.FEE.apply(withdraw), this.account.getBalance());
    }

    @Test
    public void testCannotWithdrawMoreThanAvailable(){
        this.account.deposit(1000);
        assertThrows(IllegalStateException.class, () -> this.account.withdraw(1200));
    }

}
