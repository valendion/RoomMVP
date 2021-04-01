package com.dion.roommvp.view

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
    lateinit var jekel: String
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
            onRadioButtonClick(binding.root)
            if (binding.simpanUpdateBtn.text.toString() == "Simpan") {
                bio.nik = binding.nikInputTIL.editText?.text.toString()
                bio.name = binding.nameInputTIL.editText?.text.toString()
                bio.job = binding.jobInputTIL.editText?.text.toString()
                bio.sex = jekel

                mPresenter.add(bio)

            } else if(binding.simpanUpdateBtn.text.toString() == "Update") {
                bio.nik = binding.nikInputTIL.editText?.text.toString()
                bio.name = binding.nameInputTIL.editText?.text.toString()
                bio.job = binding.jobInputTIL.editText?.text.toString()
                bio.sex = jekel

                mPresenter.update(bio)

                Log.d("update", "${bio.job.toString()} ${bio.name.toString()} ${bio.sex.toString()} ${bio.nik.toString()} ")
            }

        }

        binding.deleteBtn.setOnClickListener {
            onRadioButtonClick(binding.root)

            bio.nik = binding.nikInputTIL.editText?.text.toString()
            bio.name = binding.nameInputTIL.editText?.text.toString()
            bio.job = binding.jobInputTIL.editText?.text.toString()
            bio.sex = jekel

            mPresenter.delete(bio)

            Log.d("update", "${bio.job.toString()} ${bio.name.toString()} ${bio.sex.toString()} ${bio.nik.toString()} ")

        }

    }


    override fun changeMode(status: String) {
            binding.simpanUpdateBtn.text = resources.getString(R.string.update)
            binding.deleteBtn.visibility = View.VISIBLE
    }

    override fun biodataPerson(bio: Biodata) {
        binding.nikInputTIL.editText?.setText(bio.nik)
        binding.nameInputTIL.editText?.setText(bio.name)
        binding.jobInputTIL.editText?.setText(bio.job)
        binding.nikInputTIL.isEnabled = false

        Log.d("laki", "${bio.sex} ${binding.sexManRB.text.toString()}")
        Log.d("perem", "${bio.sex} ${binding.sexGirlRB.text.toString()}")

        if (bio.sex == binding.sexManRB.text.toString()) {
            binding.sexManRB.isChecked = true
        } else if (bio.sex == binding.sexGirlRB.text.toString()) {
            binding.sexGirlRB.isChecked = true
        }else{
            binding.sexManRB.isChecked = false
            binding.sexGirlRB.isChecked = false

        }

    }

    override fun showToast(message: String) {
        Toast.makeText(this@EditActivity, message, Toast.LENGTH_SHORT).show()
    }

    override fun setPresenter(presenter: EditContract.Presenter) {
        mPresenter = presenter
    }

    fun onRadioButtonClick(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.id) {
                R.id.sexManRB -> if (checked) jekel = binding.sexManRB.text.toString()
                R.id.sexGirlRB -> if (checked) jekel = binding.sexGirlRB.text.toString()
            }
        }
    }
}