package ru.netology.domain


import kotlin.collections.withIndex

object WallService {
    internal var posts = emptyArray<Post>()
    internal var comments = emptyArray<Comments>()

    internal var reports = emptyArray<ReportComments>()
    private var nextId = 0

    fun clear() {
        posts = emptyArray()
        nextId = 0
    }

    fun add(post: Post): Post {
        post.id = nextId
        nextId += 1
        posts += post.copy()
        return posts.last()
    }

    fun update(postUpd: Post): Boolean {
        var status = false

        for ((index, post) in posts.withIndex()) {
            if (post.id == postUpd.id) {
                posts[index] = postUpd.copy(
                    ownerId = postUpd.ownerId,
                    fromId = postUpd.fromId,
                    text = postUpd.text,
                    replyOwnerId = postUpd.replyOwnerId,
                    replyPostId = postUpd.replyPostId,
                    friendsOnly = postUpd.friendsOnly,
                    commentsOfPost = postUpd.commentsOfPost,
                    copyright = postUpd.copyright,
                    likes = postUpd.likes,
                    reposts = postUpd.reposts,
                    views = postUpd.views,
                    attachment = postUpd.attachment
                )
                status = true
            }

        }
        return status
    }

    fun createComment(postId: Int, comment: Comments): Comments {
        var status = false

        for (post in posts) {
            if (post.id == postId) {
                comments += comment
                status = true
            }
        }
        if (!status) {
            throw PostNotFoundException("The post not found")
        }
        return comments.last()
    }

    fun createReportForComment(commentId: Int, reason: Int, reportComments: ReportComments): ReportComments {
        var result = false
        val listOfReasons = setOf<Int>(0, 1, 2, 3, 4, 5, 6, 8)

        for (comment in comments) {
            if (comment.id == commentId && listOfReasons.contains(reason)) {
                reports += reportComments.copy()
                result = true
            }
        }
        if (!result) {
            throw PostNotFoundException("The post not found or the reason is not correct")
        }
        return reports.last()
    }

}

fun main() {

    val post = Post(
        ownerId = 2,
        fromId = 312415,
        date = 1663613504,
        text = "Welcome!",
        replyOwnerId = 0,
        replyPostId = 0,
        friendsOnly = false,
        commentsOfPost = CommentsOfPost(1),
        copyright = "Vk",
        likes = Likes(1),
        reposts = Reposts(2),
        views = Views(4),
        attachment = GraffitiAttachment(Graffiti(1, 2, "22", "24"))

    )

    val updatePost = Post(
        id = 1,
        ownerId = 5,
        fromId = 4,
        date = 1663613504,
        text = "Welcome home!",
        replyOwnerId = null,
        replyPostId = null,
        friendsOnly = false,
        commentsOfPost = CommentsOfPost(2),
        copyright = "Vk",
        likes = Likes(3),
        reposts = Reposts(2),
        views = Views(4),
        attachment = GraffitiAttachment(Graffiti(1, 2, "22", "24"))
    )

    WallService.add(post)
    WallService.add(post)

    WallService.createComment(0, Comments(0, 1, 1, "test"))
    WallService.createComment(1, Comments(1, 1, 1, "test"))


    println(
        WallService.createReportForComment(
            commentId = 1,
            reason = 6,
            reportComments = ReportComments(1, 1, 0, 0)
        )
    )


}