package ru.netology

import org.junit.Test

import org.junit.Assert.*
import ru.netology.attachments.Attachments

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
        val emptyStringArray = arrayOf<Attachments>()   //задаем пустой массив вложений поста

        val result = WallService.add(Post(id, ownerId, fromId, friendsOnly, date, text, views, likes, isPinned, isFavourite, emptyStringArray)).id
        assertEquals(1, result)
    }
}

class UpdateTest {


    @Test
    fun update_true() {
        val id = 1
        val ownerId = 9
        val fromId = 6
        val friendsOnly = true
        val date: Long = 0
        val text = "ПОСТ #1 на стене пользователя 9 автора 6"
        val views = 17
        val likes = 3
        val isPinned = true
        val isFavourite = false
        val emptyStringArray = arrayOf<Attachments>()   //задаем пустой массив вложений поста

        WallService.add(Post(id, ownerId, fromId, friendsOnly, date, text, views, likes, isPinned, isFavourite, emptyStringArray))
        val result = WallService.update(Post(id, ownerId, fromId, friendsOnly, date, text, views, likes, isPinned, isFavourite, emptyStringArray))
        assertEquals(true, result)
    }

    @Test
    fun update_false() {
        val id = 1
        val ownerId = 9
        val fromId = 6
        val friendsOnly = true
        val date: Long = 0
        val text = "ПОСТ #1 на стене пользователя 9 автора 6"
        val views = 17
        val likes = 3
        val isPinned = true
        val isFavourite = false
        val emptyStringArray = arrayOf<Attachments>()   //задаем пустой массив вложений поста

        WallService.add(Post(id, ownerId, fromId, friendsOnly, date, text, views, likes, isPinned, isFavourite, emptyStringArray))
        val result = WallService.update(Post(888, ownerId, fromId, friendsOnly, date, text, views, likes, isPinned, isFavourite, emptyStringArray))
        assertEquals(false, result)
    }
}