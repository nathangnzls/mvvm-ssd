package ph.com.southstardrug.www.southstardrug.util

import android.graphics.Color
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import ph.com.southstardrug.www.southstardrug.R

open class GenericTextInputEditTextWatcher(tfName: EditText) : TextWatcher{
    var etName: EditText = tfName
    var finpass: String = ""

    override fun afterTextChanged(s: Editable?) {validate()}
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {validate()}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        validate()
    }
    fun validate(){
        when {
            etName.id == R.id.txtEmail -> {
                when{
                    !android.util.Patterns.EMAIL_ADDRESS.matcher(etName.text.toString()).matches() ->{
                        etName.setError("Invalid Email Address")
                        etName.setBackgroundResource(R.drawable.default_textfield)}
                    else -> {
                        etName.setBackgroundResource(R.drawable.default_textfield)
                    }
                }
            }

            etName.id == R.id.txtPassword -> {

                when{
                    etName.text.toString().isEmpty() -> {etName.setError("This field is required")
                        etName.setBackgroundResource(R.drawable.default_textfield)
                    }
                    else -> {
                        etName.setBackgroundResource(R.drawable.default_textfield)
                    }
                }
            }
            etName.id == R.id.txtfieldUname -> {
                when {
                    etName.text.toString().isEmpty() -> etName.setError("This field is required")
                    etName.text.toString().length == 2 -> etName.setError("Username should be atleast 6 characters above")
                    etName.text.toString().length == 4 -> etName.setError("2 more characters almost there")
                    etName.text.toString().length >= 6 -> {
                    }
                }
            }
            etName.id == R.id.txtfieldUemail -> {
                when {
                    etName.text.toString().isEmpty() -> etName.setError("This field is required")
                    !android.util.Patterns.EMAIL_ADDRESS.matcher(etName.text.toString()).matches() -> etName.setError("Invalid Email Address")
                    else -> {
                    }
                }
            }
            etName.id == R.id.txtfieldUpass -> {
                when {
                    etName.text.toString().isEmpty() -> etName.setError("This field is required")
                    etName.text.toString().length >= 4 -> {
                        when {
                            etName.text.toString().length < 8 -> etName.setError("Password should be atleast 8 characters above")
                            else -> {
                                finpass = etName.text.toString()
                            }
                        }
                    }
                }
            }
            etName.id == R.id.txtfieldUcpass -> {
                when {
                    etName.text.toString().isEmpty() -> etName.setError("This field is required")
                    etName.text.toString().length >= 4 -> {
                        etName.text.toString()
                        when {
                            !finpass.equals(etName.text.toString()) ->  etName.setError("Your password and confirmation password do not matched")
                            else -> {
                            }
                        }
                    }
                }
            }
            else -> {
            }
        }
    }
}