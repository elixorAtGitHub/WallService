package ru.netology.attachments

class Photo (
    override val type: String = "photo",
    val photoAttachment: PhotoAttachment
) : Attachments

class PhotoAttachment (
    val id: Int,                //Идентификатор фотографии
    val albumId: Int,           //Идентификатор альбома, в котором находится фотография
    val ownerId: Int,           //Идентификатор владельца фотографии
    val userId: Int,            //Идентификатор пользователя, загрузившего фото
    val text: String            //Текст описания фотографии
)