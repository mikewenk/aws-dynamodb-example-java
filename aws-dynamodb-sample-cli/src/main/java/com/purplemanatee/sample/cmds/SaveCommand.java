package com.purplemanatee.sample.cmds;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.purplemanatee.sample.dao.PetDbDynamoDBImpl;
import com.purplemanatee.sample.model.DataHolder;
import com.purplemanatee.sample.model.Pet;

import java.io.FileReader;

import static com.purplemanatee.sample.utils.AssertUtils.notNull;

public class SaveCommand implements Command {
    @Override
    public void executeCommand(DataHolder data) throws Exception {
        PetDbDynamoDBImpl dao = new PetDbDynamoDBImpl();
        ObjectMapper mapper = new ObjectMapper();
        notNull(data.getJsonFile(), "Json File argument cant be null for Saving a pet");
        Pet in = mapper.readValue(new FileReader(data.getJsonFile()), Pet.class);
        dao.savePet(in);
    }
}
