package com.example;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cassandra.core.cql.CqlIdentifier;
import org.springframework.cassandra.core.keyspace.DropKeyspaceSpecification;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.core.CassandraAdminOperations;
import org.springframework.stereotype.Component;

import com.example.cassandra.model.TestData;
import com.example.cassandra.repo.TestDataRepository;

@Component
@PropertySource(value = { "classpath:cassandra.properties" })
public class Startup {

	private static final Log LOGGER = LogFactory.getLog(Startup.class);

	@Autowired
	private Environment environment;

	@Autowired
	private CassandraAdminOperations adminTemplate;

	@Autowired
	private TestDataRepository testDataRepository;

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		initCassandra();
		LOGGER.info("-------------> startup finished");
	}


	//
	// ---> private
	//


	private void initCassandra() {
//		adminTemplate.dropTable(CqlIdentifier.cqlId(TestDataRepository.TESTDATA_TABLE));

		adminTemplate.execute(DropKeyspaceSpecification.dropKeyspace("testkeyspace"));
		LOGGER.info("-------------> initCassandra finished");
	}

	private void initTestData() {
		// final TestData testDataExisting =
		// testDataRepository.findByUuid(TestData.TEST_UUID);
		// if (testDataExisting != null) {
		// testDataRepository.deleteAll();
		// }
		final TestData testData = new TestData();
		testData.setValue("1");
		final TestData testDataSaved = testDataRepository.save(testData);
		System.out.println("testdata.uuid=" + testDataSaved.getId());
	}
}
