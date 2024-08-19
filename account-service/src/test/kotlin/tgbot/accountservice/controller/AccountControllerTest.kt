package tgbot.accountservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

import tgbot.accountservice.BaseTest


@AutoConfigureMockMvc
@SpringBootTest
class AccountControllerTest : BaseTest() {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `Get existed account`() {
        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/account/$chatId")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("chatId").value(account.chatId))
            .andExpect(jsonPath("name").value(account.name))
            .andExpect(jsonPath("email").value(account.email))
            .andExpect(jsonPath("about").value(account.about))
    }

    @Test
    fun `Get nonexistent account`() {
        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/account/$incorrectChatId")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andDo(print())
            .andExpect(status().is4xxClientError)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("timestamp").exists())
            .andExpect(jsonPath("message").exists())
            .andExpect(jsonPath("status").exists())
            .andExpect(jsonPath("code").exists())
    }

    @Test
    fun `Save account`() {
        val objectMapper = ObjectMapper()

        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/account")
                .content(objectMapper.writeValueAsString(accountDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("chatId").value(accountDto.chatId))
            .andExpect(jsonPath("name").value(accountDto.name))
            .andExpect(jsonPath("email").value(accountDto.email))
            .andExpect(jsonPath("about").value(accountDto.about))
    }
}