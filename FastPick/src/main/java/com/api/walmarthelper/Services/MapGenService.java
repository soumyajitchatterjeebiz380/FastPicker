package com.api.walmarthelper.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.walmarthelper.Objects.Node;
import com.api.walmarthelper.Repository.ItemRepository;
@Service
public class MapGenService {
    @Autowired
    ItemRepository ir;
    /*
     * Aisles = [A, B, C, D]
     * int repre = [0,1,2,3]
     * 
     * 
     */
    int[][] neighbors = {

        {0,2,3,4},
        {2,0,5,6},
        {3,5,0,8},
        {4,6,8,0}

    };
    

    public List<String> genMapWithMST(){
        List<String> finalres = new ArrayList<>();
        List<Character> mst = generateMST();
        for(Character aisle : mst) {
            List<String> res = new ArrayList<>();
            ir.findByAisleStartingWith(String.valueOf(aisle)).forEach(item -> res.add(item.getAisle()));
            Collections.sort(res);
            finalres.addAll(res);
        }

        return finalres;
    }

    public List<String> genMapWithTSP(){
        List<String> finalres = new ArrayList<>();
        List<Character> mst = listTSP();
        for(int idx = 0; idx < mst.size(); idx++) {
            Character aisle = mst.get(idx);
            List<String> res = new ArrayList<>();
            ir.findByAisleStartingWith(String.valueOf(aisle)).forEach(item -> res.add(item.getAisle()));
            Collections.sort(res);
            finalres.addAll(res);
        }

        return finalres;
    }

    public List<Character> listTSP() {
        int distance = Integer.MAX_VALUE;
        List<Character> res = new ArrayList<>();
        Set<Character> aislesToAddInMSt = new HashSet<>();
        ir.findAll().forEach(item -> aislesToAddInMSt.add(item.getAisle().charAt(0)));
        List<List<Character>> paths = new ArrayList<>();
        ArrayList<Character> placeholderpath = new ArrayList<>();
        placeholderpath.add('A');
        generatePermutations(aislesToAddInMSt, placeholderpath, paths);
        for(List<Character> path : paths) {
            int currpathdist = 0;
            for(int idx = 1; idx < path.size(); idx++) {
                currpathdist += neighbors[path.get(idx-1) - 'A'][path.get(idx) - 'A'];
            }
            if(currpathdist < distance) {
                distance = currpathdist;
                res = path;
            }
        }
        return res;
    }

    private void generatePermutations(Set<Character> aisles, List<Character> currentPath, List<List<Character>> result) {
        if (currentPath.size() == aisles.size()) {
            result.add(new ArrayList<>(currentPath));
            return;
        }

        for (char c : aisles) {
            if (!currentPath.contains(c)) {
                currentPath.add(c);
                generatePermutations(aisles, currentPath, result);
                currentPath.remove(currentPath.size() - 1);
            }
        }
    }

    public List<Character> generateMST() {
        Set<Character> aislesToAddInMST = new HashSet<>();
        ir.findAll().forEach(item -> aislesToAddInMST.add(item.getAisle().charAt(0)));
        boolean[] visited = new boolean[aislesToAddInMST.size()];
        Arrays.fill(visited, false);
        List<Character> res = generateMSTHelper(aislesToAddInMST, visited);
        return res;
    }

    private  List<Character> generateMSTHelper(Set<Character> aislesToAddInMSt, boolean[] visited) {
        List<Character> res = new ArrayList<>();
        Character[] map = {'A','B','C','D'};
        Comparator<Node> acomp = (Node a, Node b) -> a.getCost() - b.getCost();
        Queue<Node> minheap = new PriorityQueue<>(acomp);
        minheap.add(new Node('A',0));

        while(!minheap.isEmpty()) {
            Node extract = minheap.poll();
            Character vertex = extract.getVertex();
            if(visited[vertex - 'A'] == false) {
                res.add(vertex);
                visited[vertex - 'A'] = true;
            }

            // 0 == [vertex - 'A']  veetex = 'A'
            for(int i = 0; i < 4;i++) {
                if(i == vertex - 'A') {
                    continue;
                }else if(aislesToAddInMSt.contains(map[i]) && visited[map[i] - 'A'] == false){
                    minheap.add(new Node(map[i], neighbors[vertex-'A'][i]));
                }
            }

        }
        return res;
        
    }


}
