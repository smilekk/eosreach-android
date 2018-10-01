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
package com.memtrip.eosreach.uikit

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class SimpleRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private lateinit var simpleAdapter: SimpleAdapter<*>

    init {
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    atEnd()
                }
            }
        })
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<*>?) {

        if (adapter !is SimpleAdapter<*>) {
            throw IllegalStateException(
                "SimpleRecyclerView must use a SimpleAdapter as its source")
        }

        super.setAdapter(adapter)

        simpleAdapter = adapter
    }

    private fun atEnd() {
        simpleAdapter.atEnd(id)
    }
}