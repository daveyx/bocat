package com.example.cassandra.model;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserAccount extends AbstractEntity {

	@PrimaryKeyColumn(name = "firstName", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	private String firstName;

	@PrimaryKeyColumn(name = "lastName", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
	private String lastName;

	@PrimaryKeyColumn(name = "email", ordinal = 3, type = PrimaryKeyType.PARTITIONED)
	private String email;

	@PrimaryKeyColumn(name = "password", ordinal = 4, type = PrimaryKeyType.PARTITIONED)
	private String password;

	public UserAccount() {
		super();
	}

	public UserAccount(final String firstName, final String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format("UserAccount[id=%s, firstName='%s', lastName='%s']", id, firstName, lastName);
	}
}
