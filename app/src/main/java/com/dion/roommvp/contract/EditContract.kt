package com.dion.roommvp.contract

import android.app.Activity
import android.content.Context
import com.dion.roommvp.BasePresenter
import com.dion.roommvp.BaseView
import com.dion.roommvp.model.Biodata
import com.google.android.material.textfield.TextInputLayout

interface EditContract {

    interface Presenter : BasePresenter {
        fun add(bio: Biodata)
        fun delete(bio: Biodata)
        fun update(bio: Biodata)
        fun mode(mode: String,input: TextInputLayout)
        fun getBio(nik: String): Biodata
//        fun validation(nik: String, name: String, jekel: String, job: String, pengoprasinya: String)
    }

    interface View : BaseView<Presenter> {
        fun changeMode(status: String)
        fun biodataPerson(bio: Biodata)
        fun showToast(message: String)

    }
}