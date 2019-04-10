package ph.com.southstardrug.www.southstardrug.module
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_registration.view.*
import ph.com.southstardrug.www.southstardrug.R
import ph.com.southstardrug.www.southstardrug.R.id.*
import ph.com.southstardrug.www.southstardrug.databinding.ActivityRegistrationBinding
import ph.com.southstardrug.www.southstardrug.util.GenericTextInputEditTextWatcher
import ph.com.southstardrug.www.southstardrug.util.showProgressDialog
import ph.com.southstardrug.www.southstardrug.viewmodels.RegisterViewModel
import ph.com.southstardrug.www.southstardrug.views.RegisterView
class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var activityRegistrationBinding : ActivityRegistrationBinding
        activityRegistrationBinding = DataBindingUtil.setContentView(this, R.layout.activity_registration)
        var viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        activityRegistrationBinding.viewmodels = viewModel
        activityRegistrationBinding.setLifecycleOwner(this)
        var finName: String = ""
        var finEmail: String = ""
        var finPass: String = ""
        var finCpass: String = ""

        activityRegistrationBinding.txtfieldUname.addTextChangedListener(GenericTextInputEditTextWatcher(activityRegistrationBinding.txtfieldUname))
        activityRegistrationBinding.txtfieldUemail.addTextChangedListener(GenericTextInputEditTextWatcher(activityRegistrationBinding.txtfieldUemail))
        activityRegistrationBinding.txtfieldUpass.addTextChangedListener(GenericTextInputEditTextWatcher(activityRegistrationBinding.txtfieldUpass))
//        activityRegistrationBinding.txtfieldUcpass.addTextChangedListener(GenericTextInputEditTextWatcher(activityRegistrationBinding.txtfieldUcpass))
        activityRegistrationBinding.txtfieldUpass.txtfieldUpass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(perpass: Editable?) {

                finPass = perpass.toString()
                activityRegistrationBinding.txtfieldUpass.setBackgroundResource(R.drawable.default_textfield)
                when {
                    viewModel.validateUpass(perpass.toString()) ->  {
                        activityRegistrationBinding.txtfieldUpass.setError(getString(R.string.reg_password_warning))
                    }
                    else -> {
                       // activityRegistrationBinding.upassWrapper.isErrorEnabled = false
                        finPass = perpass.toString()
//                        pass = perpass.toString()
                    }
                }

            }
            override fun onTextChanged(perpass: CharSequence?, start: Int, before: Int, count: Int) {
                finPass = perpass.toString()
                activityRegistrationBinding.txtfieldUpass.setBackgroundResource(R.drawable.default_textfield)
                when {
                    viewModel.validateUpass(perpass.toString()) ->  {
                        activityRegistrationBinding.txtfieldUpass.setError(getString(R.string.reg_password_warning))
                    }
                    else -> {
                        finPass = perpass.toString()
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
        })
        activityRegistrationBinding.txtfieldUcpass.txtfieldUcpass.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                activityRegistrationBinding.txtfieldUcpass.setBackgroundResource(R.drawable.default_textfield)
                when {
                    s.toString().length >= 4 -> {
                        finCpass = s.toString()
                        when {
                            viewModel.validateCUPass(finPass, s.toString()) -> activityRegistrationBinding.txtfieldUcpass.setError(getString(R.string.password_not_match))
                            else -> {
                               // activityRegistrationBinding.ucpassWrapper.isErrorEnabled = false
                                finCpass = s.toString()
                            }
                        }
                    }
                }

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                activityRegistrationBinding.txtfieldUcpass.setBackgroundResource(R.drawable.default_textfield)
                when {
                    s.toString().length >= 4 -> {
                        finCpass = s.toString()
                        when {
                            viewModel.validateCUPass(finPass, s.toString()) -> activityRegistrationBinding.txtfieldUcpass.setError(getString(R.string.password_not_match))
                            else -> {
                                // activityRegistrationBinding.ucpassWrapper.isErrorEnabled = false
                                finCpass = s.toString()
                            }
                        }
                    }
                }
                }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
        })
        activityRegistrationBinding.views = object : RegisterView {
            override fun register() {
                var name :String = activityRegistrationBinding.txtfieldUname.text.toString()
                var email : String = activityRegistrationBinding.txtfieldUemail.text.toString()
                var cpass: String = activityRegistrationBinding.txtfieldUcpass.text.toString()
                var pass :String =  activityRegistrationBinding.txtfieldUpass.text.toString()
                if(!name.isNullOrBlank() && name.length >= 6) {
                    activityRegistrationBinding.txtfieldUname.setBackgroundResource(R.drawable.default_textfield)
                    finName = name
                } else {
                    setErrorToET(activityRegistrationBinding.txtfieldUname)
                }
                if(!email.isNullOrBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() ) {
                    activityRegistrationBinding.txtfieldUemail.setBackgroundResource(R.drawable.default_textfield)
                    finEmail = email
                } else {
                    setErrorToET(activityRegistrationBinding.txtfieldUemail)
                }
                if(!pass.isNullOrBlank() && pass.length > 7) {
                    activityRegistrationBinding.txtfieldUpass.setBackgroundResource(R.drawable.default_textfield)
                    finPass = pass
                } else {
                    setErrorToET(activityRegistrationBinding.txtfieldUpass)
                }
                if(!cpass.isNullOrBlank() && finPass.equals(cpass)) {
                    activityRegistrationBinding.txtfieldUcpass.setBackgroundResource(R.drawable.default_textfield)
                    finCpass = cpass
                } else {
                    setErrorToET(activityRegistrationBinding.txtfieldUcpass)
                }

                if(!finName.isNullOrBlank() && !finEmail.isNullOrBlank() && !finPass.isNullOrBlank()) {
                    if(!viewModel.validateCUPass(finPass, finCpass)) {
                        showProgressDialog(getString(R.string.loading))
                        viewModel.registerFinalData(finName, finEmail, finPass , applicationContext)
                    }
                }
                else {
                    showToast(getString(R.string.emptyfields))
                }
            }
            override fun backtoLogin() {
                startActivity(Intent(this@RegisterActivity, LogInActivity::class.java))
            }
        }
    }
    fun showToast(msg:String) {
        Toast.makeText(this,""+msg, Toast.LENGTH_LONG).show()
    }
    fun setErrorToET(etName: EditText){
        etName.setError(getString(R.string.field_error))
        etName.setBackgroundResource(R.drawable.error_textfield)
    }
}