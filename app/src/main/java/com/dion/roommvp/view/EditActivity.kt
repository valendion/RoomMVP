package com.dion.roommvp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dion.roommvp.Presenter.BioPresenter
import com.dion.roommvp.R
import com.dion.roommvp.contract.EditContract
import com.dion.roommvp.databinding.ActivityEditBinding
import com.dion.roommvp.model.Biodata

class EditActivity : AppCompatActivity(), EditContract.View {

    lateinit var binding: ActivityEditBinding
    lateinit var mPresenter: EditContract.Presenter
    var dataList: ArrayList<Biodata> = arrayListOf()
     var jekel =""
    var bio = Biodata()
    val nik by lazy {
        intent.getStringExtra("nik") ?: ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        BioPresenter(this@EditActivity, this@EditActivity)
        Log.d("nik", nik)
        mPresenter.mode(nik, binding.nikInputTIL)

        binding.simpanUpdateBtn.setOnClickListener {

            if (binding.simpanUpdateBtn.text.toString() == "Simpan") {
                bio.nik = binding.nikInputTIL.editText?.text.toString()
                bio.name = binding.nameInputTIL.editText?.text.toString()
                bio.job = binding.jobInputTIL.editText?.text.toString()
                bio.sex = getRadioButtun()

                mPresenter.add(bio)

            } else if(binding.simpanUpdateBtn.text.toString() == "Update") {
                bio.nik = binding.nikInputTIL.editText?.text.toString()
                bio.name = binding.nameInputTIL.editText?.text.toString()
                bio.job = binding.jobInputTIL.editText?.text.toString()
                bio.sex = getRadioButtun()

                mPresenter.update(bio)

            }

        }

        binding.deleteBtn.setOnClickListener {


            bio.nik = binding.nikInputTIL.editText?.text.toString()
            bio.name = binding.nameInputTIL.editText?.text.toString()
            bio.job = binding.jobInputTIL.editText?.text.toString()
            bio.sex = binding.jobInputTIL.editText?.text.toString()
//                onRadioButtonClick(binding.root)

            mPresenter.delete(bio)
        }

    }


    override fun changeMode(status: String, bio: Biodata) {
        binding.simpanUpdateBtn.text = resources.getString(R.string.update)
        binding.deleteBtn.visibility = View.VISIBLE

        binding.nikInputTIL.editText?.setText(bio.nik)
        binding.nameInputTIL.editText?.setText(bio.name)
        binding.jobInputTIL.editText?.setText(bio.job)
        binding.nikInputTIL.isEnabled = false

        Log.d("sex2", bio.sex.toString())

        if (bio.sex == binding.sexManRB.text.toString()) {
            binding.sexManRB.isChecked = true
        } else  {
            binding.sexGirlRB.isChecked = true
        }
    }



    override fun showToast(message: String) {
        Toast.makeText(this@EditActivity, message, Toast.LENGTH_SHORT).show()
    }

    override fun finish(context: Context) {
        startActivity(Intent(context, MainActivity::class.java))
        finishAffinity()
    }

    override fun setPresenter(presenter: EditContract.Presenter) {
        mPresenter = presenter
    }

//    fun onRadioButtonClick(): String {
//        if (view is RadioButton) {
//            val checked = view.isChecked
//
//            when (view.id) {
//                R.id.sexManRB -> if (checked) jekel = binding.sexManRB.text.toString()
//                R.id.sexGirlRB -> if (checked) jekel = binding.sexGirlRB.text.toString()
//            }
//        }
//        Log.e("lapar",jekel)
//        return jekel
//    }

    fun getRadioButtun(): String{
        val id =  binding.sexRB.checkedRadioButtonId
        when(id){
            R.id.sexManRB -> jekel = binding.sexManRB.text.toString()
            R.id.sexGirlRB -> jekel = binding.sexGirlRB.text.toString()
        }
        return  jekel
    }

}