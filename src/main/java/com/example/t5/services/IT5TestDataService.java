package com.example.t5.services;

import com.example.cassandra.model.TestData;

public interface IT5TestDataService {

	TestData getTestDataByUuid(final String uuid);

	void saveTestDataByUuid(final TestData testData);

}
