package com.example.todo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.get
import org.hamcrest.Matchers.*
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest(
    @Autowired private val mockMvc: MockMvc
) {
    @Test
    fun testGetAllTasks(){
        mockMvc.get("/api/tasks")
            .andExpect{
                status { isOk() }
                jsonPath("$[0].title") { value("資料作成") }
                jsonPath("$[0].startDate") { value("2025-07-01") }
                jsonPath("$[0].dueDate") { value("2025-07-03") }
                jsonPath("$[0].condition") { value("資料ドラフト作成中") }
                jsonPath("$[0].memo") { value("パワポで資料を作成中") }
                jsonPath("$[0].status") { value(1) }

                jsonPath("$[1].title") { value("会議準備") }
                jsonPath("$[1].startDate") { value("2025-07-02") }
                jsonPath("$[1].dueDate") { value("2025-07-05") }
                jsonPath("$[1].condition") { value("関連資料収集中") }
                jsonPath("$[1].memo") { value("必要な資料をまとめる") }
                jsonPath("$[1].status") { value(0) }
            }
    }
}