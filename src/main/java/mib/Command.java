package mib;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Command {
    private final String scope;
    private final String name;
    private final String oid;
    private final String typeRequest;

    public Command(String scope, String name, String oid, String typeRequest) {
        this.scope = scope;
        this.name = name;
        this.oid = oid;
        this.typeRequest = typeRequest;
    }

    public Command(List<String> collect) {
        this(collect.get(0).trim(), collect.get(1).trim(), collect.get(2).trim(), collect.get(3).trim());

    }


    public static Stream<Command> get(String s) {
        Command command = new Command(Arrays.asList(s.split(",")));
        return Stream.of(command);
    }

    public String getScope() {
        return scope;
    }

    public String getName() {
        return name;
    }

    public String getOid() {
        return oid;
    }

    public String getTypeRequest() {
        return typeRequest;
    }
}
