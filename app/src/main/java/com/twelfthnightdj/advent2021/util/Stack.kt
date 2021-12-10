package com.twelfthnightdj.advent2021.util

class Stack<T> (seed: List<T> = emptyList(), val filo:Boolean = true) {
    var stuff = mutableListOf<T>()
    init {
        stuff = seed.toMutableList()
    }
    fun push(element: T) {
        if (filo) {
            stuff.add(element)
        } else {
            stuff.add(0, element)
        }
    }
    fun peek(): T? {
        if (stuff.isEmpty()) return null
        return if (filo) {
            stuff.last()
        } else {
            stuff[0]
        }
    }
    fun pop(): T? {
        if (stuff.isEmpty()) return null
        return if (filo) {
            val returning = stuff.last()
            stuff.removeLast()
            returning
        } else {
            val returning = stuff.first()
            stuff.removeFirst()
            returning
        }
    }
    fun size() = stuff.size
    fun isEmpty() = stuff.isEmpty()
}
