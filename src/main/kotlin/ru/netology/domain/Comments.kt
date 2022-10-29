package ru.netology.domain

class Comments(
    val id: Int,
    val fromId: Int,
    val date: Int,
    val text: String,
    val replyToUser: Int? = null,
    val replyToComment: Int? = null,
    val attachment: Attachment? = null,
    val parents_stack: Array<Comments>? = null,
    val tread: Tread? = null
)

data class Tread(
    val count: Int,
    val items: Array<Comments>,
    val canPost: Boolean,
    val showReplyButton: Boolean,
    val groupsCanPost: Boolean
)

data class ReportComments(
    val authorId: Int,
    val ownerId: Int,
    val commentId: Int,
    val reason: Int
)