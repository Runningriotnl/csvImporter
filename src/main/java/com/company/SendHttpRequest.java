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
    private String baseUrl;
    private String authToken = "xelion 76f49dc49dc2bab335f1ca513334d2bab335f1ca513334d8d0f8ab9e3c5bf8e04ae259e7cf10baecd778679cd61493ac7068785030d189a6987bc6f66948bf35fc320c8cec79264f3f83aa5f7764b4f3701abb8de99c42b611e6fb23de733559a3bea4dd55a248b";
    private Gson gson;
    private Logger logger = LoggerFactory.getLogger(MakeHttpRequest.class);

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
            if(addressableList.size() > 0){
                organizationOID = addressableList.get(0).getOid();
            }

        } catch (UnirestException e) {
            logger.error(e.toString());
        }

        return organizationOID;

    }

}
