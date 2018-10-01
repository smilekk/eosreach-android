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
package com.memtrip.eosreach.app.account.vote.cast

import android.app.Application
import com.memtrip.mxandroid.MxViewModel
import io.reactivex.Observable
import javax.inject.Inject

class CastVoteViewModel @Inject internal constructor(
    application: Application
) : MxViewModel<CastVoteIntent, CastVoteRenderAction, CastVoteViewState>(
    CastVoteViewState(view = CastVoteViewState.View.Idle),
    application
) {

    override fun dispatcher(intent: CastVoteIntent): Observable<CastVoteRenderAction> = when (intent) {
        is CastVoteIntent.Init -> Observable.just(CastVoteRenderAction.Populate(intent.eosAccount, intent.page))
        CastVoteIntent.CastProducerVoteTabIdle -> startingTab(CastVoteFragmentPagerFragment.Page.PRODUCER)
        CastVoteIntent.CastProxyVoteTabIdle -> startingTab(CastVoteFragmentPagerFragment.Page.PROXY)
    }

    override fun reducer(previousState: CastVoteViewState, renderAction: CastVoteRenderAction): CastVoteViewState = when (renderAction) {
        is CastVoteRenderAction.TabIdle -> previousState.copy(
            view = CastVoteViewState.View.Idle,
            page = renderAction.page)
        is CastVoteRenderAction.Populate -> previousState.copy(
            view = CastVoteViewState.View.Populate(renderAction.eosAccount),
            page = renderAction.page)
    }

    override fun filterIntents(intents: Observable<CastVoteIntent>): Observable<CastVoteIntent> = Observable.merge(
        intents.ofType(CastVoteIntent.Init::class.java).take(1),
        intents.filter {
            !CastVoteIntent.Init::class.java.isInstance(it)
        }
    )

    private fun startingTab(page: CastVoteFragmentPagerFragment.Page): Observable<CastVoteRenderAction> = when (page) {
        CastVoteFragmentPagerFragment.Page.PRODUCER -> Observable.just(CastVoteRenderAction.TabIdle(CastVoteFragmentPagerFragment.Page.PRODUCER))
        CastVoteFragmentPagerFragment.Page.PROXY -> Observable.just(CastVoteRenderAction.TabIdle(CastVoteFragmentPagerFragment.Page.PROXY))
    }
}