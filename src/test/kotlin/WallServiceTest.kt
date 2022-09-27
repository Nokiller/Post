import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import ru.netology.domain.Post
import ru.netology.domain.WallService
import ru.netology.domain.WallService.posts
import ru.netology.domain.ru.netology.domain.Likes

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
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
            copyright = "Vk",
            likes = Likes(1)
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
            copyright = "Vk",
            likes = Likes(2)
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
            copyright = "Vk",
            likes = Likes(3)
        )

        // act
        WallService.add(post)
        WallService.add(post)
        val result = WallService.update(updatePost)

        // assert
        assertEquals(true, result)
    }

    @Test
    fun update_returnFalse(){
        // arrange
        val post = Post(
            ownerId = 2,
            fromId = 312415,
            date = 1663613504,
            text = "Welcome!",
            replyOwnerId = 0,
            replyPostId = 0,
            friendsOnly = false,
            copyright = "Vk",
            likes = Likes(2)
        )

        val updatePost = Post(
            id = 2,
            ownerId = 3,
            fromId = 4,
            date = 1663613504,
            text = "Welcome home!",
            replyOwnerId = 0,
            replyPostId = 0,
            friendsOnly = false,
            copyright = "Vk",
            likes = Likes(3)
        )

        // act
        WallService.add(post)
        val result = WallService.update(updatePost)

        // assert
        assertEquals(false, result)
    }
}


