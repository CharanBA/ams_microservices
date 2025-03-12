package com.optimas.componentservice.config;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrientDBConnectionConfig {

	@Bean
	public ODatabaseSession databaseSession() {
		System.out.println("Starting OrientDB connection...");

		OrientDB orient = new OrientDB("remote:localhost", OrientDBConfig.defaultConfig());
		ODatabaseSession db = orient.open("amsdb", "root", "charanba");

		System.out.println("OrientDB setup completed!");
		return db;
	}
}
