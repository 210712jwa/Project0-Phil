package com.revature.model;

import java.util.List;
import java.util.Objects;

public class Client {
	
	private int id;
	private String name;
	


	
	public Client() {
		super();
	}
	
	
	public Client(int id, String name) {
		this.id = id;
		this.name = name;
	
		
		
		
	}







	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return id == other.id && Objects.equals(name, other.name);
	}


	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + "]";
	}
















}










