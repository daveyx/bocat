package com.example.cassandra.repo;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.example.cassandra.model.UserAccount;

public interface UserAccountRepository extends CassandraRepository<UserAccount> {

	public UserAccount findByFirstName(String firstName);

	public List<UserAccount> findByLastName(String lastName);

}
