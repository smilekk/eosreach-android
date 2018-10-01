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
package com.memtrip.eosreach.api.eosprice

import com.memtrip.eosreach.api.Result
import com.memtrip.eosreach.utils.RxSchedulers
import io.reactivex.Single
import javax.inject.Inject

class EosPriceRequestImpl @Inject internal constructor(
    private val eosPriceApi: EosPriceApi,
    private val rxSchedulers: RxSchedulers
) : EosPriceRequest {

    override fun getPrice(currencyCode: String): Single<Result<EosPrice, EosPriceError>> {
        return eosPriceApi.getPrice(currencyCode.toUpperCase())
            .subscribeOn(rxSchedulers.background())
            .observeOn(rxSchedulers.main())
            .map {
                if (it.isSuccessful) {
                    Result(EosPrice(it.body()!!.value, it.body()!!.currency))
                } else {
                    if (it.code() == 406) {
                        Result<EosPrice, EosPriceError>(EosPriceError.UnsupportedCurrency)
                    } else {
                        Result<EosPrice, EosPriceError>(EosPriceError.Generic)
                    }
                }
            }
            .onErrorReturn { Result(EosPriceError.Generic) }
    }
}