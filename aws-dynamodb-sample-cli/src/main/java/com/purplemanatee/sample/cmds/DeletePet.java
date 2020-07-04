package com.purplemanatee.sample.cmds;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.purplemanatee.sample.dao.PetDbDynamoDBImpl;
import com.purplemanatee.sample.model.DataHolder;
import com.purplemanatee.sample.model.Pet;

import java.io.FileReader;

import static com.purplemanatee.sample.utils.AssertUtils.notNull;

public class DeletePet implements Command {

    @Override
    public void executeCommand(DataHolder data) throws Exception {
        PetDbDynamoDBImpl dao = new PetDbDynamoDBImpl();
        notNull(data.getId(), "ID must be present to delete pet");
        Pet p = dao.readPetById(data.getId());
        notNull(p, "Pet appears already deleted.  Did you really want that?");
        dao.deletePet(p);

        System.out.println("Pet deleted.");
    }
}
