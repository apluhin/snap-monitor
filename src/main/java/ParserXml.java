import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;


public class ParserXml {
    private static final String REPORTS_DIRECTORY = System.getProperty("user.home") + "/reports";

    public static void main(String[] args) throws DocumentException {
        File file = new File(REPORTS_DIRECTORY, "snmp3.xml");
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        treeWalk(document);
    }

    public static List<Device> treeWalk(Document document) {
        List<Device> deviceList = new ArrayList<>();
        Iterator i = document.getRootElement().elementIterator();
        while (i.hasNext()) {
            Map<String, String> map = new HashMap<>();
            treeWalk(((Element) i.next()), map);
            deviceList.add(new Device(map.get("vendor"), map.get("addres"), new Snmp(map)));

        }
        return deviceList;
    }

    public static void treeWalk(Element element, Map<String, String> map) {

        for (int i = 0, size = element.nodeCount(); i < size; i++) {
            Node node = element.node(i);
            if (node instanceof Element) {
                treeWalk((Element) node, map);
            } else {
                if (!node.getText().startsWith("\n")) {
                    map.put(node.getParent().getName(), node.getText());
                }
            }
        }
    }
}

