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

public class SendHttpRequest {
    private String baseUrl;
    private String authToken = "xelion c8c498c498c2bfbbd7d6879408ab32bfbbd7d6879408ab3a87fea02759b6718c579b3820a1ecaaf69f4727e7f923964d476fdb29446710ef926201a375de44b3499d786cbed9e283bc3a18a3b546d26a88662620dd4f0467fdfee082d95ba83092d301e6ef5b79f";
    private Gson gson;
    private Logger logger = LoggerFactory.getLogger(SendHttpRequest.class);

    public SendHttpRequest(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void postAddressableToServer(List<? extends Addressable> objectList) {
        gson = new Gson();
        HttpResponse<JsonNode> jsonResponse;
        String apiUrl = baseUrl + "/api/v1/master/addressables";

        logger.info("Apiurl is: " + apiUrl);

        for(Addressable addressable: objectList) {
            try {
                logger.info(gson.toJson(addressable));
                jsonResponse = Unirest.post(apiUrl).header("Authorization", authToken).body(gson.toJson(addressable)).asJson();
                logger.info("Status code: " + jsonResponse.getStatus() + " status text: " + jsonResponse.getStatusText());
                Type objectType = new TypeToken<Data<Addressable>>() {}.getType();
                logger.info("Raw body response: " + jsonResponse.getBody().toString());
                Data<Addressable> response = gson.fromJson(jsonResponse.getBody().toString(), objectType);
                logger.info(response.getObject().toString());
                addressable.setOid(response.getObject().getOid());

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
