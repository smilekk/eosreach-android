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
package com.memtrip.eosreach.app.settings

import com.memtrip.eosreach.app.settings.eosendpoint.EosEndpointActivity
import com.memtrip.eosreach.app.settings.eosendpoint.EosEndpointActivityModule
import com.memtrip.eosreach.app.settings.viewprivatekeys.ViewPrivateKeysActivity
import com.memtrip.eosreach.app.settings.viewprivatekeys.ViewPrivateKeysActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SettingsModule {

    @ContributesAndroidInjector(modules = [SettingsActivityModule::class])
    internal abstract fun contributeSettingsActivity(): SettingsActivity

    @ContributesAndroidInjector(modules = [EosEndpointActivityModule::class])
    internal abstract fun contributeEosEndpointActivity(): EosEndpointActivity

    @ContributesAndroidInjector(modules = [ViewPrivateKeysActivityModule::class])
    internal abstract fun contributeViewPrivateKeysActivity(): ViewPrivateKeysActivity
}