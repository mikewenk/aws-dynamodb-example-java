package com.purplemanatee.sample;

import com.purplemanatee.sample.cmds.*;
import com.purplemanatee.sample.enumeration.StatusEnum;
import com.purplemanatee.sample.model.DataHolder;
import io.jaegertracing.Configuration;
import io.opentracing.Tracer;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import static com.purplemanatee.sample.utils.AssertUtils.notNull;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        Tracer t = createTracer();

        Options cliOptions = new Options();
        cliOptions.addOption("c", "cmd", true, "What to do (save, getid, getstatus, update)");
        cliOptions.addOption("j", "json", true, "Json file of Object to save");
        cliOptions.addOption("i", "id", true, "Id To query for");
        cliOptions.addOption("s", "status", true, "Status to query for");
        CommandLineParser parser = new DefaultParser();
        CommandLine cli = parser.parse(cliOptions, args);

        String cmdStr = cli.getOptionValue("cmd");

        notNull(cmdStr, "You must include a cmd argument");
        DataHolder data = getDataFromCommandLine(cli);
        Command cmdToExecute;
        switch (cmdStr) {
            case "save":
                cmdToExecute = new SavePet(t);
                break;
            case "getid":
                cmdToExecute = new GetPetById(t);
                break;
            case "getstatus":
                cmdToExecute = new GetPetListByStatus(t);
                break;
            case "delete":
                cmdToExecute = new DeletePet(t);
                break;
            default:
                throw new RuntimeException("command unknown");

        }
        cmdToExecute.executeCommand(getDataFromCommandLine(cli));
    }

    private static Tracer createTracer() {
        Tracer tracer = Configuration.fromEnv("DynamoDBDemoApp").getTracer();
        return tracer;
    }

    private static DataHolder getDataFromCommandLine(CommandLine cli) {
        long id = -1;
        StatusEnum status = null;
        String idStr = cli.getOptionValue("i");
        if (idStr != null && !"".equals(idStr))
            try {
                id = Long.parseLong(idStr);
            } catch (NumberFormatException ignore) {
            }

        String jsonFile = cli.getOptionValue("json");
        String statusStr = cli.getOptionValue("status");

        if (statusStr != null && !"".equals(statusStr))
            status = StatusEnum.valueOf(statusStr);
        return DataHolder.builder().id(id).jsonFile(jsonFile).status(status).build();

    }
}
