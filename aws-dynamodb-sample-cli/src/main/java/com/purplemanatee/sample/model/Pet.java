package com.purplemanatee.sample.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.purplemanatee.sample.enumeration.StatusEnum;

import java.util.Date;


@DynamoDBTable(tableName = "Pets")
public class Pet {
    private Long id;
    private String name;
    private String description;
    private String species;
    private String breed;
    private String adoptionStatus;

    @DynamoDBAttribute(attributeName="adoptionStatus")
    public String getAdoptionStatus() {
        return adoptionStatus;
    }

    public void setAdoptionStatus(String adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }


    @DynamoDBAttribute(attributeName="species")
    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }


    @DynamoDBAttribute(attributeName="breed")
    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }

    @DynamoDBAttribute(attributeName="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName="description")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBHashKey(attributeName="id")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
