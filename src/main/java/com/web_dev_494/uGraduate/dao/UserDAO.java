package com.web_dev_494.uGraduate.dao;

import com.web_dev_494.uGraduate.entity.User;

public interface UserDAO {

    User getByUsername(String username);

}
