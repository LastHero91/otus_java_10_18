package com.k381;

public interface HwListener<K,V> {
    void notify(K key, V value, String action);
}
