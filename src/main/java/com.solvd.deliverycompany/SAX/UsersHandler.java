package com.solvd.deliverycompany.SAX;

import com.solvd.deliverycompany.model.Courier;
import com.solvd.deliverycompany.model.Customer;
import com.solvd.deliverycompany.model.User;
import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;

public class UsersHandler extends BaseHandler<List<User>> {

    private List<User> users = new ArrayList<>();
    private User currentUser;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if ("user".equals(qName)) {
            currentUser = null;
        }

        content.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        content.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        String value = getValue();

        switch (qName) {

            case "role":

                String role = value.toUpperCase();

                if ("CUSTOMER".equals(role)) {
                    currentUser = new Customer();
                } else if ("COURIER".equals(role)) {
                    currentUser = new Courier();
                } else {
                    currentUser = new User();
                }

                currentUser.setRole(role);
                break;

            case "id":
                if (currentUser != null) {
                    currentUser.setId(parseLongSafe(value));
                }
                break;

            case "firstName":
                if (currentUser != null) {
                    currentUser.setFirstName(value);
                }
                break;

            case "lastName":
                if (currentUser != null) {
                    currentUser.setLastName(value);
                }
                break;

            case "email":
                if (currentUser instanceof Customer c) {
                    c.setEmail(value);
                } else if (currentUser instanceof Courier c) {
                    c.setEmail(value);
                }
                break;

            case "phone":
                if (currentUser instanceof Customer c) {
                    c.setPhone(value);
                } else if (currentUser instanceof Courier c) {
                    c.setPhone(value);
                }
                break;

            case "vehicleType":
                if (currentUser instanceof Courier c) {
                    c.setVehicleType(value);
                }
                break;

            case "status":
                if (currentUser instanceof Courier c) {
                    c.setStatus(value);
                }
                break;

            case "createdAt":
                if (currentUser != null) {
                    currentUser.setCreatedAt(value);
                }
                break;

            case "user":
                if (currentUser != null) {
                    users.add(currentUser);
                    currentUser = null;
                }
                break;
        }
    }

    @Override
    public List<User> getResult() {
        return users;
    }
}