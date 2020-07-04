package com.purplemanatee.sample.dao;




import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.*;
import com.purplemanatee.sample.enumeration.StatusEnum;
import com.purplemanatee.sample.model.Pet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetDbDynamoDBImpl {
    private AmazonDynamoDB client;
    private DynamoDBMapper mapper;


    public PetDbDynamoDBImpl() {
        client = AmazonDynamoDBClientBuilder.standard().withCredentials(new ProfileCredentialsProvider()).build();
        mapper = new DynamoDBMapper(client);
    }

    public void savePet(Pet pet) {
        mapper.save(pet);
    }
    public Pet readPetById(Long id) {
        return mapper.load(Pet.class, id);
    }

    public void deletePet(Pet pet) {
        mapper.delete(pet);
    }

    public List<Pet> readPetByStatus(StatusEnum status) {
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":status", new AttributeValue().withS(status.name()));
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("Status = :status").withIndexName("Status-Name-index").withExpressionAttributeValues(eav);
        return mapper.scan(Pet.class, scanExpression);
    }
}
