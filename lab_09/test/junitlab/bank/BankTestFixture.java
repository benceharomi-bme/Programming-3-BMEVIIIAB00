package junitlab.bank;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import junitlab.bank.impl.FirstNationalBank;

public class BankTestFixture {

	FirstNationalBank bank;
	String acc1;
	String acc2;
	
	@Before
	public void setUp() throws AccountNotExistsException {
		bank = new FirstNationalBank();
		acc1 = bank.openAccount();
		acc2 = bank.openAccount();
		bank.deposit(acc1, 1500);
		bank.deposit(acc2, 12000);
		
	}
	
	@Test
	public void testTransfer() throws AccountNotExistsException, NotEnoughFundsException {
		bank.transfer(acc2, acc1, 3456);
		
		Assert.assertEquals(4956, bank.getBalance(acc1));
		Assert.assertEquals(8544, bank.getBalance(acc2));
	}
	
	@Test(expected = junitlab.bank.NotEnoughFundsException.class)
	public void testTransferWithoutEnoughFunds() throws AccountNotExistsException, NotEnoughFundsException {
		bank.transfer(acc1, acc2, 3456);
	}
}
