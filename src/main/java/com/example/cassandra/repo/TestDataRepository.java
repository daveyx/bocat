package com.example.cassandra.repo;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.example.cassandra.model.TestData;

public interface TestDataRepository extends CassandraRepository<TestData> {

	public TestData findByUuid(final UUID uuid);

}
