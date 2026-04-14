package com.solvd.deliverycompany.dao;

import com.solvd.deliverycompany.model.Courier;
import java.util.List;

public interface ICourierDAO extends IBaseDAO<Courier> {
    List<Courier> getAvailableCouriers();
}