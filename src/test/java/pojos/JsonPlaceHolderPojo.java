package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonPlaceHolderPojo {

    /*
    1) Tum key'ler icin private variable'lar olusturuyoruz
    2) Bir tane parametresiz consructer ve butun parametreleri iceren bir constructer olusturuyoruz
    3) getter ve setters'larimizi olusturuyoruz
    4) toString metodumuzu olusturuyoruz
     */

    // 1) Tum key'ler icin private variable'lar olusturuyoruz

    private Integer userId;
    private String title;
    private Boolean completed;

   // 2) Bir tane parametresiz consructer ve butun parametreleri iceren bir constructer olusturuyoruz
    public JsonPlaceHolderPojo(){

    }

    public JsonPlaceHolderPojo(int userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }
    // 3) getter ve setters'larimizi olusturuyoruz
    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getCamplated() {
        return completed;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCamplated(Boolean completed) {
        this.completed = completed;
    }

    // 4) toString metodumuzu olusturuyoruz
    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", camplated='" + completed + '\'' +
                '}';
    }
    // farkli key-value ikililerinin uyusmazligini @JsonIgnoreProperties(ignoreUnknown = true)
    // anotation'ını pojo class'imizin basina yazarak cozebiliriz.


}
