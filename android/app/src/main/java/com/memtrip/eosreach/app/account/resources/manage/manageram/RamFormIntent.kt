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
package com.memtrip.eosreach.app.account.resources.manage.manageram

import com.memtrip.eosreach.api.balance.Balance
import com.memtrip.mxandroid.MxViewIntent

sealed class RamFormIntent : MxViewIntent {
    object Init : RamFormIntent()
    object Idle : RamFormIntent()
    data class ConvertKiloBytesToEOSCost(val kb: String, val costPerKb: Balance) : RamFormIntent()
    data class Commit(
        val kilobytes: String,
        val ramCommitType: RamCommitType
    ) : RamFormIntent()
}