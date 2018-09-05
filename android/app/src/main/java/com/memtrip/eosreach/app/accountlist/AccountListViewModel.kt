package com.memtrip.eosreach.app.accountlist

import android.app.Application
import com.memtrip.eosreach.db.SelectedAccount
import com.memtrip.eosreach.db.account.AccountEntity
import com.memtrip.eosreach.db.account.GetAccounts

import com.memtrip.mxandroid.MxViewModel
import io.reactivex.Observable
import rx.lang.kotlin.toSingle
import javax.inject.Inject

class AccountListViewModel @Inject internal constructor(
    private val getAccounts: GetAccounts,
    private val selectedAccount: SelectedAccount,
    private val accountListUseCase: AccountListUseCase,
    application: Application
) : MxViewModel<AccountListIntent, AccountListRenderAction, AccountListViewState>(
    AccountListViewState(view = AccountListViewState.View.Idle),
    application
) {

    override fun dispatcher(intent: AccountListIntent): Observable<AccountListRenderAction> = when (intent) {
        is AccountListIntent.Init -> getAccounts()
        AccountListIntent.Idle -> Observable.just(AccountListRenderAction.Idle)
        is AccountListIntent.AccountSelected -> accountSelected(intent.accountName)
        AccountListIntent.RefreshAccounts -> refreshAccounts()
    }

    override fun reducer(previousState: AccountListViewState, renderAction: AccountListRenderAction): AccountListViewState = when (renderAction) {
        AccountListRenderAction.Idle -> previousState.copy(
            view = AccountListViewState.View.Idle)
        AccountListRenderAction.OnProgress -> previousState.copy(
            view = AccountListViewState.View.OnProgress)
        is AccountListRenderAction.OnSuccess -> previousState.copy(
            view = AccountListViewState.View.OnSuccess(renderAction.accountList))
        AccountListRenderAction.OnError -> previousState.copy(
            view = AccountListViewState.View.OnError)
        is AccountListRenderAction.NavigateToAccount -> previousState.copy(
            view = AccountListViewState.View.NavigateToAccount(renderAction.accountEntity))
    }

    private fun getAccounts(): Observable<AccountListRenderAction> {
        return getAccounts.select().map<AccountListRenderAction> {
            AccountListRenderAction.OnSuccess(it)
        }.toObservable()
            .onErrorReturn { AccountListRenderAction.OnError }
            .startWith(AccountListRenderAction.OnProgress)
    }

    private fun accountSelected(accountEntity: AccountEntity): Observable<AccountListRenderAction> {
        selectedAccount.put(accountEntity.accountName)
        return Observable.just(AccountListRenderAction.NavigateToAccount(accountEntity))
    }

    private fun refreshAccounts(): Observable<AccountListRenderAction> {
        return accountListUseCase
            .refreshAccounts()
            .doOnError { AccountListRenderAction.OnError }
            .andThen<AccountListRenderAction>(getAccounts())
    }
}