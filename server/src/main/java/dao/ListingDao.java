package dao;

import dto.ListingDto;

import java.util.ArrayList;
import java.util.List;
import mongo.MongoConnection;
import org.bson.Document;
import org.bson.conversions.Bson;

public class ListingDao extends MongoDao<ListingDto>
        implements DataAccessObject<ListingDto> {

    // use lazy loading for the singleton
    private static ListingDao instance;

    public static ListingDao getInstance(){
        if(instance == null){
            //instance = new ListingDao(new MongoConnection());
            instance = new ListingDao();
        }
        return instance;
    }

    //ListingDao(MongoConnection connection){
    ListingDao(){
        //super(connection);
        System.out.println("listingdao");
        collection = MongoConnection.getDbConnection().getCollection("posts", ListingDto.class);
        //put(new ListingDto("fdsfdaf","dsaffsda","sfadfdsa",8,"fdasfdsa"));
    }

    @Override
    public ListingDto put(ListingDto item) {
        collection.insertOne(item);
        // fill this out
        return item;
    }

    @Override
    public List<ListingDto> getItems() {

        List<ListingDto> l = collection.find().into(new ArrayList<ListingDto>());
        return l;
        // use .into
        // fill this out
    }

    public List<ListingDto> get(Bson json_val)
    {
        List<ListingDto> l = collection.find(json_val).into(new ArrayList<ListingDto>());
        return l;

    }

    @Override
    public void delete(String id) {



        collection.deleteOne(new Document("entryId", id));
        // fill this out;
    }

}
