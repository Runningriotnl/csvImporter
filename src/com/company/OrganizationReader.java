package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrganizationReader {

    private OrganizationParser orgParser = new OrganizationParser();

    public List<Organization> readFile(String fileName) {
        List<Organization> orgList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // this will read the first line
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    orgList.add(orgParser.parseToOrg(line));
                } catch (OrganizationParserException e) {
                    System.out.println("Skipped");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orgList;
    }

}
