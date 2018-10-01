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
package com.memtrip.eosreach.app.account

import com.memtrip.mxandroid.MxViewState

data class AccountViewState(
    val view: View,
    val accountName: String? = null,
    val accountView: AccountView? = null,
    val page: AccountFragmentPagerAdapter.Page = AccountFragmentPagerAdapter.Page.BALANCE
) : MxViewState {

    sealed class View {
        object Idle : View()
        object OnProgress : View()
        object OnSuccess : View()
        object OnErrorFetchingAccount : View()
        object OnErrorFetchingBalances : View()
        object NavigateToAccountList : View()
        object NavigateToImportKey : View()
        object NavigateToCreateAccount : View()
        object NavigateToSettings : View()
    }
}