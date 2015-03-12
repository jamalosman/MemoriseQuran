package com.gre.jamal.memorisequran.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;

import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by jamal on 08/03/15.
 */
public class MongoDBConnectivity {

    private MongoClient mongoClient;
    private DB db;

    public MongoDBConnectivity() {
        try {
            MongoCredential credential = MongoCredential.createMongoCRCredential("jamal", "mq", "bismillah".toCharArray());
            mongoClient = new MongoClient(new ServerAddress("ds055689.mongolab.com", 55689), Arrays.asList(credential));

            db = mongoClient.getDB("mq");
        } catch (MongoException mex) {
            System.out.println("Error connecting to database host");
        } catch (UnknownHostException uhex) {
            System.out.println("Counld not resolve host");
        }
    }

    public JSONObject findUser(String username) {
        DBCollection user = db.getCollection("user");
        BasicDBObject query = new BasicDBObject("username", username);
        JSONObject result = new JSONObject();

        DBCursor cursor = user.find(query);
        Iterator<DBObject> objectIterator;
        if (cursor.hasNext()){
            objectIterator = cursor.iterator();
            String JSONString = "";

        }




        return new JSONObject();
    }
}

