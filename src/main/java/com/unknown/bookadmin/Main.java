package com.unknown.bookadmin;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {
    public static void main(String[] args) {
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        System.out.println(b.encode("123456"));
    }
}
