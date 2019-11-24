package com.xx.xchat.utils.route.consistenthash;

import java.util.*;
import java.util.stream.IntStream;

/**
 *
 */
public class TreeMapConsistentHash extends AbstractConsistentHash {
    private TreeMap<Long,String> treeMap = new TreeMap<Long, String>() ;

    /**
     * 虚拟节点数量
     */
    private static final int VIRTUAL_NODE_SIZE = 10 ;

    @Override
    public void add(long key, String value) {
        for (int i = 0; i < VIRTUAL_NODE_SIZE; i++) {
            Long hash = super.hash("vir" + key + i);
            treeMap.put(hash,value);
        }
        treeMap.put(key, value);
    }



    @Override
    public String getFirstNodeValue(String value) {
        long hash = super.hash(value);
        SortedMap<Long, String> last = treeMap.tailMap(hash);
        if (!last.isEmpty()) {
            return last.get(last.firstKey());
        }
        return treeMap.firstEntry().getValue();
    }

    public static void main(String[] args) {
        AbstractConsistentHash map = new TreeMapConsistentHash();
        List<String> ips = Arrays.asList("192.168.1.1", "192.168.1.2", "192.168.1.3", "192.168.1.4", "192.168.1.5");
        map.addList(ips);
        HashMap<String, Integer> hashMap = new HashMap<>();
        IntStream.range(0, 10000).forEach(i -> {
            UUID uuid = UUID.randomUUID();
            String firstNodeValue = map.getFirstNodeValue(uuid.toString());

            Integer in = hashMap.get(firstNodeValue);
            if (in == null) {
                hashMap.put(firstNodeValue, 1);
            } else {
                hashMap.put(firstNodeValue, ++in);
            }
        });

        System.out.println(hashMap);
    }
}
