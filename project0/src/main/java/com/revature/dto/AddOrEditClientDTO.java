package com.revature.dto;

import java.util.Objects;

public class AddOrEditClientDTO {
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddOrEditClientDTO other = (AddOrEditClientDTO) obj;
		return Objects.equals(name, other.name);
	}


	private String name;
	

	
	
	public AddOrEditClientDTO() {
		super();
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	

}
