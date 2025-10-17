package com.example.todo

import com.example.todo.entity.Status
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.get
import com.example.todo.input.TaskStatusInput
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class TaskControllerTest(
    @Autowired private val mockMvc: MockMvc,
    @Autowired private val objectMapper: ObjectMapper
) {
    @Test
    fun testGetAllTasks() {
        mockMvc.get("/api/tasks")
            .andExpect {
                status { isOk() }
                jsonPath("$[0].id") { value(1) }
                jsonPath("$[0].title") { value("資料作成") }
                jsonPath("$[0].startDate") { value("2025-07-01") }
                jsonPath("$[0].dueDate") { value("2025-07-03") }
                jsonPath("$[0].taskCondition") { value("資料ドラフト作成中") }
                jsonPath("$[0].memo") { value("パワポで資料を作成中") }
                jsonPath("$[0].status") { value(1) }

                jsonPath("$[1].id") { value(2) }
                jsonPath("$[1].title") { value("会議準備") }
                jsonPath("$[1].startDate") { value("2025-07-02") }
                jsonPath("$[1].dueDate") { value("2025-07-05") }
                jsonPath("$[1].taskCondition") { value("関連資料収集中") }
                jsonPath("$[1].memo") { value("必要な資料をまとめる") }
                jsonPath("$[1].status") { value(0) }
            }
    }

    @Test
    fun testGetTaskById() {

        mockMvc.get("/api/tasks/1")
            .andExpect {
                status { isOk() }
                jsonPath("$.id") { value(1) }
                jsonPath("$.title") { value("資料作成") }
                jsonPath("$.startDate") { value("2025-07-01") }
                jsonPath("$.dueDate") { value("2025-07-03") }
                jsonPath("$.taskCondition") { value("資料ドラフト作成中") }
                jsonPath("$.memo") { value("パワポで資料を作成中") }
                jsonPath("$.status") { value(1) }
            }
        }

    @Test
    fun testInsertTask() {
        // 登録するタスクデータ
        val newTask = mapOf(
            "title" to "テスト用タスク",
            "startDate" to LocalDate.of(2025, 10, 17).toString(),
            "dueDate" to LocalDate.of(2025, 10, 20).toString(),
            "taskCondition" to "資料作成中",
            "memo" to "テストのためのメモ",
            "status" to 0 // TODO
        )

        mockMvc.post("/api/tasks") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newTask)
        }.andExpect {
            status { isCreated() } // ステータス201を期待
            content {
                contentType(MediaType.APPLICATION_JSON)
            }
            jsonPath("$.title") { value("テスト用タスク") }
            jsonPath("$.startDate") { value("2025-10-17") }
            jsonPath("$.dueDate") { value("2025-10-20") }
            jsonPath("$.taskCondition") { value("資料作成中") }
            jsonPath("$.memo") { value("テストのためのメモ") }
            jsonPath("$.status") { value(0) }
        }
    }

    @Test
    fun testUpdateTaskStatus() {
        val statusInput = TaskStatusInput(status = Status.fromCode(2))

        mockMvc.patch("/api/tasks/2") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(statusInput)
        }.andExpect {
            status { isNoContent() } // 204が返ってくることを確認
        }

        // GETリクエストで更新結果を確認
        val result = mockMvc.get("/api/tasks/2")
            .andExpect { status { isOk() } }
            .andReturn()

        val updatedTaskJson = result.response.contentAsString
        val updatedTask = objectMapper.readTree(updatedTaskJson)

        // ステータスが2に更新されていることを確認
        assertThat(updatedTask.get("status").asInt()).isEqualTo(2)
    }

    @Test
    fun testUpdateTask() {
        mockMvc.put("/api/tasks/4") {
            contentType = MediaType.APPLICATION_JSON
            content = """
                {
                  "title": "テストケース修正",
                  "startDate": "2025-07-04",
                  "dueDate": "2025-07-08",
                  "taskCondition": "完了",
                  "memo": "テストケースを全て修正済み",
                  "status": 2
                }
            """.trimIndent()
        }.andExpect {
            status { isNoContent() }
        }

        mockMvc.get("/api/tasks/4")
            .andExpect {
                status { isOk() }
                jsonPath("$.title") { value("テストケース修正") }
                jsonPath("$.startDate") { value("2025-07-04") }
                jsonPath("$.dueDate") { value("2025-07-08") }
                jsonPath("$.taskCondition") { value("完了") }
                jsonPath("$.memo") { value("テストケースを全て修正済み") }
                jsonPath("$.status") { value(2) }
            }
    }

    @Test
    fun testDeleteTask() {
        mockMvc.perform(delete("/api/tasks/3"))
            .andExpect(status().isNoContent) // 204
    }
}