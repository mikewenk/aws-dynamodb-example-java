package com.purplemanatee.sample.cmds;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.purplemanatee.sample.dao.PetDbDynamoDBImpl;
import com.purplemanatee.sample.model.DataHolder;
import com.purplemanatee.sample.model.Pet;
import io.opentracing.Tracer;

import static com.purplemanatee.sample.utils.AssertUtils.notNull;

public class GetPetById  extends  Command {
    public GetPetById(Tracer t) {
        super(t);
    }

    @Override
    public void executeCommand(DataHolder data) throws Exception {
        PetDbDynamoDBImpl dao = new PetDbDynamoDBImpl();
        notNull(data.getId(), "ID must be present to delete pet");
        Pet p = dao.readPetById(data.getId());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(p);
        System.out.println("Pet");
        System.out.println("-----");
        System.out.print(json);
        System.out.println("");
    }
}
