package ru.netology.attachments

class Video (
    override val type: String = "video",
    val videoAttachment: VideoAttachment
) : Attachments

class VideoAttachment (
    val id: Int,                //Идентификатор видеозаписи
    val ownerId: Int,           //Идентификатор владельца видеозаписи
    val title: String,          //Название видезаписи
    val description: String,    //Текст описания видеозаписи
    val duration: Int           //Длительность видеозаписи
)