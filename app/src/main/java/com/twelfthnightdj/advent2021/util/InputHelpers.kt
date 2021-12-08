package com.twelfthnightdj.advent2021.util

object InputHelpers {
    fun getContentsFromFile(path: String): String = javaClass.getResource(path)?.readText()?.trim() ?: ""

    fun getListOfStringsFromFile(path: String): List<String> = getContentsFromFile(path)?.trim()?.split("\n") ?: emptyList()
}
