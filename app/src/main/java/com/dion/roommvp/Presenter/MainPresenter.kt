package com.dion.roommvp.Presenter

import android.content.Context
import android.util.Log
import com.dion.roommvp.BioDB.BioDB
import com.dion.roommvp.contract.BiodataContract
import com.dion.roommvp.model.Biodata
import kotlinx.coroutines.runBlocking

class MainPresenter(
    context: Context,
    private val viewMain: BiodataContract.ViewMain
): BiodataContract.PresenterMain {

    init {
        viewMain.setPresenter(this)
    }

    private val db = BioDB.getInstance(context).bioDao()
    var biodata = Biodata()
    var data = mutableListOf<Biodata>()
    var dataSearch = mutableListOf<Biodata>()

    override fun getAll(): MutableList<Biodata> {
        runBlocking {
            data = db.selectAll()
        }

        return data
    }

    override fun searchBioata(searchName: String): MutableList<Biodata> {
        runBlocking {
            dataSearch = db.searchName(searchName)
        }
        Log.e("cari", dataSearch.toString())
        return dataSearch

    }

    override fun searchLoad() {
        viewMain.showSearch(dataSearch)
    }

    override fun start() {
        viewMain.showData(getAll())
    }

}