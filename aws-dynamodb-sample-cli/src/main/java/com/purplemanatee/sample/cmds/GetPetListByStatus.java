package com.purplemanatee.sample.cmds;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.purplemanatee.sample.dao.PetDbDynamoDBImpl;
import com.purplemanatee.sample.model.DataHolder;
import com.purplemanatee.sample.model.Pet;
import io.opentracing.Tracer;

import java.util.List;

import static com.purplemanatee.sample.utils.AssertUtils.notNull;

public class GetPetListByStatus extends Command {

    public GetPetListByStatus(Tracer t) {
        super(t);
    }

    @Override
    public void executeCommand(DataHolder data) throws Exception {

        PetDbDynamoDBImpl dao = new PetDbDynamoDBImpl();
        notNull(data.getStatus(), "Status must be present to retreive by status");
        List<Pet> petList = dao.readPetByStatus(data.getStatus());
        System.out.printf("Total of %d pets in db with status %s\n", petList.size(), data.getStatus().name());
        ObjectMapper mapper = new ObjectMapper();
        for (Pet p : petList) {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(p);
            System.out.println("Pet");
            System.out.println("-----");
            System.out.print(json);
            System.out.println("");
        }



    }
}
