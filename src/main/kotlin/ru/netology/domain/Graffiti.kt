package ru.netology.domain

class Graffiti(
    val id: Int,
    val ownerId: Int,
    val photo130: String,
    val photo604: String
)

class GraffitiAttachment(val graffiti: Graffiti): Attachment("Graffiti") {


}