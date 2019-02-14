package com.company;

import com.company.organization.Organization;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.List;

public class MakeHttpRequest {
    String baseUrl;
    String authToken = "xelion 250f54cf54c973b7a1559fe0f66c2973b7a1559fe0f66c2a8f5bc7d8b8935bf995bd9f22ecfc3c3ad963be03b61742286c9b5f1ec8b2c7f53650b76233fb9086cd2008fa7a5ff93db8414f4950310810838e4b6dedd633d1713dbce1e1ff66318170799d28c1736";
    Gson gson;
    Logger logger = LoggerFactory.getLogger(MakeHttpRequest.class);

    public MakeHttpRequest(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void postAddressableToServer(List<Addressable> objectList) {
        gson = new Gson();
        HttpResponse<JsonNode> jsonResponse;
        String apiUrl = baseUrl + "/api/v1/master/addressables";

        logger.info("Apiurl is: " + apiUrl);

        for(Addressable addressable: objectList) {
            try {
                jsonResponse = Unirest.post(apiUrl).header("Authorization", authToken).body(addressable.toJsonObject()).asJson();
                logger.info("Status code: " + jsonResponse.getStatus() + " status text: " + jsonResponse.getStatusText());
                Type objectType = new TypeToken<Data<Addressable>>() {
                }.getType();
                Data<Addressable> response = gson.fromJson(jsonResponse.getBody().toString(), objectType);
                logger.info(response.getObject().toString());

            } catch (UnirestException e) {
                logger.error(e.toString());
            }
        }
    }

    public String getOrganizationOID(String orgName) {
        gson = new Gson();
        HttpResponse<JsonNode> jsonResponse;
        String organizationOID = "";
        String apiUrl = baseUrl + "/api/v1/master/addressables?name=" + orgName;
        try {
            jsonResponse = Unirest.get(apiUrl).header("Authorization", authToken).asJson();
            logger.info("Status code: " + jsonResponse.getStatus() + " status text: " + jsonResponse.getStatusText());
            Type listType = new TypeToken<DataList<Addressable>>() {}.getType();
            DataList<Addressable> response = gson.fromJson(jsonResponse.getBody().toString(), listType);
            List<Addressable> addressableList = response.getData();
            organizationOID = addressableList.get(0).getOid();

        } catch (UnirestException e) {
            logger.error(e.toString());
        }

        return organizationOID;

    }

}
