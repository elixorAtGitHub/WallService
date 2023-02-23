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

class CreateCommentTest {

    @Test
    fun createComment() {
        //вначале создаем пост, мы же не можем проверить добавление комментария к несуществующему посту, т.к. всегда будет ошибка из-за отсутствующего поста
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
        WallService.add(Post(id, ownerId, fromId, friendsOnly, date, text, views, likes, isPinned, isFavourite, emptyStringArray)).id

        //теперь создаем комментарий
        val postId = 1                                       //К какому посту добавляем комментарий
        var commentId = 1                                    //Идентификатор комментария
        val commentFromId = 1                                //Идентификатор автора комментария
        val commentDate: Long = 0                            //Дата
        val commentText = "текст комментария"                //Текст комментария
        var commentEmptyStringArray = arrayOf<Attachments>() //массив вложений
        val result = WallService.createComment(postId, Comment(commentId, commentFromId, commentDate, commentText, commentEmptyStringArray)).id

        //проверяем на соответствие ожидаемому результату
        assertEquals(1, result)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        //создаем только комментарий, сам пост не нужен, т.к. мы будем пытаться добавить комментарий к несуществующему посту чтобы выкинуло ошибку
        val postId = 1                                       //К какому посту добавляем комментарий
        var commentId = 1                                    //Идентификатор комментария
        val commentFromId = 1                                //Идентификатор автора комментария
        val commentDate: Long = 0                            //Дата
        val commentText = "текст комментария"                //Текст комментария
        var commentEmptyStringArray = arrayOf<Attachments>() //массив вложений
        WallService.createComment(postId, Comment(commentId, commentFromId, commentDate, commentText, commentEmptyStringArray))
    }
}