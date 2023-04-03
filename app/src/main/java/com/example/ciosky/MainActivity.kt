package com.example.ciosky

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ciosky.Viewmodel.viewmodel
import com.example.ciosky.network.Resource

class MainActivity : AppCompatActivity() {
    lateinit var viewmodelmethod: viewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodelmethod = ViewModelProvider(this)[viewmodel::class.java]

        viewmodelmethod.getList()
        observeRecentProduct()

        viewmodelmethod.AddUser()
        observeAddUser()


    }
    private fun observeRecentProduct() {
        viewmodelmethod.observeRecentLiveData().observe(this, Observer { response ->
            when(response){
                is Resource.Success -> {
                    println("### list success : ${response.data?.title}")
                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {

                    println("### products error occured")
                }
            }

        })
    }
    private fun observeAddUser(){
        viewmodelmethod.observeAddUserLiveData().observe(this, Observer { response ->
            when(response){
                is Resource.Success -> {
                    println("### list success : ${response.data?.firstName}")
                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {

                    println("### products error occured")
                }
            }

        })
    }
}