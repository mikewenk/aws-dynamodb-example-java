package com.purplemanatee.sample.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.purplemanatee.sample.enumeration.StatusEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@DynamoDBTable(tableName = "Pets")
public class Pet {
    private Integer Id;
    private LocalDate adoptedDate;
    private String name;
    private String description;
    private StatusEnum status;

    @DynamoDBRangeKey(attributeName = "AdopedDate")

    public LocalDate getAdoptedDate() {
        return adoptedDate;
    }

    public void setAdoptedDate(LocalDate adoptedDate) {
        this.adoptedDate = adoptedDate;
    }

    @DynamoDBAttribute(attributeName="Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName="Status")
    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @DynamoDBAttribute(attributeName="Status")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBHashKey(attributeName="Id")
    public Integer getId() {
        return Id;
    }
    public void setId(Integer Id) {
        this.Id = Id;
    }
}
