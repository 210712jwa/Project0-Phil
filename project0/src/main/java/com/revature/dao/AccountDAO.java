package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.dto.AddOrEditAccountDTO;
import com.revature.model.Account;

public interface AccountDAO {

	public abstract List<Account> getAllAccounts() throws SQLException;

	public abstract List<Account> getAllAccountsFromClient(int clientId) throws SQLException;

	public abstract Account addAccount(int id, AddOrEditAccountDTO accountToAdd) throws SQLException;

	public abstract List<Account> getAccountByIdAndClientId(int clientId, int accountId) throws SQLException;

	public abstract Account editAccount(int idc1, int ida1, AddOrEditAccountDTO accountToEdit) throws SQLException;

	public abstract void deleteAccount(int ida2) throws SQLException;

	public abstract List<Account> getAllAccountsInRangeFromClient(int clientId, int lowerEnd, int upperEnd) throws SQLException;

	public abstract List<Account> getGreaterThanAccounts(int clientId, int lowerEnd) throws SQLException;

	public abstract List<Account> getLessThanAccounts(int clientId, int upperEnd)throws SQLException;





}
