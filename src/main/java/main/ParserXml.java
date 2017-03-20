package main;

import criteria.Task;
import enums.Vendor;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import snmp.SnmpDevice;

import java.io.File;
import java.util.*;


public class ParserXml {


    public static List<Device> treeWalk(File file) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        List<Device> deviceList = new ArrayList<>();
        Iterator i = document.getRootElement().elementIterator();
        while (i.hasNext()) {
            Map<String, String> map = new HashMap<>();
            treeWalk(((Element) i.next()), map);
            Task tesrConnect = Vendor.valueOf(map.get("vendor").toUpperCase()).getTestTask();
            deviceList.add(new Device(map.get("vendor"), map.get("addres"), map.get("name"), new SnmpDevice(map), tesrConnect));

        }
        return deviceList;
    }

    private static void treeWalk(Element element, Map<String, String> map) {
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

