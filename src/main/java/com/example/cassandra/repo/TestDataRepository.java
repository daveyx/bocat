package com.example.cassandra.repo;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.example.cassandra.model.TestData;

public interface TestDataRepository extends CassandraRepository<TestData> {

	public static final String TESTDATA_TABLE = "testdata";
	@Query("select * from " + TESTDATA_TABLE + " where uuid = ?0")
	public TestData findByUuid(final UUID uuid);

//    Iterable<Book> findByTitleAndPublisher(String title, String publisher);

}
