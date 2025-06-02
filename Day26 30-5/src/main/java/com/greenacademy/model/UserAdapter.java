package com.greenacademy.model;

import com.greenacademy.entity.Role;
import com.greenacademy.entity.User;
import com.greenacademy.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserAdapter implements UserDetails {
    private final User user;

    public UserAdapter(User userEntity) {
        this.user = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getRoles() != null) {
            for (UserRole ur : user.getRoles()) {
                String roleName = ur.getRole().getRoleName();
                System.out.println("User has role: " + roleName); // ✅ debug
                authorities.add(new SimpleGrantedAuthority(roleName));
            }
        } else {
            System.out.println("User has NO roles"); // ✅ debug
        }
        return authorities;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus() == 1;
    }
}