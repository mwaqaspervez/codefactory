package com.dkb.codefactory.controller

import com.dkb.codefactory.service.UrlService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class UrlShortnerController(private val urlService: UrlService) {

    @PostMapping("/create-short")
    fun shortenUrl(@RequestBody request: UrlRequest): ResponseEntity<UrlResponse> {
        val result = urlService.shortenUrl(request.url)
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(UrlResponse(result))
    }

    @GetMapping("/{url}")
    fun getCompleteUrl(@PathVariable("url") url: String): ResponseEntity<UrlResponse> {
        val completeUrl = urlService.getCompleteUrl(url)
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(UrlResponse(completeUrl))
    }

    data class UrlRequest(val url: String)
    data class UrlResponse(val url: String?)
}