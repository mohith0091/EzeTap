package com.mohith.ezetap.widgets

import android.content.Context
import android.graphics.Typeface
import android.view.View
import com.google.android.material.textview.MaterialTextView
import com.mohith.ezetap.uitls.Const
import com.mohith.network.model.UIData

class LabelView(_ctx:Context, _uiData: UIData) : IWidget {
    private val ctx = _ctx;
    private val uidata = _uiData

    override fun gen():View {
       val labelView  = MaterialTextView(ctx)
        labelView.layoutParams = Const.getDefaultParams(left = 65, top = Const.paddingTop, right = Const.paddingRight, bottom = 5)
        labelView.typeface = Typeface.DEFAULT_BOLD
        labelView.text = uidata.value
        return labelView
    }
}