/*
Copyright (C) 2018-present memtrip

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.memtrip.eosreach.robot.account

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.memtrip.eosreach.R
import org.hamcrest.Matchers.allOf

class AccountRobot {

    fun verifyAccountScreen(): AccountRobot {

        onView(withId(R.id.account_toolbar))
            .check(matches(isDisplayed()))

        onView(withId(R.id.account_balance_available_appbarlayout))
            .check(matches(isDisplayed()))

        onView(withContentDescription(R.string.abc_action_bar_up_description))
            .check(matches(isDisplayed()))

        return this
    }

    fun verifyReadOnlyAccountScreen(): AccountRobot {

        onView(withId(R.id.account_toolbar))
            .check(matches(isDisplayed()))

        onView(withId(R.id.account_balance_available_appbarlayout))
            .check(matches(isDisplayed()))

        onView(withId(R.id.account_menu_search))
            .check(doesNotExist())

        return this
    }

    fun verifyAvailableBalance(): AccountRobot {

        onView(withId(R.id.account_available_balance_value))
            .check(matches(isDisplayed()))
            .check(matches(withText("$162947.12")))

        onView(withId(R.id.account_available_balance_label))
            .check(matches(isDisplayed()))
            .check(matches(withText("Available Balance")))

        return this
    }

    fun verifyUnavailableBalance(): AccountRobot {

        onView(withId(R.id.account_available_balance_value))
            .check(matches(isDisplayed()))
            .check(matches(withText("-")))

        onView(withId(R.id.account_available_balance_label))
            .check(matches(isDisplayed()))
            .check(matches(withText("market rates are unavailable")))

        return this
    }

    fun verifyAccountError(): AccountRobot {

        onView(withId(R.id.account_error_view))
            .check(matches(isDisplayed()))

        return this
    }

    fun selectAccountErrorRetry(): AccountRobot {

        onView(allOf(
            withId(R.id.view_error_composite_retry),
            isDescendantOfA(withId(R.id.account_error_view))
        ))
            .check(matches(isDisplayed()))
            .perform(click())

        return this
    }

    fun swipeToRefresh(): AccountRobot {

        onView(withId(R.id.account_nestedscrollview))
            .check(matches(isDisplayed()))
            .perform(swipeDown())

        return this
    }

    fun showErrorDialog(): AccountRobot {

        onView(withText(R.string.account_error_get_account_title))
            .check(matches(isDisplayed()))

        onView(withText(R.string.account_error_get_account_body))
            .check(matches(isDisplayed()))

        onView(withText(R.string.app_dialog_positive_button))
            .check(matches(isDisplayed()))

        return this
    }

    fun selectBalanceTab(): AccountRobot {
        onView(withText(R.string.account_page_balance))
            .check(matches(isDisplayed()))
            .perform(click())
        return this
    }

    fun selectResourcesTab(): AccountRobot {
        onView(withText(R.string.account_page_resources))
            .check(matches(isDisplayed()))
            .perform(click())
        return this
    }

    fun selectVoteTab(): AccountRobot {
        onView(withText(R.string.account_page_vote))
            .check(matches(isDisplayed()))
            .perform(click())
        return this
    }

    fun selectSearchMenuItem(): AccountRobot {
        onView(withId(R.id.account_menu_search))
            .check(matches(isDisplayed()))
            .perform(click())
        return this
    }
}