package criteria;

import main.Device;

import java.io.IOException;

public interface Critirea {

    Object execute(Device device) throws IOException;


}
