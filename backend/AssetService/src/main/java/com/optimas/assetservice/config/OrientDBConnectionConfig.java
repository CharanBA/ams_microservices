package com.optimas.assetservice.config;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrientDBConnectionConfig {

    @Bean
    ODatabaseSession databaseSession() {
		System.out.println("Starting OrientDB connection...");

		try (OrientDB orient = new OrientDB("remote:localhost", OrientDBConfig.defaultConfig())) {
			ODatabaseSession db = orient.open("amsdb", "root", "charanba");

			System.out.println("OrientDB setup completed!");
			return db;
		}
	}
}
