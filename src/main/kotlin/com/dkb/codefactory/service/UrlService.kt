package com.dkb.codefactory.service

import com.dkb.codefactory.exception.InvalidUrlException
import com.dkb.codefactory.exception.UrlNotFoundException
import com.google.common.hash.Hashing
import org.springframework.stereotype.Service
import java.net.URL
import java.nio.charset.StandardCharsets

@Service
class UrlService(private val cacheService: ICacheService) {

    /**
     * Validate and creates a short url from the url provided.
     */
    fun shortenUrl(url: String): String {
        if (!isValid(url)) {
            throw InvalidUrlException("Malformed URL provided.")
        }
        val id = hash(url);
        cacheService.put(id, url)
        return id
    }

    /**
     * Gets the complete url identified by the key, Throws an exception if
     * it is not found.
     */
    fun getCompleteUrl(shortenUrl: String): String {
        val url = cacheService.get(shortenUrl)
        return url ?: throw UrlNotFoundException("Given url not found in the system")
    }

    private fun hash(url: String): String {
        return Hashing.murmur3_128().hashString(url, StandardCharsets.UTF_8).toString()
    }

    private fun isValid(url: String): Boolean {
        try {
            val u = URL(url) // this would check for the protocol
            u.toURI()
        } catch (ex: Throwable) {
            return false;
        }
        return true;
    }
}