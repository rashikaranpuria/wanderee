package com.rashikaranpuria.wanderee.ui.base

interface IBasePresenter<V : IBaseView> {

    // on presenter attached
    // @Param: v view to be attached
    fun onAttach(v: V)

    // on presenter detached
    fun onDetach()
}