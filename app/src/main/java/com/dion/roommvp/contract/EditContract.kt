package com.dion.roommvp.contract

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
        fun mode(mode: String?,input: TextInputLayout)
        fun getBio(nik: String): Biodata
    }

    interface View : BaseView<Presenter> {
        fun changeMode(status: String, bio: Biodata)
        fun showToast(message: String)
        fun finish(context: Context)
    }
}