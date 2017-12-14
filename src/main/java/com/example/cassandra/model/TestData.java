package com.example.cassandra.model;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Table
public class TestData extends AbstractEntity {

	public static final String TEST_UUID = "1234-1234-1234-1234-1234";

//	@PrimaryKeyColumn(name = "value", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	@Column
	private String value;

	@PrimaryKeyColumn(name = "uuid", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
	private String uuid;

}
