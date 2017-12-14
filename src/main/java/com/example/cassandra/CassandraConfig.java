package com.example.cassandra;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(
		  basePackages = "com.example.cassandra.repo")
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Autowired
	private Environment env;
	
	@Override
	protected String getKeyspaceName() {
		return "testKeySpace";
	}

	@Bean
	public CassandraClusterFactoryBean cluster() {
		CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
		cluster.setContactPoints("172.18.0.2");
		cluster.setPort(9042);
		return cluster;
	}

    @Override
    @Bean
    public CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
        return new BasicCassandraMappingContext();
    }
	
	@Override
	protected List getStartupScripts() {
		return Arrays.asList(env.getProperty("cassandra.startup.script"));
	}

	
//	@Bean
//	public CassandraCustomConversions cassandraCustomConversions() {
//		return new CassandraCustomConversions(Collections.emptyList());
//	}
}
