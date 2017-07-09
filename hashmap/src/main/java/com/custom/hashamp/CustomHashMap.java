package com.custom.hashamp;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by thofi_000 on 7/9/2017.
 * Custom hashmap implementation
 */
@Component
public class CustomHashMap<K,V> {

    private int capacity = 10;
    private Entry<K,V>[] table;

    public CustomHashMap(){
        table = new Entry[capacity];
    }

    /**
     * Method allows to put key-value pair in HashMapCustom.
     * @param newKey
     * @param data
     */
    public void put(K newKey, V data){
        if(newKey==null)
        return;

        int hash=hash(newKey);

        Entry<K,V> newEntry = new Entry<K,V>(newKey, data, null);

        if(table[hash] == null){
            table[hash] = newEntry;
        }else{
            Entry<K,V> previous = null;
            Entry<K,V> current = table[hash];

            while(current != null){
                if(current.key.equals(newKey)){
                    if(previous==null){
                        newEntry.next=current.next;
                        table[hash]=newEntry;
                        return;
                    }
                    else{
                        newEntry.next=current.next;
                        previous.next=newEntry;
                        return;
                    }
                }
                previous=current;
                current = current.next;
            }
            previous.next = newEntry;
        }
    }

    /**
     * Method return null or value corresponding to the key
     * @param key
     */
    public V get(K key){
        int hash = hash(key);
        if(table[hash] == null){
            return null;
        }else{
            Entry<K,V> temp = table[hash];
            while(temp!= null){
                if(temp.key.equals(key))
                    return temp.value;
                temp = temp.next;
            }
            return null;
        }
    }


    /**
     * Method to remove the key value pair
     * @param key
     */
    public boolean remove(K deleteKey){

        int hash=hash(deleteKey);

        if(table[hash] == null){
            return false;
        }else{
            Entry<K,V> previous = null;
            Entry<K,V> current = table[hash];

            while(current != null){
                if(current.key.equals(deleteKey)){
                    if(previous==null){
                        table[hash]=table[hash].next;
                        return true;
                    }
                    else{
                        previous.next=current.next;
                        return true;
                    }
                }
                previous=current;
                current = current.next;
            }
            return false;
        }

    }


    /**
     * Display key value pair
     */
    public List<Entry> display(){

        List<Entry> entries = new LinkedList<>();
        for(int i=0;i<capacity;i++){
            if(table[i]!=null){
                Entry<K, V> entry=table[i];
                while(entry!=null){
                    entries.add(entry);
                    entry=entry.next;
                }
            }
        }

        return entries;

    }

    /**
     * Method to implement hash code for a key
     * @param key
     * @return
     */
    private int hash(K key){
        return Math.abs(key.hashCode()) % capacity;
    }

    public static class Entry<K, V> {
        K key;
        V value;
        Entry<K,V> next;

        public Entry(K key, V value, Entry<K,V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


}
