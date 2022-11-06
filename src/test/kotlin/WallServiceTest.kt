import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import ru.netology.domain.*
import kotlin.test.assertFailsWith

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
            commentsOfPost = CommentsOfPost(2),
            copyright = "Vk",
            likes = Likes(1),
            reposts = Reposts(2),
            views = Views(4),
            attachment = AppAttachment(App(1, "Test app", "22", "24"))

        )
        WallService.add(post)

        // act
        val result = WallService.add(post)

        // assert
        assertEquals(1, result.id)

    }

    @Test
    fun update_returnTrue() {
        // arrange
        val post = Post(
            ownerId = 2,
            fromId = 312415,
            date = 1663613504,
            text = "Welcome!",
            replyOwnerId = 0,
            replyPostId = 0,
            friendsOnly = false,
            commentsOfPost = CommentsOfPost(2),
            copyright = "Vk",
            likes = Likes(2),
            reposts = Reposts(2),
            views = Views(4),
            attachment = AppAttachment(App(1, "Test app", "22", "24"))
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
            commentsOfPost = CommentsOfPost(2),
            copyright = "Vk",
            likes = Likes(2),
            reposts = Reposts(2),
            views = Views(4),
            attachment = AppAttachment(App(1, "Test app", "22", "24"))
        )
        WallService.add(post)
        WallService.add(post)

        // act
        val result = WallService.update(updatePost)

        // assert
        assertTrue(result)
    }

    @Test
    fun update_returnFalse() {
        // arrange
        val post = Post(
            ownerId = 3,
            fromId = 4,
            date = 1663613504,
            text = "Welcome home!",
            replyOwnerId = null,
            replyPostId = null,
            friendsOnly = false,
            commentsOfPost = CommentsOfPost(1),
            copyright = "Vk",
            likes = Likes(3),
            reposts = Reposts(2),
            views = Views(4),
            attachment = AppAttachment(App(1, "Test app", "22", "24"))
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
            commentsOfPost = CommentsOfPost(2),
            copyright = "Vk",
            likes = Likes(2),
            reposts = Reposts(2),
            views = Views(4),
            attachment = GraffitiAttachment(Graffiti(1, 2, "22", "24"))
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
            commentsOfPost = CommentsOfPost(2),
            copyright = "Vk",
            likes = Likes(1),
            reposts = Reposts(2),
            views = Views(4),
            attachment = GraffitiAttachment(Graffiti(1, 2, "22", "24"))
        )

        WallService.add(post)

        // act
        val result = post.attachment.type

        // assert
        assertEquals("Graffiti", result)
    }

    @Test
    fun commentAddedToPost() {
        // arrange
        val post1 = Post(
            id = 2,
            ownerId = 2,
            fromId = 312415,
            date = 1663613504,
            text = "Welcome!",
            replyOwnerId = 0,
            replyPostId = 0,
            friendsOnly = false,
            commentsOfPost = CommentsOfPost(2),
            copyright = "Vk",
            likes = Likes(1),
            reposts = Reposts(2),
            views = Views(4),
            attachment = GraffitiAttachment(Graffiti(1, 2, "22", "24"))
        )

        WallService.add(post1)
        WallService.createComment(post1.id, Comments(id = 3, fromId = 2, date = 1, text = "test"))

        // act
        val result = WallService.comments[0].id

        // assert
        assertEquals(3, result)
    }

    @Test
    fun commentAddingProcessShouldThrow1() {
        // arrange
        val post = Post(
            ownerId = 2,
            fromId = 312415,
            date = 1663613504,
            text = "Welcome!",
            replyOwnerId = 0,
            replyPostId = 0,
            friendsOnly = false,
            commentsOfPost = CommentsOfPost(2),
            copyright = "Vk",
            likes = Likes(1),
            reposts = Reposts(2),
            views = Views(4),
            attachment = GraffitiAttachment(Graffiti(1, 2, "22", "24"))
        )

        WallService.add(post)


        // act
        val exception = assertFailsWith<RuntimeException> {
            WallService.createComment(postId = 1, Comments(1, 1, 1, "test"))
        }

        // assert
        assertEquals("The post not found", exception.message)
    }

    @Test(expected = PostNotFoundException::class)
    fun commentAddingProcessShouldThrow2() {

        val post = Post(
            ownerId = 2,
            fromId = 312415,
            date = 1663613504,
            text = "Welcome!",
            replyOwnerId = 0,
            replyPostId = 0,
            friendsOnly = false,
            commentsOfPost = CommentsOfPost(2),
            copyright = "Vk",
            likes = Likes(1),
            reposts = Reposts(2),
            views = Views(4),
            attachment = GraffitiAttachment(Graffiti(1, 2, "22", "24"))
        )
        WallService.add(post)

        WallService.createComment(1, Comments(1, 1, 1, "test"))

    }

    @Test
    fun reportAddedToComment() {
        // arrange
        val post1 = Post(
            id = 2,
            ownerId = 2,
            fromId = 312415,
            date = 1663613504,
            text = "Welcome!",
            replyOwnerId = 0,
            replyPostId = 0,
            friendsOnly = false,
            commentsOfPost = CommentsOfPost(2),
            copyright = "Vk",
            likes = Likes(1),
            reposts = Reposts(2),
            views = Views(4),
            attachment = GraffitiAttachment(Graffiti(1, 2, "22", "24"))
        )

        WallService.add(post1)
        WallService.createComment(post1.id, Comments(id = 3, fromId = 2, date = 1, text = "test"))
        WallService.createReportForComment(
            commentId = 3,
            reason = 6,
            reportComments = ReportComments(1, 1, 1 , 6)
        )

        // act
        val result = WallService.reports[0].commentId

        // assert
        assertEquals(1, result)
    }

    @Test(expected = PostNotFoundException::class)
    fun reportAddingProcessShouldThrowDueToReasonValue() {

        val post = Post(
            ownerId = 2,
            fromId = 312415,
            date = 1663613504,
            text = "Welcome!",
            replyOwnerId = 0,
            replyPostId = 0,
            friendsOnly = false,
            commentsOfPost = CommentsOfPost(2),
            copyright = "Vk",
            likes = Likes(1),
            reposts = Reposts(2),
            views = Views(4),
            attachment = GraffitiAttachment(Graffiti(1, 2, "22", "24"))
        )
        WallService.add(post)
        WallService.createComment(post.id, Comments(id = 3, fromId = 2, date = 1, text = "test"))
        WallService.createReportForComment(
            commentId = 3,
            reason = 7,
            reportComments = ReportComments(1, 1, 1 , 7)
        )
    }

    @Test(expected = PostNotFoundException::class)
    fun reportAddingProcessShouldThrowDueToCommentId() {

        val post = Post(
            ownerId = 2,
            fromId = 312415,
            date = 1663613504,
            text = "Welcome!",
            replyOwnerId = 0,
            replyPostId = 0,
            friendsOnly = false,
            commentsOfPost = CommentsOfPost(2),
            copyright = "Vk",
            likes = Likes(1),
            reposts = Reposts(2),
            views = Views(4),
            attachment = GraffitiAttachment(Graffiti(1, 2, "22", "24"))
        )
        WallService.add(post)
        WallService.createComment(post.id, Comments(id = 3, fromId = 2, date = 1, text = "test"))
        WallService.createReportForComment(
            commentId = 1,
            reason = 6,
            reportComments = ReportComments(1, 1, 1 , 6)
        )
    }
}


