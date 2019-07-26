package com.CK;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        FreqStack2 f = new FreqStack2();
        f.push(5);
        f.push(7);
        f.push(5);
        f.push(7);
        f.push(4);
        f.push(5);
//        f.push(3);
        System.out.println(f.pop());
        System.out.println(f.pop());
        System.out.println(f.pop());
        System.out.println(f.pop());
        System.out.println(f.pop());
        System.out.println(f.pop());
//        System.out.println(f.pop());
//        System.out.println(f.pop());
//        System.out.println(f.pop());
//        System.out.println(f.pop());
    }
}


class FreqStack {
    int index; //used to deal with frequency tie. might overflow, but question says we will only have 10^4 push operations
    Map<Integer, Integer> map = new HashMap<>(); //val->freq
    PriorityQueue<int[]> pq; //val, freq, index

    public FreqStack() {
        index = 0;
        pq = new PriorityQueue<>((a, b) -> {
            if (b[1] != a[1]) {
                return b[1] - a[1];
            } else return b[2] - a[2];
        }); //dealing with tie
    }

    //O(logn)
    public void push(int x) {
        map.put(x, map.getOrDefault(x, 0) + 1);
        pq.offer(new int[]{x, map.get(x), index++});
    }

    //O(1)
    public int pop() {
        int x = pq.poll()[0];
        map.put(x, map.get(x) - 1);
        if (map.get(x) == 0) map.remove(x);
        return x;
    }
}

// Map of Stack
class FreqStack2 {
    HashMap<Integer, Integer> freq = new HashMap<>();
    HashMap<Integer, Stack<Integer>> m = new HashMap<>();
    int maxfreq = 0;

    public void push(int x) {
        int f = freq.getOrDefault(x, 0) + 1;
        freq.put(x, f);
        maxfreq = Math.max(maxfreq, f);
        if (!m.containsKey(f)) m.put(f, new Stack<Integer>());
        m.get(f).add(x);
    }

    public int pop() {
        int x = m.get(maxfreq).pop();
        freq.put(x, maxfreq - 1);
        if (m.get(maxfreq).size() == 0) maxfreq--;
        return x;
    }
}