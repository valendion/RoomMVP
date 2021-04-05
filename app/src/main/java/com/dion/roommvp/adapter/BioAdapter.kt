package com.dion.roommvp.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dion.roommvp.databinding.ItemBiodataBinding
import com.dion.roommvp.model.Biodata
import com.dion.roommvp.view.EditActivity

class BioAdapter: RecyclerView.Adapter<BioAdapter.BioViewHolder>() {
    private var dataList: MutableList<Biodata> = arrayListOf()
    inner class BioViewHolder( val binding: ItemBiodataBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(bio: Biodata){
            binding.nikTV.text = bio.nik
            binding.nameTV.text = bio.name

            itemView.setOnClickListener{
                it.context.startActivity(Intent(it.context, EditActivity::class.java).putExtra("nik",bio.nik))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BioViewHolder {
        return BioViewHolder(binding = ItemBiodataBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BioViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int{
       return dataList.size
    }

    fun setValue(data: MutableList<Biodata>){
        this.dataList.clear()
        this.dataList.addAll(data)
        Log.e("dataADapter", data.toString())
        notifyDataSetChanged()
    }

}