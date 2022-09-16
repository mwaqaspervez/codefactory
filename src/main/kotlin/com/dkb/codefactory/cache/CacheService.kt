package com.dkb.codefactory.cache

import com.dkb.codefactory.service.ICacheService
import org.springframework.stereotype.Service

@Service
class CacheServiceImp : ICacheService {

    companion object {
        private val cache: HashMap<String, String> = HashMap();
    }

    override fun put(key: String, value: String) {
        cache[key] = value;
    }

    override fun get(key: String): String? {
        return cache[key];
    }
}