package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.cassandra.model.TestData;
import com.example.cassandra.repo.TestDataRepository;

@Component
public class Startup {
	@Autowired
	private TestDataRepository testDataRepository;
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
//		final TestData testDataExisting = testDataRepository.findByUuid(TestData.TEST_UUID);
//		if (testDataExisting != null) {
//			testDataRepository.deleteAll();
//		}
		final TestData testData = new TestData();
		testData.setValue("1");
		final TestData testDataSaved = testDataRepository.save(testData);
		System.out.println("testdata.uuid=" + testDataSaved.getId());
		System.out.println("-------------> startup finished");
	}
}
