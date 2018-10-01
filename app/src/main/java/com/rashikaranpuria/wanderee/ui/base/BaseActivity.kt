package com.rashikaranpuria.wanderee.ui.base

import android.support.v7.app.AppCompatActivity
import android.widget.Toast

open class BaseActivity : AppCompatActivity(), IBaseView {

    // show text at errId inside toast
    override fun error(errId: Int) {
        Toast.makeText(this, "ERROR: ${resources.getString(errId)}", Toast.LENGTH_SHORT).show()
    }

    // show text stored at msgId inside of toast
    override fun msg(msgId: Int) {
        Toast.makeText(this, "Message: ${resources.getString(msgId)}", Toast.LENGTH_SHORT).show()
    }

    // show error inside of toast
    override fun error(err: String) {
        Toast.makeText(this, "ERROR: $err", Toast.LENGTH_SHORT).show()
    }

    // show message inside of toast
    override fun msg(msg: String) {
        Toast.makeText(this, "Message: $msg", Toast.LENGTH_SHORT).show()
    }
}