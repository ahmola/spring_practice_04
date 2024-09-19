package com.example.prac04.config;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class StringToObjectId {
    public ObjectId convert(String id){
        if(ObjectId.isValid(id)){
            return new ObjectId(id);
        } else {
            throw new IllegalArgumentException("Invalid Object Id : " + id);
        }
    }
}
