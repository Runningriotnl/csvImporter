package com.company;

import com.company.organization.Organization;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.List;

public class apiTest {
    String authToken = "";
    Gson gson;
    Logger logger = LoggerFactory.getLogger(apiTest.class);

    @Before
    public void setUp() throws Exception {
        gson = new Gson();
    }

    @Test
    public void postOrganizationTest() throws UnirestException {
        HttpResponse<JsonNode> jsonResponse;

        Organization org = new Organization("Test org3");


        jsonResponse = Unirest.post("http://10.78.40.157/api/v1/master/addressables").header("Authorization", authToken).body(org.toJsonObject()).asJson();
        logger.info("Status code: " + jsonResponse.getStatus() + " status text: " + jsonResponse.getStatusText());
        Type objectType = new TypeToken<Data<Addressable>>() {}.getType();

        Data<Addressable> response = gson.fromJson(jsonResponse.getBody().toString(), objectType);

        logger.info(response.getObject().toString());

    }

    @Test
    public void getOrganizationOID() {
        String orgName = "NieuwToegevoegd";
        String baseUrl = "http://10.78.40.157";
        gson = new Gson();
        HttpResponse<JsonNode> jsonResponse;
        String organizationOID = "";
        String apiUrl = baseUrl + "/api/v1/master/addressables?name=" + orgName;
        try {
            jsonResponse = Unirest.get(apiUrl).header("Authorization", authToken).asJson();
            logger.info("Status code: " + jsonResponse.getStatus() + " status text: " + jsonResponse.getStatusText());
            Type objectType = new TypeToken<DataList<Addressable>>() {}.getType();
            DataList<Addressable> response = gson.fromJson(jsonResponse.getBody().toString(), objectType);
            List<Addressable> addressableList = response.getData();


            organizationOID = addressableList.get(0).getOid();

        } catch (UnirestException e) {
            logger.error(e.toString());
        }

        logger.info(organizationOID);
       // return organizationOID;

    }

}
