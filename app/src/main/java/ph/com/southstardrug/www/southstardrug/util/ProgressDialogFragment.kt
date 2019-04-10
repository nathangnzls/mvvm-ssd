package ph.com.southstardrug.www.southstardrug.util

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.progress_dialog_layout.view.*
import ph.com.southstardrug.www.southstardrug.R

private var customProgressDialog: AlertDialog? = null
fun AppCompatActivity.showProgressDialog(message: String) {
    val progressDialog = LayoutInflater.from(this)
        .inflate(R.layout.progress_dialog_layout, null)
    progressDialog.dialog_title.text = message
    customProgressDialog = AlertDialog.Builder(this, R.style.CustomProgressDialog)
        .createCustomAlertDialog(view = progressDialog, cancelable = false)
    customProgressDialog?.show()
}
fun AppCompatActivity.dismissProgressDialog() {
    customProgressDialog?.dismiss()
    customProgressDialog = null
}
fun Fragment.showProgressDialog(message: String) {
    (activity as? AppCompatActivity)?.showProgressDialog(message)
}
fun Fragment.dismissProgressDialog() {
    (activity as? AppCompatActivity)?.dismissProgressDialog()
}
fun ViewModel.dissmissProgressDialog(){
    customProgressDialog?.dismiss()
    customProgressDialog = null
}
fun dissmissProgressDialog(){
    customProgressDialog?.dismiss()
    customProgressDialog = null
}
fun ViewModel.showProgressDialog(message: String, ctx: Context){
    val progressDialog = LayoutInflater.from(ctx)
            .inflate(R.layout.progress_dialog_layout, null)
    progressDialog.dialog_title.text = message
    customProgressDialog = AlertDialog.Builder(ctx, R.style.CustomProgressDialog)
            .createCustomAlertDialog(view = progressDialog, cancelable = false)
    customProgressDialog?.show()
}
