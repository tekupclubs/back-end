package com.example.demo.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Data
@Entity
public class ImageDb {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String type;
    @Lob
    private byte[] image;


    public ImageDb() {
        // TODO Auto-generated constructor stub
    }

    public ImageDb(String name, String type, byte[] image) {
        super();
        this.name = name;
        this.type = type;
        this.image = image;
    }
}
