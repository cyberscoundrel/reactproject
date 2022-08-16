package dto;

public class InquiryDto extends BaseDto {

    public  String description;
    public String postID;
    public String inquiryID;
    public String userName;
    public String userEmail;
    public String password;
    /*public  String type;
    public  Integer price;
    public  String title;*/

    public InquiryDto(){
        super(null);
    }

    public InquiryDto(String id, String description, String inquiryID, String postID, String userName, String userEmail, String password) {
        super(id);
        this.description = description;
        this.postID = postID;
        this.inquiryID = inquiryID;
        this.userEmail = userEmail;
        this.userName = userName;
        this.password = password;
        /*this.type = type;
        this.price = price;
        this.title = title;*/
    }

    public InquiryDto(String description, String inquiryID, String postID, String userName, String userEmail, String password) {
        super(null);
        this.description = description;
        this.postID = postID;
        this.inquiryID = inquiryID;
        this.userEmail = userEmail;
        this.userName = userName;
        this.password = password;
        /*this.type = type;
        this.price = price;
        this.title = title;*/
    }
}
