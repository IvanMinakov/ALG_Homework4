package ru.geekbrains.lesson4;

import java.util.Iterator;

public class HashMap<K, V> implements Iterable<HashMap.Entity> {

    private static final int INIT_BUCKET_COUNT = 16;
    private static final double LOAD_FACTOR = 0.5; // 50%
    private int size;

    private Bucket[] buckets;

    class Entity {
        K key;
        V value;
    }

    class Bucket<K, V> {
        Node head;

        class Node {
            Node next;
            Entity nodeValue;
        }

        // ... existing Bucket methods ...

    }

    private int calculateBucketIndex(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    private void recalculate() {
        // ... existing recalculate method ...
    }

    public V put(K key, V value) {
        // ... existing put method ...
    }

    public V get(K key) {
        // ... existing get method ...
    }

    public V remove(K key) {
        // ... existing remove method ...
    }

    @Override
    public Iterator<Entity> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<Entity> {

        private int currentBucketIndex;
        private Bucket.Node currentNode;

        public HashMapIterator() {
            currentBucketIndex = 0;
            currentNode = null;
            findNext();
        }

        private void findNext() {
            while (currentBucketIndex < buckets.length) {
                Bucket<K, V> bucket = buckets[currentBucketIndex];
                if (bucket != null && bucket.head != null) {
                    currentNode = bucket.head;
                    return;
                }
                currentBucketIndex++;
            }
            currentNode = null;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Entity next() {
            Entity entity = currentNode.nodeValue;
            if (currentNode.next != null) {
                currentNode = currentNode.next;
            } else {
                currentBucketIndex++;
                findNext();
            }
            return entity;
        }
    }

    public HashMap() {
        this(INIT_BUCKET_COUNT);
    }

    public HashMap(int initCount) {
        buckets = new Bucket[initCount];
    }
}
