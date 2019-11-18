package junitlab.bank;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import junitlab.bank.impl.FirstNationalBank;

public class BankTest {
	FirstNationalBank bank;

	@Before
	public void setUp() {
		bank = new FirstNationalBank();
	}
	
	@Test
	public void testOpenAccount() throws AccountNotExistsException {
		String accountNumber = bank.openAccount();
		long result = bank.getBalance(accountNumber);
		
		Assert.assertNotNull(null, accountNumber);
		Assert.assertEquals(0, result);
	}
	
	@Test
	public void testUniqueAccount() {
		String acc1 = bank.openAccount();
		String acc2 = bank.openAccount();
		
		Assert.assertNotSame(acc1, acc2);
	}
	
	@Test(expected = junitlab.bank.AccountNotExistsException.class)
	public void testInvalidAccount() throws AccountNotExistsException {
		bank.getBalance("abc123");
	}
	
	@Test
	public void testDeposit() throws AccountNotExistsException {
		String acc = bank.openAccount();
		bank.deposit(acc, 2000);
		long actual = bank.getBalance(acc);
		
		Assert.assertEquals(2000, actual);
	}

}
