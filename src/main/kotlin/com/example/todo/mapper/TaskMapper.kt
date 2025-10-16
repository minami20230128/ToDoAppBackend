package com.example.todo.mapper

import com.example.todo.entity.Task
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Update
import org.apache.ibatis.annotations.Delete

@Mapper
interface TaskMapper {
    @Select("SELECT * from task")
    fun findAll(): Array<Task>;

    @Insert("INSERT INTO task(title, startDate, dueDate, condition, memo, status, createdAt, updatedAt) VALUES (#{title}, #{startDate}, #{dueDate}, #{condition}, #{memo}, #{status}, #{createdAt}, #{updatedAt})")
    fun insertTask(): Long;

    @Update("UPDATE task SET status = #{status} WHERE id = #{id}")
    fun updateTaskStatus(id: Int, task: Task): Long;

    @Update("UPDATE task SET title = #{title}, startDate = #{startDate}, dueDate = #{dueDate}, condition = #{condition}, memo = #{memo}, status = #{status}, createdAt = #{createdAt}, updatedAt = #{updatedAt} WHERE id = #{id}")
    fun updateTask(id: Int, task: Task): Long;

    @Delete("DELETE FROM task WHERE id = #{id}")
    fun deleteTask(id: Long): Long;
}