package com.mohith.ezetap.widgets

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.widget.LinearLayout
import com.google.android.material.textview.MaterialTextView
import com.mohith.network.model.UIData
import com.mohith.network.model.UITYPE
import java.util.*

class DetailView(_ctx : Context, _uiDataList: List<UIData>?) :IWidget {
    private val ctx = _ctx
    private val uiDataList =_uiDataList
    override fun gen(): View {
        val linearLayout = LinearLayout(ctx)
        linearLayout.orientation = LinearLayout.VERTICAL
        if(uiDataList?.isNotEmpty() == true){
            uiDataList.forEach{
                if(it.uiType== UITYPE.edittext){
                    val hLinearLayout = LinearLayout(ctx)
                    val label = MaterialTextView(ctx)
                    val value = MaterialTextView(ctx)

                    value.setPadding(50,10,10,10)
                    value.typeface = Typeface.DEFAULT_BOLD

                    val key: List<String>? =  it.key?.split("_")
                    if(key?.isNotEmpty() == true && key.size==2){
                        label.text = key[1].uppercase(Locale.getDefault())
                    }else{
                        label.text = it.key?.uppercase(Locale.getDefault()) ?: "UNKNOWN"
                    }
                    if( it.value == ""){
                        value.text = "No Value Entered"
                    }else{
                        value.text = it.value
                    }

                    hLinearLayout.addView(label)
                    hLinearLayout.addView(value)
                    linearLayout.addView(hLinearLayout)
                }
            }
        }
        return linearLayout
    }
}