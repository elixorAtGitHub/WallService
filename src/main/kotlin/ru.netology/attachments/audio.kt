package ru.netology.attachments

class Audio (
    override val type: String = "audio",
    val audioAttachment: AudioAttachment
) : Attachments

class AudioAttachment (
    val id: Int,                //Идентификатор аудиозаписи
    val ownerId: Int,           //Идентификатор владельца аудиозаписи
    val artist: String,         //Исполнитель
    val title: String,          //Название композиции
    val duration: Int           //Длительность композиции
)