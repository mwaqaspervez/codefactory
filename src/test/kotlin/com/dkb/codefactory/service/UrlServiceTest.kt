package com.dkb.codefactory.service

import com.dkb.codefactory.controller.UrlShortnerController
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
internal class UrlServiceTest(@Autowired val mockMvc: MockMvc) {

    @MockBean
    lateinit var urlService: UrlService
    private val mapper = jacksonObjectMapper()

    @Test
    fun testCreateUrl() {
        val url = "https://myurl.com"
        Mockito.`when`(urlService.shortenUrl(url)).thenReturn("12345")

        mockMvc.perform(
            post("/api/v1/create-short")
                .content(mapper.writeValueAsString(UrlShortnerController.UrlRequest(url)))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.url").isNotEmpty)
    }

    @Test
    fun testCreateUrl_throwsMalformedUrlException() {
        val url = "12345"

        mockMvc.perform(
            post("/api/v1/create-short")
                .content(mapper.writeValueAsString(UrlShortnerController.UrlRequest(url)))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().is2xxSuccessful)
            .andExpect(jsonPath("$.url").isEmpty)
    }

    @Test
    fun testGetUrl() {
        val url = "https://myurl.com"
        Mockito.`when`(urlService.getCompleteUrl("12345")).thenReturn(url)

        mockMvc.perform(
            get("/api/v1/12345")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.url").isNotEmpty)
    }
}