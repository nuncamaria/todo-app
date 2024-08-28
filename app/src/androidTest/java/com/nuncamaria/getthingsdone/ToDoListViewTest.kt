package com.nuncamaria.getthingsdone

import android.content.Context
import android.content.Intent
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.nuncamaria.getthingsdone.ui.todolist.ToDoListId
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ToDoListViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val ctx: Context = InstrumentationRegistry.getInstrumentation().targetContext


    @Test
    fun emptyMessageIsNotDisplayed() {
        launchActivity<MainActivity>(Intent(ctx, MainActivity::class.java))

        composeTestRule.onNodeWithTag(ToDoListId.EMPTY_MESSAGE).isNotDisplayed()
    }

    @Test
    fun toDoListIsDisplayed() {
        launchActivity<MainActivity>(Intent(ctx, MainActivity::class.java))

        composeTestRule.onNodeWithTag(ToDoListId.TODO_LIST).isDisplayed()
    }
}