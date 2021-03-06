package toughudder;

public class CreditCardInfoBean {

    private String name;

    private String type;

    private String number;

    private String expiryYear;

    private String expiryMonth;

    private String address;

    private String city;

    private String state;

    private String zip;

    private boolean valid;

    public CreditCardInfoBean() {
    }

    public CreditCardInfoBean(
            String name, String type, String number, String expiryYear,
            String expiryMonth, String address, String city, String state,
            String zip) {

        this.name = name;
        this.type = type;
        this.number = number;
        this.expiryYear = expiryYear;
        this.expiryMonth = expiryMonth;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        validate();
    }

    // Placeholder for future validation
    private void validate() {
        valid = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        validate();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        validate();
    }

    public String getNumber() {
        return number;
    }

    public String getRedactedNumber() {
        int stars = number.length() - 4;
        if (stars > 0) {
            String last4 = number.substring(stars);
            StringBuilder sb = new StringBuilder();
            while (stars > 0) {
                sb.append("*");
                --stars;
            }
            sb.append(last4);
            return sb.toString();
        } else {
            return number;
        }
    }

    public void setNumber(String number) {
        this.number = number;
        validate();
    }

    public String getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(String expiryYear) {
        this.expiryYear = expiryYear;
        validate();
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
        validate();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        validate();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        validate();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        validate();
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
        validate();
    }

    public boolean isValid() {
        return valid;
    }
}
