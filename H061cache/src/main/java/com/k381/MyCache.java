package com.k381;

import java.lang.ref.SoftReference;
import java.util.*;

public class MyCache<K, V> implements HwCache<K, V> {
    private Map<K, SoftReference<V>> map = new HashMap<>();
    private List<HwListener> listeners = new ArrayList<>();

    @Override
    public void put(K key, V value) {
        SoftReference<V> softValue = new SoftReference<>(value);
        map.put(key, softValue);
        notifyListners(key, value, "put");
    }

    @Override
    public void remove(K key) {
        V value = get(key);
        map.remove(key);
        notifyListners(key, value, "remove");
    }

    @Override
    public V get(K key) {
        V value = null;
        if (map.containsKey(key))
            value = map.get(key).get();
        notifyListners(key, value, "get");
        return value;
    }

    @Override
    public void addListener(HwListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(HwListener listener) {
        listeners.remove(listener);
    }

    private void notifyListners(K key, V value, String method) {
        try {
            listeners.stream().forEach(listener -> listener.notify(key, value, method));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
