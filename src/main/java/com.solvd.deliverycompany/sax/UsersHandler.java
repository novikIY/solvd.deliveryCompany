package com.solvd.deliverycompany.sax;

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

        if ("customer".equals(qName)) {
            currentUser = new Customer();
        } else if ("courier".equals(qName)) {
            currentUser = new Courier();
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

            case "id":
                if (!value.isEmpty()) {
                    currentUser.setId(Long.parseLong(value));
                }
                break;

            case "firstName":
                currentUser.setFirstName(value);
                break;

            case "lastName":
                currentUser.setLastName(value);
                break;

            case "role":
                currentUser.setRole(value);
                break;

            case "email":
                if (currentUser instanceof Customer) {
                    ((Customer) currentUser).setEmail(value);
                } else if (currentUser instanceof Courier) {
                    ((Courier) currentUser).setEmail(value);
                }
                break;

            case "phone":
                if (currentUser instanceof Customer) {
                    ((Customer) currentUser).setPhone(value);
                } else if (currentUser instanceof Courier) {
                    ((Courier) currentUser).setPhone(value);
                }
                break;

            case "vehicleType":
                if (currentUser instanceof Courier) {
                    ((Courier) currentUser).setVehicleType(value);
                }
                break;

            case "status":
                if (currentUser instanceof Courier) {
                    ((Courier) currentUser).setStatus(value);
                }
                break;

            case "createdAt":
                currentUser.setCreatedAt(value);
                break;

            case "customer":
            case "courier":
                users.add(currentUser);
                break;
        }
    }

    @Override
    public List<User> getResult() {
        return users;
    }
}