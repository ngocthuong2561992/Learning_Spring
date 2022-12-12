package com.multidb.repository.mongoLocal;

import com.multidb.repository.mongoLocal.entity.GroceryItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface GroceryItemRepository extends MongoRepository<GroceryItem, String> {

    @Query("{name:'?0'}")
    GroceryItem findItemByName(String name);

//    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    @Query(value="{category:'?0'}")
    List<GroceryItem> findAll(String category);

    public long count();

}
