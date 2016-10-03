package criteria;

import main.Sender;
import mib.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractCriteria implements Critirea {

    private static final Logger logger = LoggerFactory.getLogger(AbstractCriteria.class);

    protected final Command command;
    protected final Sender sender;


    public AbstractCriteria(Command command) {
        this.command = command;
        this.sender = new Sender();
    }

}
