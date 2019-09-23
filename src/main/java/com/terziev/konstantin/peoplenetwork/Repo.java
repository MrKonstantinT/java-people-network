package com.terziev.konstantin.peoplenetwork;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.google.common.io.Resources;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;

class Repo {

    static List<CSVRecord> loadRecordsFromResource(String resourceName) throws IOException {
        return CSVParser.
            parse(Resources.getResource(resourceName), StandardCharsets.UTF_8, CSVFormat.DEFAULT).
            getRecords();
    }
}
