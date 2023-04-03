package com.example.ciosky.Viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.ciosky.model.ResponseModel
import com.example.ciosky.model.User.RequestAddUser
import com.example.ciosky.model.User.ResponseUser
import com.example.ciosky.network.Resource
import com.example.ciosky.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class viewmodel : ViewModel(){
    val getListLiveData = MutableLiveData<Resource<ResponseModel>>()
    val addUserLiveData=MutableLiveData<Resource<ResponseUser>>()

    fun getList() {
        RetrofitInstance.Api.getResponse()
            .enqueue(object : Callback<ResponseModel> {
                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    response.body()?.let { recentList ->
                        getListLiveData.value = Resource.Loading()
                        if (response.isSuccessful) {
                            getListLiveData.value = Resource.Success(recentList)
                            println("### recent products success : ${recentList}")
                        } else {
                            val message = response.message()
                            val code = response.code()
                            println("### error recent products : code :${code} and message : ${message}")
                            getListLiveData.value = Resource.Error(message = message)
                        }
                    }
                }
                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    Log.e("HomeViewModel", t.message.toString())
                }
            })
    }
    fun AddUser() {
        val request = RequestAddUser(
           firstName = "Muhammad",lastName = "Ovi", age ="25"
        )
        RetrofitInstance.ApiAddUser.AddUser(request)
            .enqueue(object : Callback<ResponseUser> {
                override fun onResponse(
                    call: Call<ResponseUser>,
                    response: Response<ResponseUser>
                ) {
                    response.body()?.let { productList ->
                        addUserLiveData.value = Resource.Loading()
                        if (response.isSuccessful) {
                                addUserLiveData.value = Resource.Success(productList)
                                println("### user success : ${productList}")

                        } else {
                            val message = response.message()
                            val code = response.code()
                            println("### error user : code :${code} and message : ${message}")
                            addUserLiveData.value = Resource.Error(message = message)
                        }


                    }
                }

                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    Log.e("HomeViewModel", t.message.toString())
                }
            })
    }


    fun observeRecentLiveData(): MutableLiveData<Resource<ResponseModel>> {
        return getListLiveData
    }

    fun observeAddUserLiveData(): MutableLiveData<Resource<ResponseUser>> {
        return addUserLiveData
    }
}