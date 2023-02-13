package ru.netology

import org.junit.Test

import org.junit.Assert.*

class AddTest {

    @Test
    fun add() {
        val id = 9
        val ownerId = 9
        val fromId = 6
        val friendsOnly = true
        val date: Long = 0
        val text = "ПОСТ #1 на стене пользователя 9 автора 6"
        val views = 17
        val likes = 3
        val isPinned = true
        val isFavourite = false

        val result = WallService.add(Post(id, ownerId, fromId, friendsOnly, date, text, views, likes, isPinned, isFavourite)).id
        assertEquals(1, result)
    }
}