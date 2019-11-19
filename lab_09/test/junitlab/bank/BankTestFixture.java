package junitlab.bank;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import junitlab.bank.impl.FirstNationalBank;
import junitlab.bank.impl.GreatSavingsBank;



public class BankTestFixture {
	Bank bank;
	String acc1;
	String acc2;
	
	@Before
	public void setUp() throws AccountNotExistsException {
//		bank = new FirstNationalBank();
		bank = new GreatSavingsBank();
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
	
	@Test
	public void testClose() throws AccountNotExistsException {
		boolean closed = bank.closeAccount(acc1);
		Assert.assertEquals(closed, false);
			
		String acc = bank.openAccount();
		boolean closed2 = bank.closeAccount(acc);
		Assert.assertEquals(closed2, true);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeDeposit() throws AccountNotExistsException {
		bank.deposit(acc1, -1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testTransferError() throws AccountNotExistsException, NotEnoughFundsException {
		bank.transfer(acc2, acc1, -1);
	}
	
}
