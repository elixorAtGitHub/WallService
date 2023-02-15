package ru.netology

data class Post(
    var id: Int,                //Идентификатор записи
    val ownerId: Int,           //Идентификатор владельца стены, на которой размещена запись
    val fromId: Int?,         //Идентификатор автора записи (от чьего имени опубликована запись)
    val friendsOnly: Boolean,   //Запись только для друзей
    val date: Long,             //Дата
    val text: String,           //Текст поста
    val views: Int,             //Количество просмотров
    val likes: Int,             //Количество лайков
    val isPinned: Boolean,      //Информация о том, что запись закреплена.
    val isFavourite: Boolean,    //Объект добавлен в закладки у текущего пользователя
)

class Likes(
    var count: Int = 0, //счетчик лайков
    var currentUserLike: Boolean = false //поставил ли лайк текущий пользователь
)

object WallService {
    private var posts = emptyArray<Post>()
    private var nextId: Int = 0

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

    fun searchById(id: Int): Post {                                       //метод поиска поста по его id
        for ((index, post) in posts.withIndex()) {
            if (post.id == id) {
                return post
            }
        }
        return TODO()
    }



}

fun main() {

    //публикуем посты
    val post1 = Post(0,9,6, true,0,"ПОСТ #1 на стене пользователя 9 автора 6",17,3,true,false)
    WallService.add(post1)
    println(post1)
    val post2 = Post(0,6,4, false,0,"ПОСТ #2 на стене пользователя 6 автора 4",25,10,false,false)
    WallService.add(post2)
    println(post2)
    val post3 = Post(0,4,9, true,0,"ПОСТ #3 на стене пользователя 4 автора 9",68,18,false,false)
    WallService.add(post3)
    println(post3)

    //отображаем пост #2
    println(WallService.searchById(2))

    //обновляем пост #2 (для наглядности в поле text добавим слово ОБНОВЛЕННЫЙ)
    val (id, ownerId, fromId, friendsOnly, date, text, views, likes, isPinned, isFavourite) = WallService.searchById(2)
    val post2Edited = Post(id, ownerId, fromId, friendsOnly, date, "ОБНОВЛЕННЫЙ " + text,views, likes, isPinned, isFavourite)
    WallService.update(post2Edited)

    //отображаем уже обновленный пост #2
    println(WallService.searchById(2))

    //также лайкнем пост #2
    WallService.likeById(2)

    //теперь отобразим лайкнутый пост #2
    println(WallService.searchById(2))


    //val liked = post.copy(likes = post.likes + 1)
    //val (id, ownerId, _, _, _, text) = post
    //println(liked)
    //println("$id, $ownerId, $text, $ownerId")
}