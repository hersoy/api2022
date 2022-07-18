package pojos;

public class BookingResponseBodyPojo {

    //1) Tum key'ler icin private variable'lar olusturuyoruz
    private Integer bookingId;
    private BookingPojo booking;

    //2) Bir tane parametresiz consructer ve butun parametreleri iceren bir constructer olusturuyoruz

    public BookingResponseBodyPojo() {
    }

    public BookingResponseBodyPojo(Integer bookingId, BookingPojo booking) {
        this.bookingId = bookingId;
        this.booking = booking;
    }
    //3) getter ve setters'larimizi olusturuyoruz

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingPojo booking) {
        this.booking = booking;
    }


    //4) toString metodumuzu olusturuyoruz


    @Override
    public String toString() {
        return "BookingResponseBodyPojo{" +
                "bookingId=" + bookingId +
                ", booking=" + booking +
                '}';
    }
}
