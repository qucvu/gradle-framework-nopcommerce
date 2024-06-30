package testData;

import utilities.DataHelper;

public class AddressCustomerData {
    private DataHelper dataHelper = DataHelper.getDataHelper();

    public static AddressCustomerData getAddressCustomerData() {
        return new AddressCustomerData();
    }

    public final String firstName = dataHelper.getFirstName();
    public final String lastName = dataHelper.getLastName();
    public final String email = dataHelper.getUserEmail();
    public final String companyName = dataHelper.getCompanyName();
    public final String country = "United States";
    public final String state = "California";
    public final String city = dataHelper.getCity();
    public final String address1 = dataHelper.getAddress();
    public final String zipCode = dataHelper.getPostalCode();
    public final String phoneNumber = dataHelper.getPhoneNumber();


}
