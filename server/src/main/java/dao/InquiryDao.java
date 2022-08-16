package dao;

import dto.InquiryDto;
import dto.InquiryDto;

import java.util.ArrayList;
import java.util.List;
import mongo.MongoConnection;
import org.bson.Document;
import org.bson.conversions.Bson;

public class InquiryDao extends MongoDao<InquiryDto>
        implements DataAccessObject<InquiryDto> {

    // use lazy loading for the singleton
    private static InquiryDao instance;

    public static InquiryDao getInstance(){
        if(instance == null){
            //instance = new InquiryDao(new MongoConnection());
            instance = new InquiryDao();
        }
        return instance;
    }

    //InquiryDao(MongoConnection connection){
    InquiryDao(){
        //super(connection);
        collection = MongoConnection.getDbConnection().getCollection("inquiries", InquiryDto.class);
    }

    @Override
    public InquiryDto put(InquiryDto item) {
        collection.insertOne(item);
        // fill this out
        return item;
    }

    @Override
    public List<InquiryDto> getItems() {

        List<InquiryDto> l = collection.find().into(new ArrayList<InquiryDto>());
        return l;
        // use .into
        // fill this out
    }

    public List<InquiryDto> get(Bson json_val)
    {
        List<InquiryDto> l = collection.find(json_val).into(new ArrayList<InquiryDto>());
        return l;
    }

    @Override
    public void delete(String id) {
        System.out.println("inquiryid");
        System.out.println(id);



        collection.deleteOne(new Document("inquiryID", id));
        // fill this out;
    }

}