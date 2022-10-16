package ru.netology.domain

class Photo(
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
    val userId: Int,
    val text: String,
    val date: Int,
    val sizes: Sizes,
    val width: Int,
    val height: Int
)

class PhotoAttachment(val photo: Photo): Attachment("photo")


data class Sizes(
    val type: String,
    val url: String,
    val width: Int,
    val height: Int
)