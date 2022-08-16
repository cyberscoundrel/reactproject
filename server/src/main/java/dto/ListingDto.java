package dto;

public class ListingDto extends BaseDto {

  public  String description;
  public  String type;
  public  Integer price;
  public  String title;
  public  String password;
  public  String email;

  public ListingDto(){
    super(null);
  }

  public ListingDto(String id, String description, String type, Integer price, String title, String password, String email) {
    super(id);
    this.description = description;
    this.type = type;
    this.price = price;
    this.title = title;
    this.password = password;
    this.email = email;
  }

  public ListingDto(String description, String type, Integer price, String title, String password, String email) {
    super(null);
    this.description = description;
    this.type = type;
    this.price = price;
    this.title = title;
    this.password = password;
    this.email = email;
  }
}
