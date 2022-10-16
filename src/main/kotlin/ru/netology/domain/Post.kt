package ru.netology.domain


data class Post(
    var id: Int = 0,
    val ownerId: Int,
    val fromId: Int,
    val date: Int,
    val text: String,
    val replyOwnerId: Int?,
    val replyPostId: Int?,
    val friendsOnly: Boolean,
    var comments: Comments?,
    val copyright: String?,
    var likes: Likes?,
    var reposts: Reposts?,
    var views: Views,
    val attachment: Attachment
)


data class Likes(
    var count: Int = 0,
    var userLikes: Boolean = false,
    var canLike: Boolean = true,
    var canPublish: Boolean = true
)
data class Reposts(
    var count: Int = 0,
    var userReposted: Boolean = false
)
data class Comments(
    var count: Int = 0,
    var canPost: Boolean = true,
    var groupCanPost: Boolean = true,
    var canClose: Boolean = false,
    var canOpen: Boolean =  false
)
data class Views(
    var count: Int = 0
)
