package mongo;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dto.ListingDto;
import java.util.Arrays;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.codecs.pojo.Conventions;

public class MongoConnection {
    private MongoClient mongoClient;
    private MongoDatabase database;

    private static MongoConnection dbConnection;

    public static MongoConnection getDbConnection()
    {
        if(dbConnection == null)
        {
            System.out.println("new connection");
            dbConnection = new MongoConnection();
        }
        return dbConnection;
    }

    // boilerplate connection, don't touch this
    private MongoConnection(){
        PojoCodecProvider provider = PojoCodecProvider.builder().
                conventions(Arrays.asList(Conventions.ANNOTATION_CONVENTION)).automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(provider));
        // open connection
        mongoClient = new MongoClient("localhost", 27017);
        // get ref to database
        database = mongoClient.getDatabase("final");
        database = database.withCodecRegistry(pojoCodecRegistry);
    }

    public MongoCollection getCollection(String name, Class clazz) {
        return database.getCollection(name, clazz);
    }

}
