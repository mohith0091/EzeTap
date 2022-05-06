package com.mohith.ezetap.widgets

import android.content.Context
import android.util.Log
import android.view.View
import com.mohith.network.model.UIData
import com.mohith.network.model.UITYPE

object WidgetGenerator {
    private val TAG:String = WidgetGenerator::class.java.simpleName
    fun generate(ctx : Context,uiDataList: List<UIData>?) : Map< UIData,View>{
        val widgets: MutableMap<UIData,View> = mutableMapOf()
        uiDataList?.forEach {
            var widget: IWidget? =null
            widget = when(it.uiType){
                UITYPE.label -> LabelView(ctx,it)
                UITYPE.edittext -> EditTextView(ctx,it)
                UITYPE.button -> ButtonView(ctx,it)
            }
            if(widget!=null){
                widgets[it] = widget.gen()
            }
        }
        return widgets
    }

    fun generateDetailView(ctx : Context,uiDataList: List<UIData>?):View{
        return DetailView(ctx,uiDataList).gen()
    }
}