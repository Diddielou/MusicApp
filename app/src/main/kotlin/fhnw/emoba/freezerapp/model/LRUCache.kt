package fhnw.emoba.freezerapp.model

import java.util.LinkedHashMap

class LRUCache<key, value> (val maxSize: Int) : LinkedHashMap<key, value>(maxSize, 0.75f, true){
    override fun removeEldestEntry(eldest: MutableMap.MutableEntry<key, value>?): Boolean {
        return size > maxSize
    }
}