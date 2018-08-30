package com.memtrip.eosreach.app.account.resources

import android.app.Application
import com.memtrip.mxandroid.MxViewModel
import io.reactivex.Observable
import javax.inject.Inject

class ResourcesViewModel @Inject internal constructor(
    application: Application
) : MxViewModel<ResourcesIntent, ResourcesRenderAction, ResourcesViewState>(
    ResourcesViewState(view = ResourcesViewState.View.Idle),
    application
) {

    override fun dispatcher(intent: ResourcesIntent): Observable<ResourcesRenderAction> = when (intent) {
        is ResourcesIntent.Init -> Observable.just(ResourcesRenderAction.OnProgress)
    }

    override fun reducer(previousState: ResourcesViewState, renderAction: ResourcesRenderAction): ResourcesViewState = when (renderAction) {
        ResourcesRenderAction.OnProgress -> previousState.copy(view = ResourcesViewState.View.OnProgress)
        ResourcesRenderAction.OnError -> previousState.copy(view = ResourcesViewState.View.OnError)
    }

    override fun filterIntents(intents: Observable<ResourcesIntent>): Observable<ResourcesIntent> = Observable.merge(
        intents.ofType(ResourcesIntent.Init::class.java).take(1),
        intents.filter {
            !ResourcesIntent.Init::class.java.isInstance(it)
        }
    )
}