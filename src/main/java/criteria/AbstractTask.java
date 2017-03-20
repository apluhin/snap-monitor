package criteria;

import main.Sender;
import mib.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractTask implements Task {

    private static final Logger logger = LoggerFactory.getLogger(AbstractTask.class);

    protected final Command command;
    protected final Sender sender;


    public AbstractTask(Command command) {
        this.command = command;
        this.sender = new Sender();
    }



}
