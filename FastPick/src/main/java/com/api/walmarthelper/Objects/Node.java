package com.api.walmarthelper.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {
    private Character vertex;
    private int cost;

    public Node(Character vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }
}
