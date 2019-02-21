package com.company;

import com.company.organization.Organization;
import com.company.organization.OrganizationParser;
import com.company.organization.OrganizationReader;
import com.company.person.Person;
import com.company.person.PersonParser;
import com.company.person.PersonReader;
import com.company.user.User;
import com.company.user.UserParser;
import com.company.user.UserReader;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private Model model;
    private ReaderManager readerManager;
    private Writer writer;
    private SendHttpRequest request;
    private String baseUrl = "http://10.78.40.157";

    public Main() {

        model = new Model();
        readerManager = new ReaderManager();
        request = new SendHttpRequest(baseUrl);
        writer = new Writer();

    }

    public static void main(String[] args) {
	    Main m = new Main();
	    m.execute(args[0]);
    }

    private void execute(String filePath) {
        // Manager reads filename and decides which reader parser and writer to use
        //List readerList = readerManager.createReaders();

        OrganizationReader organizationReader = new OrganizationReader(new OrganizationParser());
        model.getOrganisationList().addAll(organizationReader.readFile(filePath + "/organisations.csv"));
        request.postAddressableToServer(model.getOrganisationList());
        writer.write(model.getOrganisationList());

        PersonReader personReader = new PersonReader(new PersonParser(model));
        model.getPersonList().addAll(personReader.readFile(filePath + "/persons.csv"));
        request.postAddressableToServer(model.getPersonList());
        writer.write(model.getPersonList());

        UserReader userReader = new UserReader(new UserParser(model));
        model.getUserList().addAll(userReader.readFile(filePath + "/users.csv"));
        request.postUserToServer(model.getUserList());
        writer.write(model.getUserList());

    }

    public class Model {
        private final List<Organization> organisationList = new ArrayList<>();
        private final List<Person> personList = new ArrayList<>();
        private final List<User> userList = new ArrayList<>();

        public List<Organization> getOrganisationList() {
            return organisationList;
        }

        public List<Person> getPersonList() {
            return personList;
        }

        public List<User> getUserList() { return userList; }
    }

}