package com.criteria;

import com.controllers.Sender;
import com.mib.Command;


public abstract class AbstractTask implements Task {



    protected final Command command;
    protected final Sender sender;


    public AbstractTask(Command command) {
        this.command = command;
        this.sender = new Sender();
    }

}
