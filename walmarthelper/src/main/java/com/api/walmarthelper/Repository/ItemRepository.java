package com.api.walmarthelper.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.walmarthelper.Entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, String>{
    List<Item> findByAisleStartingWith(String prefix);
}
