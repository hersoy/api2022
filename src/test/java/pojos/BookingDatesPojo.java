package pojos;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDatesPojo {

    // 1) Tum key'ler icin private variable'lar olusturuyoruz
    private String checkin;
    private String checkout;

    // 2) Bir tane parametresiz consructer ve butun parametreleri iceren bir constructer olusturuyoruz

    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public BookingDatesPojo() {
    }

    // 3) getter ve setters'larimizi olusturuyoruz

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    // 4) toString metodumuzu olusturuyoruz


    @Override
    public String toString() {
        return "bookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
