package ph.com.southstardrug.www.southstardrug.module

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.view.*
import ph.com.southstardrug.www.southstardrug.R
import ph.com.southstardrug.www.southstardrug.viewmodels.LogInViewModel
import ph.com.southstardrug.www.southstardrug.views.LogInView
import ph.com.southstardrug.www.southstardrug.databinding.ActivityLoginBinding
import ph.com.southstardrug.www.southstardrug.util.GenericTextInputEditTextWatcher
import ph.com.southstardrug.www.southstardrug.util.dismissProgressDialog
import ph.com.southstardrug.www.southstardrug.util.showProgressDialog
class LogInActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    lateinit var activityLoginBinding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        var viewModel = ViewModelProviders.of(this).get(LogInViewModel::class.java)
        activityLoginBinding.viewmodels = viewModel

        activityLoginBinding.setLifecycleOwner(this)

        viewModel.getResultLogin(). observe(this, Observer {
        })
        activityLoginBinding.txtEmail.txtEmail.addTextChangedListener(GenericTextInputEditTextWatcher(activityLoginBinding.txtEmail))
        activityLoginBinding.txtPassword.addTextChangedListener(GenericTextInputEditTextWatcher(activityLoginBinding.txtPassword))
        activityLoginBinding.views = object : LogInView {
            override fun register() {
                startActivity(Intent(this@LogInActivity, RegisterActivity::class.java))
            }
            override fun logIn() {
                var name :String = activityLoginBinding.txtEmail.text.toString()
                var pass :String = activityLoginBinding.txtPassword.text.toString()
                showProgressDialog(getString(R.string.progress_login))
                if (viewModel.validateUname(name)){
                    activityLoginBinding.txtEmail.setError(getString(R.string.field_error))
                    activityLoginBinding.txtEmail.setBackgroundResource(R.drawable.error_textfield)
                    dismissProgressDialog()
                }else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(activityLoginBinding.txtEmail.text.toString()).matches()){
                    activityLoginBinding.txtEmail.setError(getString(R.string.invalid_email))
                    dismissProgressDialog()
                }
                if (viewModel.validateUpass(pass)){
                    activityLoginBinding.txtPassword.setError(getString(R.string.field_error))
                    activityLoginBinding.txtPassword.setBackgroundResource(R.drawable.error_textfield)
                    dismissProgressDialog()
                }
                if (viewModel.validateAccount(name,pass,applicationContext)){

                }else{
                    showToast(getString(R.string.invalid_username_password))
                }
            }
        }
    }
    fun showToast(msg:String) {
        Toast.makeText(this,msg, Toast.LENGTH_LONG).show()
    }
    override fun onBackPressed() {
        val builder = android.support.v7.app.AlertDialog.Builder(this)
        builder.setPositiveButton(getString(R.string.yes)) { dialog, which ->
            val homeIntent = Intent(Intent.ACTION_MAIN)
            homeIntent.addCategory(Intent.CATEGORY_HOME)
            homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(homeIntent)
        }
        builder.setNegativeButton(getString(R.string.cancel), null).setTitle(getString(R.string.exit_msg))
        builder.show()
    }
}


