package com.mohith.ezetap.widgets

import android.content.Context
import android.text.InputType
import android.view.View
import android.widget.EditText
import com.mohith.ezetap.R
import com.mohith.ezetap.uitls.Const
import com.mohith.network.model.UIData

class EditTextView(_ctx: Context, _uiData: UIData) : IWidget {
    private val ctx = _ctx;
    private val uidata = _uiData

    override fun gen(): View {
        val editTest  = EditText(ctx)
        editTest.layoutParams = Const.getDefaultParams(left = Const.paddingLeft, top = 5, right = Const.paddingRight, bottom = Const.paddingBottom)
        editTest.setPadding(30,30,30,30)
        editTest.elevation = 6F
        editTest.maxLines =1
        editTest.setText(uidata.value)
        editTest.setBackgroundResource(R.drawable.textview_background)
        editTest.hint = uidata.hint
        if(uidata.key?.equals("text_phone") == true){
            editTest.inputType = InputType.TYPE_CLASS_NUMBER
        }
        return editTest
    }
}