package edu.school21.cinema.filters;

public class NameFilter {

    public static boolean nameFilter(String str) {
        return str.matches("[-\\w.]");
    }
}
