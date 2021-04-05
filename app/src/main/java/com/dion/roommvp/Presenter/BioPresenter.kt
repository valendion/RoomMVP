package com.dion.roommvp.Presenter

import android.content.Context
import android.util.Log
import com.dion.roommvp.BioDB.BioDB
import com.dion.roommvp.contract.EditContract
import com.dion.roommvp.model.Biodata
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class BioPresenter(
    val context: Context,
    val view: EditContract.View
) : EditContract.Presenter {

    init {
        view.setPresenter(this)
    }


    var biodata = Biodata()
    private val db = BioDB.getInstance(context).bioDao()

    override fun add(bio: Biodata) {
        if (bio.nik.isBlank() || bio.job == null|| bio.sex == null || bio.job == null) {
            view.showToast("Isi data anda dengan benar")

        } else {
            CoroutineScope(Dispatchers.IO).launch {
                db.insertData(bio)
            }

            view.showToast("Add data sukses")
            view.finish(context)
        }
    }

    override fun delete(bio: Biodata) {

            CoroutineScope(Dispatchers.IO).launch {
                db.deleteData(bio)
            }
            view.showToast("Delete data sukses")
            view.finish(context)

    }

    override fun update(bio: Biodata) {
        if (bio.nik.isBlank() || bio.job.isNullOrBlank()|| bio.job.isNullOrBlank()) {
            view.showToast("Isi data anda dengan benar")
            Log.d("sex",bio.sex.toString())
        } else {
            CoroutineScope(Dispatchers.IO).launch {
                db.updateData(bio)
            }
            Log.d("sex",bio.sex.toString())
            view.showToast("Update data sukses")
            view.finish(context)
        }

    }

    override fun mode(mode: String?, input: TextInputLayout) {
        if (mode.isNullOrBlank()) {
            input.isEnabled = true
        } else {
            view.changeMode(mode,  getBio(mode))
        }

    }

    override fun getBio(nik: String): Biodata {
        runBlocking {
            biodata = db.selectOne(nik)
        }
        Log.d("dataAll", biodata.toString())
        return biodata
    }


    override fun start() {
    }
}


