package ru.netology.domain


class Note(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val text: String,
    val date: Int,
    var comments: Int,
    var readComments: Int?,
    val viewUrl: String,
    var privacyView: Array<String>,
    var privacyComment: Array<String>,
    //var canComment: Array<Int> = arrayOf(0, 1),
    var canComment: Boolean = true,
    val textWiki: String
)
class NoteAttachments(override val type: String, val note: Note): Attachment()