import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import snmp.Snmp;

import java.io.File;
import java.util.*;


public class ParserXml {
    private final File file;

    public ParserXml(File file) {
        this.file = file;
    }


    public static void main(String[] args) throws DocumentException {
        ParserXml parserXml = new ParserXml(new File(System.getProperty("user.home") + "/reports", "snmp3.xml"));
        List<Device> devices = parserXml.treeWalk();
        System.out.println(devices);

    }

    public List<Device> treeWalk() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        List<Device> deviceList = new ArrayList<>();
        Iterator i = document.getRootElement().elementIterator();
        while (i.hasNext()) {
            Map<String, String> map = new HashMap<>();
            treeWalk(((Element) i.next()), map);
            deviceList.add(new Device(map.get("vendor"), map.get("addres"), new Snmp(map)));

        }
        return deviceList;
    }

    public void treeWalk(Element element, Map<String, String> map) {
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

