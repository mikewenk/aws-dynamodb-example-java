package com.purplemanatee.sample;

import com.purplemanatee.sample.cmds.*;
import com.purplemanatee.sample.enumeration.StatusEnum;
import com.purplemanatee.sample.model.DataHolder;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        Options cliOptions = new Options();
        cliOptions.addOption("c", "cmd", true, "What to do (save, getid, getstatus, update)");
        cliOptions.addOption("j", "json", true, "Json file of Object to save");
        cliOptions.addOption("i", "id", true, "Id To query for");
        cliOptions.addOption("s", "status", true, "Status to query for");
        CommandLineParser parser = new DefaultParser();
        CommandLine cli = parser.parse(cliOptions, args);

        String cmdStr = cli.getOptionValue("cmd");

        DataHolder data = getDataFromCommandLine(cli);
        Command cmdToExecute;
        switch (cmdStr) {
            case "save":
                cmdToExecute = new SaveCommand();
                break;
            case "getid":
                cmdToExecute = new GetPetById();
                break;
            case "getstatus":
                cmdToExecute = new GetPetListByStatus();
                break;
            case "delete":
                cmdToExecute = new DeletePet();
                break;
            default:
                throw new RuntimeException("command unknown");

        }
        cmdToExecute.executeCommand(getDataFromCommandLine(cli));
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
