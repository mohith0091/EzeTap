package com.mohith.ezetap.viewmodel

import android.graphics.Bitmap
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohith.ezetap.repository.UIRepository
import com.mohith.network.model.UIData
import com.mohith.network.model.UIModel

class UIViewModel:ViewModel() {
   private val repository: UIRepository = UIRepository()

    private val uiDataMap :MutableLiveData<Map<UIData, View>> = MutableLiveData()

     fun getData():LiveData<UIModel>{
        return repository.getData()
    }
    fun getLogo():LiveData<Bitmap>{
        return repository.getLogo()
    }

    fun setUIData(uiDataMap: Map<UIData, View>){
        this.uiDataMap.value = uiDataMap
    }

    fun getUIData(): LiveData<Map<UIData, View>>{
        return uiDataMap
    }

}