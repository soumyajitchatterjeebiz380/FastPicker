package com.api.walmarthelper.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.walmarthelper.Entity.Item;
import com.api.walmarthelper.Services.MapGenService;
import com.api.walmarthelper.Services.WalmartService;

@RestController
public class WalmartController {
    @Autowired
    WalmartService ws;
    @Autowired
    MapGenService mp;
    
    @PostMapping("/api.grocery.com/management/list")
    public void insert(@RequestBody Item item ) {
        ws.insert(item);
    }

    @GetMapping("/api.grocery.com/management/list")
    public List<Item> showList() {
        return ws.showList();
    }

    @DeleteMapping("/api.grocery.com/management/list")
    public String delete(@RequestBody Item item) {
        String message = ws.delete(item);
        return message;
    }

    @PutMapping("/api.grocery.com/management/list")
    public String update(@RequestBody Item item) {
       String message =  ws.update(item);
       return message;
    }

    @GetMapping("/api.grocery.com/in-store/services/mst-map")
    public List<String> genMaps() {
        return mp.genMapWithMST();
    }

    @GetMapping("/api.grocery.com/in-store/services/tsp-guide")
    public List<String> genGuide() {
        return mp.genMapWithTSP();
    }
}
