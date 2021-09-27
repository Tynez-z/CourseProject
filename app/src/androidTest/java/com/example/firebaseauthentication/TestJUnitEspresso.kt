package com.example.firebaseauthentication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestJUnitEspresso {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Test
    fun appLaunchesSuccessfully() {
        activityRule.launchActivity(null)

        workWithPatternRobot()
            .checkIsShowButton()
    }

    class workWithPatternRobot {
        fun checkIsShowButton() = apply {
            onView(withId(R.id.btnTest))
                .check(ViewAssertions.matches(isDisplayed()))
        }
    }
}