package com.api.walmarthelper.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.walmarthelper.Entity.Item;
import com.api.walmarthelper.Repository.ItemRepository;

@Service
public class WalmartService {
    @Autowired
    ItemRepository ir;

    public void insert(Item item) {
        ir.save(item);
    }

    public List<Item> showList() {
        List<Item> items = new ArrayList<>();
        ir.findAll().forEach(item -> items.add(item));
        return items;
    }

    public String delete(Item item) {
        Item inlist = ir.findById(item.getId()).orElse(null);
        if(inlist == null) return "Item Not Found";
        ir.deleteById(item.getId());
        return "Successful Operation";
    }

    public String update(Item item) {
        Item inlist = ir.findById(item.getId()).orElse(null);
        if(inlist == null) return "Item Not Found";
        ir.save(item);
        return "Successsful Operation";
    }
    
}
