package com.revature.model;

import java.util.Objects;

public class Account {

	private int accountId;
	private String accountType;
	private int balance;
	private int clientId;

	public Account() {
		super();

	}

	public Account(int accountId, String accountType, int balance, int clientId) {
		this.accountId = accountId;
		this.accountType = accountType;
		this.balance = balance;
		this.clientId = clientId;

	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountId, accountType, balance, clientId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return accountId == other.accountId && Objects.equals(accountType, other.accountType)
				&& balance == other.balance && clientId == other.clientId;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountType=" + accountType + ", balance=" + balance
				+ ", clientId=" + clientId + "]";
	}




}
