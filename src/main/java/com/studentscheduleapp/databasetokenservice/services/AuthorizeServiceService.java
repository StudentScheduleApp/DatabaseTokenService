package com.studentscheduleapp.databasetokenservice.services;

import com.studentscheduleapp.databasetokenservice.properties.GlobalProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeServiceService {
    private GlobalProperties globalProperties;

    public boolean authorize(String token){
        return globalProperties.getServiceToken().equals(token);
    }

}
