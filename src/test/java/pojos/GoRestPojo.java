package pojos;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoRestPojo {

    //1. Tum keyler privite variable'lar oluturuyoruz
    private Object meta;
    private GoRestDataPojo data;

    //2. Tüm parametrelerle ve paramatresiz constructor olusturuyoruz

    public GoRestPojo() {
    }

    public GoRestPojo(Object meta, GoRestDataPojo data) {
        this.meta = meta;
        this.data = data;
    }

    //3. Getter ve Setters'larımızı olusturuoyurz

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public GoRestDataPojo getData() {
        return data;
    }

    public void setData(GoRestDataPojo data) {
        this.data = data;
    }


    //4. to String methodumuzu olusturuyoruz


    @Override
    public String toString() {
        return "GoRestPojo{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
