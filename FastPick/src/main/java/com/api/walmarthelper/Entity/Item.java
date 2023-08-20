package com.api.walmarthelper.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "itemlist")
@Getter
@Setter
public class Item {
    @Id
    String id;
    String aisle;
    String itemname;
    int quantity;
}
