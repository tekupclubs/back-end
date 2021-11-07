package com.example.demo.message;

import lombok.Data;

@Data
public class Responsemessage {
    private String message;


    public Responsemessage() {
        // TODO Auto-generated constructor stub
    }


    public Responsemessage(String message) {
        super();
        this.message = message;
    }

}
