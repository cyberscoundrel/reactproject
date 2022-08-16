package dto;

public class InquiryResponseDto extends BaseDto {

    public  String description;
    public String postID;
    public String inquiryResponseID;
    public String userName;
    public String userEmail;
    public String password;
    /*public  String type;
    public  Integer price;
    public  String title;*/

    public InquiryResponseDto(){
        super(null);
    }

    public InquiryResponseDto(String id, String description, String inquiryResponseID, String postID, String userName, String userEmail, String password) {
        super(id);
        this.description = description;
        this.postID = postID;
        this.inquiryResponseID = inquiryResponseID;
        this.userEmail = userEmail;
        this.userName = userName;
        this.password = password;
        /*this.type = type;
        this.price = price;
        this.title = title;*/
    }

    public InquiryResponseDto(String description, String inquiryResponseID, String postID, String userName, String userEmail, String password) {
        super(null);
        this.description = description;
        this.postID = postID;
        this.inquiryResponseID = inquiryResponseID;
        this.userEmail = userEmail;
        this.userName = userName;
        this.password = password;
        /*this.type = type;
        this.price = price;
        this.title = title;*/
    }
}
