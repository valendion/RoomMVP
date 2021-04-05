package com.dion.roommvp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.dion.roommvp.Presenter.MainPresenter
import com.dion.roommvp.R
import com.dion.roommvp.adapter.BioAdapter
import com.dion.roommvp.contract.BiodataContract
import com.dion.roommvp.databinding.ActivityMainBinding
import com.dion.roommvp.model.Biodata

class MainActivity : AppCompatActivity(), BiodataContract.ViewMain {

    private lateinit var bindiog: ActivityMainBinding
    var mAdapter  =  BioAdapter()
    lateinit var mPresenter: BiodataContract.PresenterMain
    var dataList = mutableListOf<Biodata>()
    var dataSearch = mutableListOf<Biodata>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindiog = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindiog.root)

        MainPresenter(this, this)
        mPresenter.start()

        bindiog.mainRV.apply {
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = mAdapter
        }

        bindiog.appCompatButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, EditActivity::class.java).putExtra("nik", ""))
        }
    }

    override fun showData(data: MutableList<Biodata>) {
        dataList = data
        mAdapter.setValue(dataList)
    }

    override fun showSearch(data: MutableList<Biodata>) {
        dataSearch = data
        mAdapter.setValue(dataSearch)
        Log.e("dataSearchView", data.toString())
    }

    override fun setPresenter(presenter: BiodataContract.PresenterMain) {
        mPresenter = presenter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        val searchItem = menu?.findItem(R.id.searcItem)

        val searchView: SearchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotBlank()) {
                    val search = "%$query%"

                    //Fungsi Pencarian
                    mPresenter.searchBioata(search)

                    //Menampilkan hasik pencarian
                    mPresenter.searchLoad()
                }
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query.isBlank()){
                    mAdapter.setValue(dataList)
                }
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }


}