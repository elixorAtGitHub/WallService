package ru.netology.attachments

class Gift (
    override val type: String = "gift",
    val giftAttachment: GiftAttachment
) : Attachments

class GiftAttachment (
    val id: Int,                //Идентификатор подарка
    val thumb: String           //URL изображения
)