package com.example.tip_calculator_android

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorTests {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calcular_gorjeta_20_porcento() {
        onView(withId(R.id.bill_value_edit))
            .perform(typeText("100.00"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.twenty_percentage_radio))
            .perform(click())

        onView(withId(R.id.calculate_button))
            .perform(click())

        onView(withId(R.id.tip_value_text))
            .check(matches(withText(containsString("20"))))
    }

    @Test
    fun calcular_gorjeta_15_porcento() {
        onView(withId(R.id.bill_value_edit))
            .perform(typeText("100.00"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.fifteen_percentage_radio))
            .perform(click())

        onView(withId(R.id.calculate_button))
            .perform(click())

        onView(withId(R.id.tip_value_text))
            .check(matches(withText(containsString("15"))))
    }

    @Test
    fun calcular_gorjeta_10_porcento() {
        onView(withId(R.id.bill_value_edit))
            .perform(typeText("100.00"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.ten_percentage_radio))
            .perform(click())

        onView(withId(R.id.calculate_button))
            .perform(click())

        onView(withId(R.id.tip_value_text))
            .check(matches(withText(containsString("10"))))
    }

    @Test
    fun calcular_gorjeta_nao_arredondada() {
        onView(withId(R.id.bill_value_edit))
            .perform(typeText("103.00"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.round_up_switch))
            .perform(click())

        onView(withId(R.id.calculate_button))
            .perform(click())

        onView(withId(R.id.tip_value_text))
            .check(matches(withText(containsString("20.60"))))
    }
}

