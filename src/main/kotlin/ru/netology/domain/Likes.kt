package ru.netology.domain.ru.netology.domain

class Likes(
    var count: Int = 0,
    var userLikes: Boolean = false,
    var canLike: Boolean = true,
    var canPublish: Boolean = true
)