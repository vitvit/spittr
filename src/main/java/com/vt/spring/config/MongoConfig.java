package com.vt.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories("com.vt.spring.mongo.repository")
public class MongoConfig extends AbstractMongoConfiguration {

	//@Autowired
	//Environment environment;
	
	@Override
	protected String getDatabaseName() {
		return "OrdersDB";
	}

	@Override
	public Mongo mongo() throws Exception {
		//MongoCredential mongoCredential = MongoCredential.createCredential(environment.getProperty("mongo.username")
		//											, "OrdersDB", environment.getProperty("mongo.password").toCharArray());
		//return new MongoClient(new ServerAddress("localhost", 37017),  Arrays.asList(mongoCredential));
		
		return new MongoClient();
	}
}
