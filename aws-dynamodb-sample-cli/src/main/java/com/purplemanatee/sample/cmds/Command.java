package com.purplemanatee.sample.cmds;

import com.purplemanatee.sample.model.DataHolder;
import io.opentracing.Tracer;


public abstract class Command {

    Tracer tracer;
    public Command(Tracer t) {
        this.tracer = t;
    }

    protected Tracer getTracer() {
        return tracer;
    }
    public abstract void executeCommand(DataHolder data) throws Exception;
}
