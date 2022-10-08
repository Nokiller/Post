package ru.netology.domain

import ru.netology.domain.WallService.posts
import ru.netology.domain.ru.netology.domain.Likes
import kotlin.collections.withIndex
import kotlin.collections.withIndex as withIndex1

data class Post(
    var id: Int = 0,
    val ownerId: Int,
    val fromId: Int,
    val date: Int,
    val text: String,
    val replyOwnerId: Int?,
    val replyPostId: Int?,
    val friendsOnly: Boolean,
    val copyright: String?,
    var likes: Likes?
)


object WallService {
    private var posts = emptyArray<Post>()
    private var nextId = 0

     fun clear() {
        posts = emptyArray()
        nextId = 0
    }

    fun add(post: Post): Post {
        //var lastPostId: Int = posts.lastIndex + 1
       //post.id = lastPostId
        post.id = nextId
        nextId += 1
        posts += post

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
                    copyright = postUpd.copyright,
                    likes = postUpd.likes
                )
                status = true
            }

        }
        return status
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
        copyright = "Vk",
        likes = Likes(1)
    )

    val updatePost = Post(
        id = 1,
        ownerId = 3,
        fromId = 4,
        date = 1663613504,
        text = "Welcome home!",
        replyOwnerId = null,
        replyPostId = null,
        friendsOnly = false,
        copyright = "Vk",
        likes = Likes(3)
    )
    WallService.add(post)
    WallService.add(post)

    WallService.update(updatePost)
   // println(posts[1])
}