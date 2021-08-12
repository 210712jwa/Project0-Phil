package com.revature.service;

import java.sql.SQLException;
import java.util.List;

import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.ClientDAO;
import com.revature.dao.ClientDAOImpl;
import com.revature.dto.AddOrEditAccountDTO;
import com.revature.exceptions.BadParameterException;
import com.revature.exceptions.ClientNotFoundException;
import com.revature.exceptions.DatabaseException;
import com.revature.model.Account;
import com.revature.model.Client;

public class AccountService {

	private AccountDAO accountDao;
	private ClientDAO clientDao;

	public AccountService() {

		this.accountDao = new AccountDAOImpl();
		this.clientDao = new ClientDAOImpl();
	}

	public List<Account> getAllAccounts() throws DatabaseException {

		try {
			List<Account> accounts = accountDao.getAllAccounts();
			return accounts;
		} catch (SQLException e) {
			throw new DatabaseException("Something went wrong with our DAO operations");

		}

	}

	public List<Account> getAllAccountsFromClient(String clientIdString, String lEnd, String uEnd) throws BadParameterException, DatabaseException, ClientNotFoundException {
		
	
		try {
			
			int clientId = Integer.parseInt(clientIdString);
			int lowerEnd = -1;
			int upperEnd = -1;
			List<Account> accounts = null;
			
			try {
				if (lEnd != null) {
					lowerEnd = Integer.parseInt(lEnd);
				}
			} catch (NumberFormatException e) {
				throw new BadParameterException(uEnd + " was passed in by the user as the id, but it is not an int");
			}
			
			try {
				if (uEnd != null) {
					upperEnd = Integer.parseInt(uEnd);
				}
			} catch (NumberFormatException e) {
				throw new BadParameterException(uEnd + " was passed in by the user as the id, but it is not an int");
			}
			
			if(clientDao.getClientById(clientId) == null) {
				throw new ClientNotFoundException("Client with id " + clientId + " was not found");
			}
			if (lowerEnd != -1 && upperEnd != -1) {
				accounts = accountDao.getAllAccountsInRangeFromClient(clientId, lowerEnd, upperEnd);
			}
			else if (lowerEnd != -1) {
				accounts = accountDao.getGreaterThanAccounts(clientId, lowerEnd);
			}
			else if (upperEnd != -1) {
				accounts = accountDao.getLessThanAccounts(clientId, upperEnd);
			}
			else {
				accounts = accountDao.getAllAccountsFromClient(clientId);
			}
			
			return accounts;
			
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} catch (NumberFormatException e) {
			throw new BadParameterException(clientIdString + " was passed in by the user as the id, " + "but it is not an int");
		}
	}

	public Account addAccount(String clientId, AddOrEditAccountDTO accountToAdd) throws SQLException {

		int id = Integer.parseInt(clientId);
		Account addedAccount = accountDao.addAccount(id, accountToAdd);

		return addedAccount;
	}

	public List<Account> getAccountByIdAndClientId(String clientId, String accountId) throws SQLException {

		int idc = Integer.parseInt(clientId);
		int ida = Integer.parseInt(accountId);

		List<Account> account = accountDao.getAccountByIdAndClientId(idc, ida);
		return account;

	}

	public Account editAccount(String clientId, String accountId, AddOrEditAccountDTO accountToEdit)
			throws SQLException {
		int idc1 = Integer.parseInt(clientId);
		int ida1 = Integer.parseInt(accountId);

		Account editedAccount = accountDao.editAccount(idc1, ida1, accountToEdit);
		return editedAccount;

	}

	public void deleteAccount(String clientId, String accountId) throws SQLException {

		int idc2 = Integer.parseInt(clientId);
		int ida2 = Integer.parseInt(accountId);

		List<Account> account = accountDao.getAccountByIdAndClientId(idc2, ida2);

		accountDao.deleteAccount(ida2);

	}



}
