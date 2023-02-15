package ru.netology.attachments

class Geolocation (
    override val type: String = "geolocation",
    val geolocationAttachment: GeolocationAttachment
) : Attachments

class GeolocationAttachment (
    val id: Int,                //Идентификатор геолокации
    val latitude: Int,          //Географическая широта
    val longitude: Int,         //Географическая долгота
    val place: String           //Описание места
)