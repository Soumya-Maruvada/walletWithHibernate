package com.cg.wallet.ui;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.cg.wallet.bean.Customer;
import com.cg.wallet.bean.Wallet;
import com.cg.wallet.bean.WalletTransaction;
import com.cg.wallet.exception.WalletException;
import com.cg.wallet.service.IWalletService;
import com.cg.wallet.service.WalletServiceImpl;

public class Client {
	static Scanner scanner = new Scanner(System.in);
	IWalletService walletService = new WalletServiceImpl();
	public static void main(String[] args) throws WalletException, ParseException {
		
		Client client = new Client();
		while (true) {
			System.out.println("========Payment wallet application========");
			System.out.println("1. Create Account ");
			System.out.println("2. Show Balance ");
			System.out.println("3. Deposit ");
			System.out.println("4. Withdraw ");
			System.out.println("5. Fund Transfer ");
			System.out.println("6. Print Transactions ");
			System.out.println("7. Exit ");
			System.out.println(" Choose an option ");
			int option = scanner.nextInt();
			switch (option) {
			case 1:
				client.createAccount();
				break;
			case 2:
				client.showBalance();
				break;
			case 3:
				client.deposit();
				break;
			case 4:
				client.withdraw();
				break;
			case 5:
				client.fundTransfer();
				break;
			case 6:
				client.printTransaction();
				break;
			case 7:
				System.exit(0);
				break;
			default:
				System.err.println("Invalid Option Choose from 1 to 7");
				System.out.println();
				break;

			}
	}
	}
	void createAccount() throws WalletException, ParseException {
		
		
		Customer customer = new Customer();
		
		System.out.println("Enter First name: ");
		String firstName = scanner.next();
		customer.setFirstName(firstName);
		
		System.out.println("Enter Last name: ");
		String lastName = scanner.next();
		customer.setLastName(lastName);
		
		System.out.println("Enter mobile number: ");
		BigInteger mobile = scanner.nextBigInteger();
		customer.setMobile(mobile);
		
		System.out.println("Enter customer email: ");
		String email = scanner.next();
		customer.setEmail(email);
		
		System.out.println("Set your password");
		String password = scanner.next();
		customer.setPassWord(password);
		
		
		Wallet wallet = new Wallet();
		System.out.println("Enter  Account ID");
		int walletId=scanner.nextInt();
		wallet.setWalletId(walletId);
		
		System.out.println("Enter Date of Opening (DD/MM/YYYY)");
		String accDateInput=scanner.next();
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		
		Date dateOfOpening=sdf.parse(accDateInput);
		wallet.setDateOfOpenning(dateOfOpening);
		System.out.println("Enter balance to create account");
		double balance=scanner.nextDouble();
		wallet.setBalance(balance);
		
		wallet.setCustomer(customer);
		
		
		if ( walletService.validate(customer)) {
			
			boolean create = walletService.createWallet(wallet);
			if(create)
			{
				System.out.println("\n\n\t\tCongratulations Customer account has been created successfully...");
			}
			else
			{
				System.out.println("Enter valid Details");
			}
			
		}
		
	}
	
	
	private void showBalance() {
		System.out.println("Enter Wallet ID");
		int walletId=scanner.nextInt();
		
		Wallet wallet=walletService.showBalance(walletId);
		
		if(wallet==null){
			System.out.println("Account Does not exist");
			
		}else {
			double balance=wallet.getBalance();
			System.out.println("Your balance is: " +balance);
		}
		
	}
	private void deposit() {
		System.out.println("Enter Wallet ID");
		int walletId=scanner.nextInt();
		
		Wallet wallet=walletService.showBalance(walletId);
		
		if(wallet==null){
			System.out.println("Account Does not exist");
			
		}else {
			System.out.println("Enter Ammount to deposit.");
			double depositAmt = scanner.nextDouble();
			double balance=wallet.getBalance();
			System.out.println("Your balance is: " +balance);
			
			WalletTransaction walletTransaction=new WalletTransaction();
			walletTransaction.setTransactionType(1);
			walletTransaction.setTransactionDate(new Date());
			walletTransaction.setTransactionAmt(depositAmt);
			walletTransaction.setBeneficiaryAccountBean(null);
			
			wallet.addTransation(walletTransaction);
			boolean deposit = walletService.deposit(wallet, depositAmt);
			if(deposit){
				System.out.println("Deposited Money into Account ");
			}else{
				System.out.println("NOT Deposited Money into Account ");
			}
		}
		
	}
	private void printTransaction() {
		
		
	}
	private void fundTransfer() {
		// TODO Auto-generated method stub
		
	}
	private void withdraw() {
		// TODO Auto-generated method stub
		
	}
	
	
}
