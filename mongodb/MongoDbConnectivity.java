package com.gre.jamal.memorisequran.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;

import org.json.JSONObject;

import java.net.UnknownHostException;

/**
 * Created by jamal on 08/03/15.
 */
public class MongoDbConnectivity {

    private MongoClient mongoClient;

    public MongoDbConnectivity() {
        try {
            mongoClient = new MongoClient("ds055689.mongolab.com/mq",55689);
        } catch (MongoException mex) {
            System.out.println("Error connecting to database host");
        } catch (UnknownHostException uhex) {
            System.out.println("Counld not resolve host");
        }
    }

    public JSONObject findUser(String username){
        return new JSONObject();
    }

}
