package com.dion.roommvp.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dion.roommvp.Presenter.BioPresenter
import com.dion.roommvp.Presenter.MainPresenter
import com.dion.roommvp.adapter.BioAdapter
import com.dion.roommvp.contract.BiodataContract
import com.dion.roommvp.contract.EditContract
import com.dion.roommvp.databinding.ActivityMainBinding
import com.dion.roommvp.model.Biodata

class MainActivity : AppCompatActivity(),BiodataContract.ViewMain {

    private lateinit var bindiog: ActivityMainBinding
    lateinit var mAdapter: BioAdapter
    lateinit var mPresenter: BiodataContract.PresenterMain



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindiog = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindiog.root)

        MainPresenter(this, this)
        mPresenter.start()


        bindiog.appCompatButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, EditActivity::class.java).putExtra("nik",""))
        }
    }

    override fun showData(data: MutableList<Biodata>) {
        bindiog.mainRV.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            mAdapter = BioAdapter(data)
            adapter = mAdapter
        }
    }

    override fun setPresenter(presenter: BiodataContract.PresenterMain) {
        mPresenter = presenter
    }

    override fun onResume() {
        super.onResume()
        mPresenter.start()
    }

}