package ph.com.southstardrug.www.southstardrug.viewmodels
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableField
import ph.com.southstardrug.www.southstardrug.network.registerToFirebase
class RegisterViewModel: ViewModel{
    var  username =    ObservableField("")
    var  useremail = ObservableField("")
    var  userpass =    ObservableField("")
    var  userconfirmpass =    ObservableField("")
    var finUemail: String = ""
    var finUpass: String = ""
    var finUname: String = ""
    constructor():super()
    fun validateEmptyUname(uname: String): Boolean {
        var result: String = ""
        username.set(uname)
        if(username.get().isNullOrBlank()) {
            return true
        }
        finUname = uname
        return false
    }
    fun validateUname(uname: String): Boolean {
        var result: String = ""
        username.set(uname)
        if(uname.length < 6) {
            return true
        }
        finUname = uname
        return false
    }


    fun validateUemail(uemail: String): Boolean {
        var result: String = ""
        useremail.set(uemail)
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(uemail).matches()) {
            return true
        }
        finUemail = uemail
        return false
    }
    fun validateEmptyUmail(uemail: String): Boolean {
        var result: String = ""
        useremail.set(uemail)
        if(useremail.get().isNullOrBlank()){
            return true
        }
        return false
    }
    fun validateUpass(upass: String): Boolean {
        var result: String = ""
        userpass.set(upass)
        if(upass.length < 8) {

            return true
        }
        finUpass = upass
        return false
    }
    fun validateEmptyUpass(upass: String): Boolean {
        var result: String = ""
        userpass.set(upass)
        if(userpass.get().isNullOrBlank()) {
            return true
        }
        finUpass = upass
        return false
    }
    fun validateCUPass(upass: String, cupass: String): Boolean {
        var result: String = ""
        userconfirmpass.set(cupass)
        userpass.set(upass)
        if(!upass.equals(cupass)){
            print(upass)
            return true
        }
        return false
    }
    fun validateEmptyCUpass(cupass: String): Boolean {
        var result: String = ""
        userconfirmpass.set(cupass)
        if(userconfirmpass.get().isNullOrBlank()) {
            return true
        }
        return false
    }
    fun registerFinalData(fusername: String, femail: String, fpassword: String, ctx: Context):Boolean{
        username.set(fusername)
        useremail.set(femail)
        userpass.set(fpassword)

        if(!(username.get().isNullOrBlank()&&!(useremail.get().isNullOrBlank())&&!(userpass.get().isNullOrBlank()))){
            registerToFirebase(femail, fpassword,fusername, ctx)
        }
        return false
    }

}