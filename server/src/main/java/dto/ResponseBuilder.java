package dto;

import java.util.Date;
import java.util.List;

// Use this to create response objects
public class ResponseBuilder<T extends BaseDto> {

  private Date date;
  private List<T> items;
  private Boolean success;

  public void setDate(Date date) {
    this.date = date;
  }

  public void setItems(List<T> items) {
    this.items = items;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public ResponseDto<T> build(){
    return new ResponseDto<>(date, items, success);
  }
}
