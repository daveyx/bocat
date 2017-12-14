package com.example.cassandra.model;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class TestData extends AbstractEntity {

	public static final String TEST_UUID = "TEST_UUID";

	@PrimaryKeyColumn(name = "value", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	private String value;

}
