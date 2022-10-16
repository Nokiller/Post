package ru.netology.domain

class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int,
    val url: String,
    val lyricsId: Int?,
    val albumId: Int?,
    val genreId: Int,
    val date: Int,
    val nosearch: Boolean = false,
    val isHd: Boolean = false
)

class AudioAttachment(val audio: Audio): Attachment("audio")
