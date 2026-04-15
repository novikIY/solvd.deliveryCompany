package com.solvd.deliverycompany.sax;

import com.solvd.deliverycompany.model.Address;
import com.solvd.deliverycompany.model.Customer;
import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;

public class AddressHandler extends BaseHandler<List<Address>> {

    private List<Address> addresses = new ArrayList<>();
    private Address currentAddress;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if ("address".equals(qName)) {
            currentAddress = new Address();
        }

        content.setLength(0);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        String value = getValue();

        switch (qName) {

            case "id":
                if (currentAddress != null) {
                    currentAddress.setId(parseLongSafe(value));
                }
                break;

            case "customerId":
                if (currentAddress != null) {
                    Customer c = new Customer();
                    c.setId(parseLongSafe(value));
                    currentAddress.setCustomer(c);
                }
                break;

            case "street":
                if (currentAddress != null) {
                    currentAddress.setStreet(value);
                }
                break;

            case "city":
                if (currentAddress != null) {
                    currentAddress.setCity(value);
                }
                break;

            case "postalCode":
                if (currentAddress != null) {
                    currentAddress.setPostalCode(value);
                }
                break;

            case "country":
                if (currentAddress != null) {
                    currentAddress.setCountry(value);
                }
                break;

            case "address":
                if (currentAddress != null) {
                    addresses.add(currentAddress);
                    currentAddress = null;
                }
                break;
        }
    }

    @Override
    public List<Address> getResult() {
        return addresses;
    }
}