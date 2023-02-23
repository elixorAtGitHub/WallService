package ru.netology

import ru.netology.attachments.*
import java.lang.RuntimeException

//public inline fun TODO(reason: String): Nothing = throw NotImplementedError("An operation is not implementer: $reason")


data class Post(
    var id: Int,                             //Идентификатор записи
    val ownerId: Int,                       //Идентификатор владельца стены, на которой размещена запись
    val fromId: Int?,                       //Идентификатор автора записи (от чьего имени опубликована запись)
    val friendsOnly: Boolean,               //Запись только для друзей
    val date: Long,                         //Дата
    val text: String,                       //Текст поста
    val views: Int,                         //Количество просмотров
    val likes: Int,                         //Количество лайков
    val isPinned: Boolean,                  //Информация о том, что запись закреплена.
    val isFavourite: Boolean,               //Объект добавлен в закладки у текущего пользователя
    var attachments: Array<Attachments>     //массив вложений
)

class Comment(
    var id: Int,                            //Идентификатор комментария
    val fromId: Int,                        //Идентификатор автора комментария
    val date: Long,                         //Дата
    val text: String,                       //Текст комментария
    var attachments: Array<Attachments>     //массив вложений
)

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var nextId: Int = 0
    private var nextCommentId: Int = 0

    fun createComment(postId: Int, comment: Comment): Comment {     //метод добавления комментария
        if (searchById(postId) != null) {
            comments += comment
            comment.id = ++nextCommentId
            return comments.last()
        }
        throw PostNotFoundException("No post with id $postId")
    }

    fun add(post: Post): Post {                                     //метод добавления поста
        posts += post
        post.id = ++nextId
        return posts.last()
    }

    fun update(post: Post): Boolean {                               //метод обновления поста по его id
        for ((index, postOld) in posts.withIndex()) {
            if (postOld.id == post.id) {
                posts[index] = post
                println("ПОСТ ОБНОВЛЕН")
                return true
            }
        }
        println("ПОСТ НЕ НАЙДЕН")
        return false
    }

    fun likeById(id: Int): Boolean {                                         //метод добавления лайка посту по его id
        for ((index, post) in posts.withIndex()) {
            if (post.id == id) {
                //posts[index] = post.copy(likes = post.likes + 1)
                posts[index] = post.copy(likes = post.likes + 1)
                println("ПОСТ ЛАЙКНУТ")
                return true
            }
        }
        println("ПОСТ НЕ НАЙДЕН")
        return false
    }

    fun searchById(id: Int): Post? {                                       //метод поиска поста по его id
        for (post in posts) {
            if (post.id == id) {
                return post
            }
        }
        return null
    }



}

class PostNotFoundException (message: String) : RuntimeException(message)

fun main() {

    //val emptyStringArray = arrayOf<Attachments>()   //задаем пустой массив вложений поста
    //публикуем посты
    val post1 = Post(0,9,6, true,0,"ПОСТ #1 на стене пользователя 9 автора 6",17,3,true,false, arrayOf<Attachments>())
    WallService.add(post1)
    println(post1)
    val post2 = Post(0,6,4, false,0,"ПОСТ #2 на стене пользователя 6 автора 4",25,10,false,false, arrayOf<Attachments>())
    WallService.add(post2)
    println(post2)
    val post3 = Post(0,4,9, true,0,"ПОСТ #3 на стене пользователя 4 автора 9",68,18,false,false, arrayOf<Attachments>())
    WallService.add(post3)
    println(post3)

    //обновляем пост #2 (для наглядности в поле text добавим слово ОБНОВЛЕННЫЙ)
    val post2Edited = Post(2,6,4, false,0,"ОБНОВЛЕННЫЙ " + "ПОСТ #2 на стене пользователя 6 автора 4",25,10,false,false, arrayOf<Attachments>())
    WallService.update(post2Edited)
    //также лайкнем пост #2
    WallService.likeById(2)
    //теперь отобразим обновленный и лайкнутый пост #2
    println(WallService.searchById(2))

    val commentForPost = Comment(1,59,0,"комментарий", arrayOf<Attachments>())

    // добавление комментария к существующему посту #2
    val id = 10
    val textComment = WallService.createComment(id, commentForPost).text
    val textPost = WallService.searchById(id)?.text
    println("К ПОСТУ: " + textPost + " ДОБАВЛЕН КОММЕНТАРИЙ: " + textComment)

}