package com.example.cassandra;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cassandra.core.keyspace.CreateKeyspaceSpecification;
import org.springframework.cassandra.core.keyspace.KeyspaceOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.convert.CassandraConverter;
import org.springframework.data.cassandra.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.CassandraAdminOperations;
import org.springframework.data.cassandra.core.CassandraAdminTemplate;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.mapping.SimpleUserTypeResolver;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@PropertySource(value = { "classpath:cassandra.properties" })
@EnableCassandraRepositories(basePackages = "com.example.cassandra.repo")
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Autowired
	private Environment environment;

	@Override
	protected String getKeyspaceName() {
		return environment.getProperty("cassandra.keyspace");
	}

	@Bean
	@Override
	public CassandraClusterFactoryBean cluster() {
		CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
		cluster.setContactPoints(environment.getProperty("cassandra.contactpoints"));
		cluster.setPort(Integer.parseInt(environment.getProperty("cassandra.port")));
		cluster.setKeyspaceCreations(getKeyspaceCreations());
		return cluster;
	}

	@Bean
	public CassandraMappingContext mappingContext() {

		BasicCassandraMappingContext mappingContext = new BasicCassandraMappingContext();
		mappingContext.setUserTypeResolver(
				new SimpleUserTypeResolver(cluster().getObject(), environment.getProperty("cassandra.keyspace")));

		return mappingContext;
	}

	@Bean
	public CassandraConverter converter() throws ClassNotFoundException {
		return new MappingCassandraConverter(cassandraMapping());
	}

	@Override
	protected List<String> getStartupScripts() {
		return Arrays.asList(environment.getProperty("cassandra.startup.script"));
	}

	@Bean
	public CassandraSessionFactoryBean session() throws ClassNotFoundException {
		final CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
		session.setCluster(cluster().getObject());
		session.setKeyspaceName(environment.getProperty("cassandra.keyspace"));
		session.setConverter(converter());
		session.setSchemaAction(SchemaAction.CREATE_IF_NOT_EXISTS);
		return session;
	}

	// @Bean
	// public CassandraOperations cassandraAdminOperations() throws Exception {
	// return new CassandraTemplate(session().getObject());
	// }

	@Bean
	public CassandraAdminOperations cassandraAdminOperations() throws Exception {
		return new CassandraAdminTemplate(session().getObject(), cassandraConverter());
	}

	//
	// --> protected
	//

	@Override
	protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
		return Collections.singletonList(CreateKeyspaceSpecification.createKeyspace(getKeyspaceName()).ifNotExists(true)
				.with(KeyspaceOption.DURABLE_WRITES, true).withSimpleReplication());
	}
}
