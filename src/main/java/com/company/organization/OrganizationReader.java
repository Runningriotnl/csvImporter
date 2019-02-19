package com.company.organization;

import com.company.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrganizationReader implements Reader {

    private OrganizationParser orgParser;

    public OrganizationReader(OrganizationParser orgParser) {
        this.orgParser = orgParser;
    }

    public List<Organization> readFile(String fileName) {
        List<Organization> orgList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // this will read the first line
            String line;
            int counter = 1;
            while ((line = br.readLine()) != null) {
                try {
                    orgList.add(orgParser.parseToOrg(line));
                } catch (OrganizationParserException e) {
                    System.out.println("Skipped entry on line " + counter);
                    e.printStackTrace();
                }
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orgList;
    }

}
