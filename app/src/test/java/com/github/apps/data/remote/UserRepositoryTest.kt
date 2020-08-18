package com.github.apps.data.remote

import com.github.apps.domain.entities.response.UserResponse
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

class UserRepositoryTest {
    @Rule
    @JvmField
    var rule = MockitoJUnit.rule()

    @Mock
    var remoteAPI: RemoteAPI? = null

    @InjectMocks
    var repository: UserRepository? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {

    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
    }

    @Test
    fun testSearchSuccessful() {
        runBlocking {
            val observer = repository!!.searchUser("a", 1)!!.test()
            observer.awaitTerminalEvent()
            observer.assertNoErrors().assertValue { r: UserResponse -> r.items?.size!! > 0 }
        }
    }

    @Test
    fun testSearchUnsuccessful() {
        runBlocking {
            val observer = try {
                repository!!.searchUser("", 1)!!.test()
            } catch (e: java.lang.Exception) {
                Assert.assertTrue(e.message.equals("HTTP 422 Unprocessable Entity"))
            }
//            observer.awaitTerminalEvent()
//            observer.assertNoErrors().assertValue { r: UserResponse -> r.errors?.isNotEmpty()!! }
        }

    }
}