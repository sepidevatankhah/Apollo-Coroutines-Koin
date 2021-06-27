package ir.nwise.app.ui.detail

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import ir.nwise.app.R
import ir.nwise.app.ui.MainActivity
import ir.nwise.app.ui.home.UserPostViewHolder
import ir.nwise.app.utils.EspressoIdlingResourceRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class PostDetailFragmentTest {

    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val espressoIdlingResourceRule = EspressoIdlingResourceRule()

    @Test
    fun initial_state_user_post_detail_UI_test() {
        val textToMatch = "Earth's Date: "
        activityTestRule.scenario.moveToState(Lifecycle.State.RESUMED)
        with(Espresso.onView(withId(R.id.recyclerView))) {
            perform(RecyclerViewActions.scrollToPosition<UserPostViewHolder>(0))
            perform(ViewActions.click())
        }
        Espresso.onView(ViewMatchers.withText(textToMatch))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun back_button_test_UI_test() {
        initial_state_user_post_detail_UI_test()
        Espresso.pressBack()
        Espresso.onView(withId(R.id.recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}