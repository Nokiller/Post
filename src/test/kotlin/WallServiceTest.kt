import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import ru.netology.domain.*
import kotlin.test.BeforeTest

class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
        println("Before")
    }

    @Test
    fun add_testId() {
        // arrange
        val post = Post(
            ownerId = 2,
            fromId = 312415,
            date = 1663613504,
            text = "Welcome!",
            replyOwnerId = 0,
            replyPostId = 0,
            friendsOnly = false,
            comments = Comments(2),
            copyright = "Vk",
            likes = Likes(1),
            reposts = Reposts(2),
            views = Views(4),
            attachment = AppAttachment(App(1,"Test app","22","24"))

        )
        WallService.add(post)

        // act
        val result = WallService.add(post)

        // assert
        assertEquals(1, result.id)

    }

    @Test
    fun update_returnTrue(){
        // arrange
        val post = Post(
            ownerId = 2,
            fromId = 312415,
            date = 1663613504,
            text = "Welcome!",
            replyOwnerId = 0,
            replyPostId = 0,
            friendsOnly = false,
            comments = Comments(2),
            copyright = "Vk",
            likes = Likes(2),
            reposts = Reposts(2),
            views = Views(4),
            attachment = AppAttachment(App(1,"Test app","22","24"))
        )

        val updatePost = Post(
            id = 1,
            ownerId = 3,
            fromId = 4,
            date = 1663613504,
            text = "Welcome home!",
            replyOwnerId = 0,
            replyPostId = 0,
            friendsOnly = false,
            comments = Comments(2),
            copyright = "Vk",
            likes = Likes(2),
            reposts = Reposts(2),
            views = Views(4),
            attachment = AppAttachment(App(1,"Test app","22","24"))
        )
        WallService.add(post)
        WallService.add(post)

        // act
        val result = WallService.update(updatePost)

        // assert
        assertTrue(result)
    }
    @Test
    fun update_returnFalse(){
        // arrange
        val post = Post(
            ownerId = 3,
            fromId = 4,
            date = 1663613504,
            text = "Welcome home!",
            replyOwnerId = null,
            replyPostId = null,
            friendsOnly = false,
            comments = Comments(1),
            copyright = "Vk",
            likes = Likes(3),
            reposts = Reposts(2),
            views = Views(4),
            attachment = AppAttachment(App(1,"Test app","22","24"))
        )

        val updatePost = Post(
            id = 1,
            ownerId = 3,
            fromId = 4,
            date = 1663613504,
            text = "Welcome home!",
            replyOwnerId = 0,
            replyPostId = 0,
            friendsOnly = false,
            comments = Comments(2),
            copyright = "Vk",
            likes = Likes(2),
            reposts = Reposts(2),
            views = Views(4),
            attachment = GraffitiAttachment(Graffiti(1,2,"22","24"))
        )
        WallService.add(post)

        // act
        val result = WallService.update(updatePost)

        // assert
        assertFalse(result)
    }

    @Test
    fun attachmentType() {
        // arrange
        val post = Post(
            ownerId = 2,
            fromId = 312415,
            date = 1663613504,
            text = "Welcome!",
            replyOwnerId = 0,
            replyPostId = 0,
            friendsOnly = false,
            comments = Comments(2),
            copyright = "Vk",
            likes = Likes(1),
            reposts = Reposts(2),
            views = Views(4),
            attachment = GraffitiAttachment(Graffiti(1,2,"22","24"))
        )

        WallService.add(post)

        // act
        val result = post.attachment.type

        // assert
        assertEquals("Graffiti", result)
    }


}


