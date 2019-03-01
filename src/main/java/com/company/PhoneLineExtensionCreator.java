package com.company;

import com.company.user.User;

import java.util.List;

public class PhoneLineExtensionCreator {

    private Main.Model model;
    SendHttpRequest request;

    public PhoneLineExtensionCreator(Main.Model model, SendHttpRequest request) {
        this.model = model;
        this.request = request;
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
