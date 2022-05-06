package com.mohith.ezetap.widgets

import android.content.Context
import android.view.View
import com.google.android.material.button.MaterialButton
import com.mohith.ezetap.R
import com.mohith.ezetap.uitls.Const
import com.mohith.network.model.UIData

class ButtonView(_ctx: Context, _uiData: UIData) : IWidget {
    private val ctx = _ctx;
    private val uidata = _uiData

    override fun gen(): View {
        val button  = MaterialButton(ctx)
        button.layoutParams = Const.getDefaultParams()
        button.setBackgroundResource(R.drawable.button_background)
        button.text = uidata.value
        button.setTextColor(ctx.resources.getColor(R.color.white) )
        return button
    }
}