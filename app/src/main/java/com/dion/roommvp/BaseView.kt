package com.dion.roommvp

interface BaseView<T> {
    fun setPresenter(presenter: T)

}