package com.web_dev_494.uGraduate.security;

import com.web_dev_494.uGraduate.entity.User;
import com.web_dev_494.uGraduate.entity.Authorities;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public class UserSecurity extends User implements UserDetails {

    public UserSecurity(User user){
        setAuthorities(user.getAuthorities());
        setId(user.getId());
        setPassword(user.getPassword());
        setUsername(user.getUsername());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
