package com.revature.controller;

import java.util.List;

import com.revature.service.AccountService;
import com.revature.dto.AddOrEditAccountDTO;
import com.revature.dto.AddOrEditClientDTO;
import com.revature.model.Account;
import com.revature.model.Client;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class AccountController implements Controller {

	private AccountService accountService;

	public AccountController() {
		this.accountService = new AccountService();

	}

	private Handler getAllAccount = (ctx) -> {
		List<Account> accounts = accountService.getAllAccounts();
		ctx.status(200);
		ctx.json(accounts);

	};
	private Handler getAccountFromClient = (ctx) -> {
		String clientId = ctx.pathParam("clientid");
		String upper = ctx.queryParam("amountLessThan");
		String lower = ctx.queryParam("amountGreaterThan");
		List<Account> accountFromClient = accountService.getAllAccountsFromClient(clientId, lower, upper);

		ctx.json(accountFromClient);
	};

	private Handler addAccount = (ctx) -> {
		String clientId = ctx.pathParam("clientid");
		AddOrEditAccountDTO accountToAdd = ctx.bodyAsClass(AddOrEditAccountDTO.class);

		Account addedAccount = accountService.addAccount(clientId, accountToAdd);
		ctx.json(addedAccount);

	};
	private Handler getAccountByIdAndClientId = (ctx) -> {
		String clientId = ctx.pathParam("clientid");
		String accountId = ctx.pathParam("accountid");
		List<Account> accountIdFromClientId = accountService.getAccountByIdAndClientId(clientId, accountId);
		ctx.json(accountIdFromClientId);

	};
	private Handler editAccountByIdAndClientId = (ctx) -> {
		String clientId = ctx.pathParam("clientid");
		String accountId = ctx.pathParam("accountid");
		AddOrEditAccountDTO accountToEdit = ctx.bodyAsClass(AddOrEditAccountDTO.class);
		Account editedAccount = accountService.editAccount(clientId, accountId, accountToEdit);
		ctx.json(editedAccount);

	};
	private Handler deleteAccount = (ctx) -> {
		String clientId = ctx.pathParam("clientid");
		String accountId = ctx.pathParam("accountid");
		accountService.deleteAccount(clientId, accountId);

	};



	@Override
	public void mapEndpoints(Javalin app) {
		app.get("/account", getAllAccount);
		app.get("/client/:clientid/account", getAccountFromClient);
		app.post("/client/:clientid/account", addAccount);
		app.get("/client/:clientid/account/:accountid", getAccountByIdAndClientId);
		app.put("/client/:clientid/account/:accountid", editAccountByIdAndClientId);
		app.delete("/client/:clientid/account/:accountid", deleteAccount);

	}

}
