package com.geektext.backend.models;

import com.geektext.backend.UserDataParser;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Document(collection = "users")
public class UserEntity implements UserDetails {
    @Id private String username;
    private String password;

    private String name;

    private String email;

    private List<String> address;

    private List<String> homeAddress;


    private boolean enabled;

    @Field("roles")
    private  List<String> userRoles;

    public UserEntity() {

    }

    public UserEntity(String username, String password, boolean enabled, List<String> userRoles) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.userRoles = userRoles;
    }

    public UserEntity(UserDataParser userData) {
        this.username = userData.getUsername();
        this.password = String.valueOf(userData.getPassword().hashCode());
        this.name = userData.getName();
        this.email = userData.getEmail();
        this.address = userData.getAddress();
        this.homeAddress = userData.getHomeaddress();
        this.enabled = true;
        this.setUserRoles(List.of("user"));
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach(role -> roles.add(new SimpleGrantedAuthority(role)));

        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public List<String> getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(List<String> homeAddress) {
        this.homeAddress = homeAddress;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}