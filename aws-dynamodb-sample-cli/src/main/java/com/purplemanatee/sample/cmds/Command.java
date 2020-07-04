package com.purplemanatee.sample.cmds;

import com.purplemanatee.sample.model.DataHolder;

public interface Command {

    public void executeCommand(DataHolder data) throws Exception;
}
