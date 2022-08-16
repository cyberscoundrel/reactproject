package dao;

import com.mongodb.client.MongoCollection;
import dto.BaseDto;
import mongo.MongoConnection;

public abstract class MongoDao<T extends BaseDto> {
    MongoCollection<T> collection;
    //MongoConnection mongoConnection;

    /*public MongoDao(MongoConnection mongoConnection) {
        this.mongoConnection = mongoConnection;
    }*/
    public MongoDao(){}

}
