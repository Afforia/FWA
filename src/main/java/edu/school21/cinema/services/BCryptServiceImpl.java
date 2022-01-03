package edu.school21.cinema.services;

import com.sun.org.apache.bcel.internal.util.BCELifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptServiceImpl implements BCryptService{
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public Boolean checkPassword(String rowPassword, String encodedPassword){
        return bCryptPasswordEncoder.matches(rowPassword, encodedPassword);
    }
}
