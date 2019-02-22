package com.company;

import com.company.user.User;

import java.util.ArrayList;
import java.util.List;

public class PhoneLineExtension {

    private String baseUrl = "http://10.78.40.157";
    private Main.Model model;
    SendHttpRequest request = new SendHttpRequest(baseUrl);


    public PhoneLineExtension(Main.Model model) {
        this.model = model;
    }

    public void addExtensionsToPhoneLine() {
        String phoneLineOID;
        String userExtensionNumber;
        List<User> users = model.getUserList();
        for(User user : users) {
            userExtensionNumber = user.getExtensionNumber();
            phoneLineOID = request.getPhoneLineOID(user.getOid());
            request.patchPhoneLineExtension(phoneLineOID, userExtensionNumber);
        }
    }
}
