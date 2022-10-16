package ru.netology.domain

class App(
    val id: Int,
    val name: String,
    val photo130: String,
    val photo604: String
)

class AppAttachment(val app: App) : Attachment("app")