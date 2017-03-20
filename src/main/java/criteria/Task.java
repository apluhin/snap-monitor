package criteria;

import main.Device;

public interface Task {

    Object execute(Device device) throws NullPointerException;

}
