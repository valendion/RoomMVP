package com.dion.roommvp.contract

import com.dion.roommvp.BasePresenter
import com.dion.roommvp.BaseView
import com.dion.roommvp.model.Biodata

interface BiodataContract {

    interface PresenterMain: BasePresenter{
        fun getAll(): MutableList<Biodata>
    }

    interface ViewMain: BaseView<PresenterMain>{
        fun showData(data: MutableList<Biodata>)
    }


}