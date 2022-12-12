package com.multidb.repository.mongoLocal.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("grocery_items")
public class GroceryItem {

    @Id
    private String id;

    //    @Field(name="NAME")
    private String name;

    private int quantity;
    private String category;
    private String info;

    public GroceryItem(){}

    public GroceryItem(String id, String name, int quantity, String category) {
        super();
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = category;
    }
    public GroceryItem(String id, String name, int quantity, String category, String info) {
        super();
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        this.info = info;
    }
}
