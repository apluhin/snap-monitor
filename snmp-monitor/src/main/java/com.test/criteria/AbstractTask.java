package com.test.criteria;

import com.test.controllers.Sender;
import com.test.mib.Command;


public abstract class AbstractTask implements Task {



    protected final Command command;
    protected final Sender sender;


    public AbstractTask(Command command) {
        this.command = command;
        this.sender = new Sender();
    }

}
