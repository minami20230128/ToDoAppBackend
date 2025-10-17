package com.example.todo.mapper

import com.example.todo.entity.Status
import com.example.todo.entity.Task
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Update
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param

@Mapper
interface TaskMapper {
    @Select("SELECT * from task")
    fun findAll(): Array<Task>;

    @Select("SELECT * from task WHERE id = #{id}")
    fun findTaskById(id: Long): Task;

    @Insert("""
    INSERT INTO task(
        title,
        start_date,
        due_date,
        task_condition,
        memo,
        status,
        created_at,
        updated_at
        )
        VALUES (
            #{title},
            #{startDate},
            #{dueDate},
            #{taskCondition},
            #{memo},
            #{status},
            #{createdAt},
            #{updatedAt}
        )
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insertTask(task: Task): Int

    @Update("UPDATE task SET status = #{status} WHERE id = #{id}")
    fun updateTaskStatus(id: Long, status: Int): Int;//更新件数を返す

    @Update("""
        UPDATE task
        SET
            title = #{task.title},
            start_date = #{task.startDate},
            due_date = #{task.dueDate},
            task_condition = #{task.taskCondition},
            memo = #{task.memo},
            status = #{task.status},
            updated_at = CURRENT_TIMESTAMP
        WHERE id = #{id}
    """)
    fun updateTask(id: Long, task: Task): Int

    @Delete("DELETE FROM task WHERE id = #{id}")
    fun deleteTask(id: Long): Int;//消去件数を返す
}