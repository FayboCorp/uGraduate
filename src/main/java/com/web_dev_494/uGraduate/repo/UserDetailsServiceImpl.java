package com.web_dev_494.uGraduate.repo;

import com.web_dev_494.uGraduate.entity.User;
import com.web_dev_494.uGraduate.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminRepo adminRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = adminRepo.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("Unknown Username/Password");
        }
        return new UserSecurity(user);

    }
}
