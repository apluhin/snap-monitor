package com.mib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class ParseMib {

    public static List<Command> parseCvs(File file) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return reader.lines()
                .flatMap(Command::get)
                .collect(Collectors.toList());
    }

}
