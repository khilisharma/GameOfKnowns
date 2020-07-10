package com.gameofknowns.adapter;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import java.util.Collections;
import java.util.List;
import javax.inject.Singleton;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

@AllArgsConstructor
@Builder
@Getter
@Singleton
public class MongoDBAdapter {

  private String ipAddress;
  private int portNumber;
  private String databaseName;

  public MongoDatabase getDatabase() throws RuntimeException {
    try {
      final ServerAddress address = new ServerAddress(ipAddress, portNumber);
      final List<ServerAddress> serverAddresses = Collections.singletonList(address);

      final CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(
          MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries.fromProviders(
              PojoCodecProvider.builder().automatic(true).build()));
      final MongoClient mongoClient = new MongoClient(serverAddresses);
      final MongoDatabase database = mongoClient.getDatabase(databaseName).withCodecRegistry(pojoCodecRegistry);
      return database;
    } catch (RuntimeException ex) {
      throw ex;
    }
  }
}