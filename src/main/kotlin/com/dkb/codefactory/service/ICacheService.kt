package com.dkb.codefactory.service

interface ICacheService {
    fun put(key: String, value: String)
    fun get(key: String): String?
}