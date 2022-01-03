package edu.school21.cinema.services;

public interface BCryptService {
    String encodePassword(String password);

    Boolean checkPassword(String rowPassword, String encodedPassword);
}
