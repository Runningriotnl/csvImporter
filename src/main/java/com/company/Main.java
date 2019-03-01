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

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.util.List.of;

public class Main {
    private Model model;
    private ReaderManager readerManager;
    private Writer writer;
    private SendHttpRequest request;
    private PhoneLineExtensionCreator phoneLineExtensionCreator;
    private String baseUrl = "http://10.78.40.157";
    private HttpRequestAssembler httpAssembler;

    private String authToken = "xelion 3ff5aac5aac38dba931c74d28ff2d38dba931c74d28ff2d69850390be20d9783c6da1cf7d9259a0cb6b84a8e05185eb107e179479518cca3e90b39415c049945b70ec0f9975d082f9dfc9f0b3b54d8d152749792666ab58caea69f9c0e96793716504da999da64c";

    public Main() {

        model = new Model();
        readerManager = new ReaderManager();
        httpAssembler = new JavaHttpRequest(authToken);
        request = new SendHttpRequest(baseUrl, httpAssembler);
        writer = new Writer();


    }

    public static void main(String[] args) {
	    Main m = new Main();
	    m.execute(args[0]);
    }

    private void execute(String filePath) {
        validateAllFilesPresent(filePath);

        Reader organizationReader = new OrganizationReader(new OrganizationParser());
        model.getOrganisationList().addAll(organizationReader.readFile(filePath + "/organisations.csv"));
        request.postAddressableToServer(model.getOrganisationList());
        writer.write(model.getOrganisationList());

        Reader personReader = new PersonReader(new PersonParser(model));
        model.getPersonList().addAll(personReader.readFile(filePath + "/persons.csv"));
        request.postAddressableToServer(model.getPersonList());
        writer.write(model.getPersonList());

        Reader userReader = new UserReader(new UserParser(model));
        model.getUserList().addAll(userReader.readFile(filePath + "/users.csv"));
        request.postUserToServer(model.getUserList());
        writer.write(model.getUserList());

        PhoneLineExtensionCreator phoneLineExtensionCreator = new PhoneLineExtensionCreator(model, request);
        phoneLineExtensionCreator.addExtensionsToPhoneLine();
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

    private void validateAllFilesPresent(String filePath) {
        Path directoryPath = Paths.get(filePath);
        if (Files.isDirectory(directoryPath)) {
            List<Path> csvPaths = new ArrayList<>();
            csvPaths.add(Paths.get(filePath + "/organisations.csv"));
            csvPaths.add(Paths.get(filePath + "/persons.csv"));
            csvPaths.add(Paths.get(filePath + "/users.csv"));
            csvPaths.add(Paths.get(filePath + "/phonelines.csv"));
            csvPaths.add(Paths.get(filePath + "/phones.csv"));
            for (Path path : csvPaths) {
                if (Files.notExists(path)) {
                    throw new ReaderException("Not all necessary files are present in given filePath.");
                }
            }
        }
    }
}