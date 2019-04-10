package ph.com.southstardrug.www.southstardrug.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableField
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import ph.com.southstardrug.www.southstardrug.network.validateLogin

class LogInViewModel : ViewModel{
    private var mAuth: FirebaseAuth? = null
    var  username =    ObservableField("")
    var  userpass =    ObservableField("")
    var resultdata =   MutableLiveData<String>()
    constructor() : super()
    fun validateUname(uname: String): Boolean {
        username.set(uname)
        if(username.get().isNullOrBlank()){
            return true
        }else{
            validateUemail(username.get().toString())
        }
        return false
    }
    fun validateUpass(upass: String): Boolean {
        userpass.set(upass)
        if(userpass.get().isNullOrBlank()){
            return true
        }
        return false
    }
    fun validateAccount(uname: String, upass: String, ctx: Context): Boolean {
        var result: String = ""
        userpass.set(upass)
        username.set(uname)
        if(!(userpass.get().isNullOrBlank())&&!(username.get().isNullOrBlank())){
            if(validateLogin(username.get().toString(),userpass.get().toString(), ctx)){
            }
        }else{

        }
        return true
    }
    fun getResultLogin(): MutableLiveData<String> {
        Log.d("ABCd","a")
        return  resultdata
    }
    fun validateUemail(uemail: String): Boolean {
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(uemail).matches()) {
            return true
        }
        return false
    }
}
