package com.web_dev_494.uGraduate.repo;

import com.web_dev_494.uGraduate.entity.User;


public interface AdminRepo  {

   User findByUsername(String username);

}
