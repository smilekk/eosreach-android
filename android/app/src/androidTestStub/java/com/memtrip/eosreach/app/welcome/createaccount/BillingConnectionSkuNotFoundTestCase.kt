package com.memtrip.eosreach.app.welcome.createaccount

import com.memtrip.eosreach.StubTestCase
import com.memtrip.eosreach.billing.BillingConnectionResponse

class BillingConnectionSkuNotFoundTestCase : StubTestCase() {

    override fun test() {
        splashRobot.navigateCreateAccount()
        createAccountRobot.verifySkuNotFound()
    }

    override fun billingConnectionResponse(): BillingConnectionResponse {
        return BillingConnectionResponse.SkuNotFound
    }
}