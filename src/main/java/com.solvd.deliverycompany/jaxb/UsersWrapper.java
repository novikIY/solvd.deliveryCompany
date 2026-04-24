package com.solvd.deliverycompany.jaxb;

import com.solvd.deliverycompany.model.Courier;
import com.solvd.deliverycompany.model.Customer;
import com.solvd.deliverycompany.model.User;
import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)

public class UsersWrapper {
    @XmlElements({
            @XmlElement(name = "customer", type = Customer.class),
            @XmlElement(name = "courier", type = Courier.class)
    })
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
