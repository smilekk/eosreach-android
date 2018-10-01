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
package com.memtrip.eosreach.app.account.vote

import android.os.SystemClock
import com.memtrip.eosreach.StubTestCase

class VoteForProxyTestCase : StubTestCase() {

    override fun test() {
        splashRobot
            .navigateImportKey()
        importKeyRobot
            .typePrivateKey("5KQwrPbwdL6PhXujxW37FSSQZ1JiwsST4cqQzDeyXtP79zkvFD3")
            .selectImportButton()
        accountListRobot
            .selectFirstAccountRow()
        accountRobot
            .verifyAccountSuccess()
            .verifyAvailableBalance()
            .selectVoteTab()
        voteRobot
            .verifyNotVotedScreen()
            .selectVoteForProxy()
            .verifyCastProxyVoteScreen()
            .typeCastProxyVote("memtripproxy")
            .selectCastProxyVoteButton()
        transactionRobot
            .verifyTransactionErrorDialog()
            .selectViewLogButton()
            .verifyTransactionLog(readJsonFile("stub/error/error_push_transaction_log.json"))
        commonRobot.pressBack()
        voteRobot
            .verifyCastProxyVoteInputValue("memtripproxy")
    }
}