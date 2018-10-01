package com.rashikaranpuria.wanderee.ui.base

interface IBaseView {
    // error func to show error string
    fun error(err: String)
    // error func to show error text stored at errorId
    fun error(errId: Int)
    // msg func to show message string
    fun msg(msg: String)
    // msg func to show text stored at id msg in android resources
    fun msg(msgId: Int)
}