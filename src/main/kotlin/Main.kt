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
    val replyOwnerId: Int,
    val replyPostId: Int,
    val friendsOnly: Boolean,
    val copyright: String,
    var likes: Likes
)


object WallService {
    var posts = emptyArray<Post>()
    private var nextId = 0

     fun clear() {
        posts = emptyArray()
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
            else
                println(posts[index])
        }
        return status
    }
}

fun main() {

}