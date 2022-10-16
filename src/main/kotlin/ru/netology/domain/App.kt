package ru.netology.domain

class App(
    val id: Int,
    val name: String,
    val photo130: String,
    val photo604: String
)

class AppAttachment(override val type: String, val app: App) : Attachment()