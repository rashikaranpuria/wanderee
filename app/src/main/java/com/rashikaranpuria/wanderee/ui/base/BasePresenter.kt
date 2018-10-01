package com.rashikaranpuria.wanderee.ui.base

open class BasePresenter<V : IBaseView> : IBasePresenter<V> {

    var view: V? = null

    // on presenter attach
    // @Param v: View
    // set view locally
    override fun onAttach(v: V) {
        view = v
    }

    // on presenter detach
    // unset local view variable
    override fun onDetach() {
        view = null
    }
}