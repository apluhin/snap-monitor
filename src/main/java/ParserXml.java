import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class ParserXml {
    private static final String REPORTS_DIRECTORY = System.getProperty("user.home") + "/reports";

    public static void main(String[] args) throws DocumentException {
        File file = new File(REPORTS_DIRECTORY, "snmp.xml");
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        treeWalk(document);
        // Do something with the document here.
    }

    public static void treeWalk(Document document) {
        Map<String, String> map = new HashMap<String, String>();
        treeWalk(document.getRootElement(), map);
        Device device = new Device(map.get("vendor"), map.get("addres"), new Snmp(map));
    }

    public static void treeWalk(Element element, Map<String, String> map) {

        for (int i = 0, size = element.nodeCount(); i < size; i++) {
            Node node = element.node(i);
            if (node instanceof Element) {
                treeWalk((Element) node, map);
            } else {
                if(!node.getText().startsWith("\n")) {
                    map.put(node.getParent().getName(), node.getText());
                }
            }
        }
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            System.out.println(stringStringEntry.getKey() + " " + stringStringEntry.getValue());
        }

    }
}

