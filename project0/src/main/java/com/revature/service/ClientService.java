package com.revature.service;

import java.sql.SQLException;
import java.util.List;

import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.ClientDAO;
import com.revature.dao.ClientDAOImpl;
import com.revature.dto.AddOrEditClientDTO;
import com.revature.exceptions.BadParameterException;
import com.revature.exceptions.ClientNotFoundException;
import com.revature.exceptions.DatabaseException;
import com.revature.model.Account;
import com.revature.model.Client;

public class ClientService {

	private ClientDAO clientDao;

	public ClientService() {
		this.clientDao = new ClientDAOImpl();

	}

	public ClientService(ClientDAO mockedDaoObject) {
		this.clientDao = mockedDaoObject;
	}

	public List<Client> getAllClients() throws DatabaseException {
		List<Client> clients;
		try {
			clients = clientDao.getAllClients();

		} catch (SQLException e) {
			throw new DatabaseException("Something went wrong with our DAO operations");
		}
		return clients;
	}

	public Client getClientById(String stringId)
			throws DatabaseException, ClientNotFoundException, BadParameterException {
		try {

			int id = Integer.parseInt(stringId);
			Client client = clientDao.getClientById(id);

			if (client == null) {
				throw new ClientNotFoundException("Client with id " + id + " was not found");
			}
			return client;
		} catch (SQLException e) {
			throw new DatabaseException("Something went wrong with our DAO operations");

		} catch (NumberFormatException e) {
			throw new BadParameterException(
					stringId + " was passed in by the user as the id " + "but it is not an int");
		}

	}

	public Client addClient(AddOrEditClientDTO client) throws DatabaseException, BadParameterException {
		if (client.getName().trim().equals("")) {
			throw new BadParameterException("Client name cannot be blank");

		}

		try {
			Client addedClient = clientDao.addClient(client);
			return addedClient;
		} catch (SQLException e) {
			throw new DatabaseException("Something went wrong with our DAO operations");
		}
	}

	public Client editClient(String stringId, AddOrEditClientDTO client)
			throws DatabaseException, ClientNotFoundException, BadParameterException {

		try {
			int clientId = Integer.parseInt(stringId);

			if (clientDao.getClientById(clientId) == null) {
				throw new ClientNotFoundException("Client with id " + clientId + " was not found");
			}

			Client editedClient = clientDao.editClient(clientId, client);
			return editedClient;
		} catch (SQLException e) {
			throw new DatabaseException("Something went wrong with our DAO operations");
		} catch (NumberFormatException e) {
			throw new BadParameterException(
					stringId + " was passed in by the user as the id " + "but it is not an int");
		}

	}

	public void deleteClient(String clientId) throws ClientNotFoundException, DatabaseException, BadParameterException {

		try {
			int id = Integer.parseInt(clientId);

			Client client = clientDao.getClientById(id);
			if (client == null) {
				throw new ClientNotFoundException(
						"Trying to delete client with an id of " + id + ", but it does not exist");

			}

			clientDao.deleteClient(id);
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} catch (NumberFormatException e) {
			throw new BadParameterException(
					clientId + " was passed in by the user as the id, " + "but it is not an int");
		}

	}
}
