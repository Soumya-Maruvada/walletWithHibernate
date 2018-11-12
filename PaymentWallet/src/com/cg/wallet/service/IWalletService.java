package com.cg.wallet.service;

import com.cg.wallet.bean.Customer;
import com.cg.wallet.bean.Wallet;
import com.cg.wallet.exception.WalletException;

public interface IWalletService {
	public boolean validate(Customer customer) throws WalletException;

	public boolean createWallet(Wallet wallet);

	public Wallet showBalance(int walletId);

	public boolean deposit(Wallet wallet, double depositAmt);
	
}
