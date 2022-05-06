package com.mohith.ezetap.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MediatorLiveData
import com.mohith.network.Api
import com.mohith.network.RetrofitHelper
import com.mohith.network.model.UIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL

class UIRepository {
    private val BASE_URL: String = "https://demo.ezetap.com"
    private val JSON_PATH: String = "/mobileapps/android_assignment.json"

    private val liveUIModel: MediatorLiveData<UIModel> = MediatorLiveData()
    private val logo: MediatorLiveData<Bitmap> = MediatorLiveData()
    private val api = RetrofitHelper.getInstance(BASE_URL).create(Api::class.java)


    fun getData(): MediatorLiveData<UIModel> {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val result = api.fetchCustomUI(JSON_PATH)
                val uiModel: UIModel = result.body() as UIModel
                liveUIModel.value = uiModel
                getLogo(uiModel.logoURL)
                Log.d("data: ", result.body().toString())
            } catch (e: Exception) {
                e.stackTrace
            }
        }
        return liveUIModel
    }

    private fun getLogo(url: String?) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                url?.let {
                    val result = api.fetchImage(it)
                    result.enqueue(object : Callback<ResponseBody> {

                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            Log.e("TAG", "onFailure: ")

                        }

                        override fun onResponse(
                            call: Call<ResponseBody>,
                            response: Response<ResponseBody>
                        ) {
                            if (!response.isSuccessful || response.body() == null || response.errorBody() != null) {
                                Log.e("TAG", "onFailure: ")
                                return
                            }
                            val bytes = response.body()!!.bytes()
                            val bitmap: Bitmap =
                                BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                            logo.value = bitmap
                            Log.e("TAG", "onResponse: " + bitmap)
                        }
                    })
                }


            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }

    fun getLogo(): MediatorLiveData<Bitmap> {
        return logo
    }
}