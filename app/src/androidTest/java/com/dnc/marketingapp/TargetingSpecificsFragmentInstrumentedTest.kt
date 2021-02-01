package com.dnc.marketingapp

import android.view.View
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.*
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dnc.marketingapp.presentation.ui.targetingspecifics.TargetingSpecificsFragment
import org.junit.Assert

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TargetingSpecificsFragmentInstrumentedTest {

    @Test
    fun has_visible_loading_view_on_create() {
        val scenario = launchFragmentInContainer<TargetingSpecificsFragment>()
        scenario.moveToState(Lifecycle.State.CREATED)
        Assert.assertEquals(View.VISIBLE, isVisible())
    }

    private fun isVisible() = getViewAssertion(ViewMatchers.Visibility.VISIBLE)

    fun isGone() = getViewAssertion(ViewMatchers.Visibility.GONE)

    private fun getViewAssertion(visibility: ViewMatchers.Visibility): ViewAssertion? {
        return ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(visibility))
    }
}