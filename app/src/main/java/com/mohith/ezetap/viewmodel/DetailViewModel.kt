package com.mohith.ezetap.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohith.network.model.UIData
import java.util.ArrayList

class DetailViewModel:ViewModel(){
    private val uiDataList: MutableLiveData<ArrayList<UIData>?> = MutableLiveData()

    fun setUIDataList(dataList:ArrayList<UIData>?){
        uiDataList.value = dataList
    }

    fun getUIDataList():LiveData<ArrayList<UIData>?>{
       return uiDataList
    }
}