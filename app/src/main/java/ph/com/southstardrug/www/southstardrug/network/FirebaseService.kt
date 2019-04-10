package ph.com.southstardrug.www.southstardrug.network

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ph.com.southstardrug.www.southstardrug.module.HomeActivity
import ph.com.southstardrug.www.southstardrug.module.LogInActivity
import ph.com.southstardrug.www.southstardrug.util.SessionManager
import ph.com.southstardrug.www.southstardrug.util.dissmissProgressDialog
import ph.com.southstardrug.www.southstardrug.R


val mAuth = FirebaseAuth.getInstance()
lateinit var mDatabase: DatabaseReference
fun registerToFirebase(finUemail: String, finUpass: String, finUname: String, ctx: Context) {
    lateinit var session: SessionManager
        mDatabase = FirebaseDatabase.getInstance().getReference("users")
        mAuth.createUserWithEmailAndPassword(finUemail, finUpass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val LoginIntent = Intent(ctx, LogInActivity::class.java)
                    var user = mAuth.currentUser
                    val uid = user!!.uid
                    mDatabase.child(uid).child("email").setValue(finUemail)
                    mDatabase.child(uid).child("username").setValue(finUname)
//                user?.sendEmailVerification()
//                    ?.addOnCompleteListener { task ->
//                        if (task.isSuccessful) {
//
//                        }
//                    }
                    Toast.makeText(ctx, R.string.registration_success, Toast.LENGTH_LONG).show()
                    startActivity(ctx, LoginIntent, null)
                    dissmissProgressDialog()
                } else {
                    var errorCode = task.exception
                    dissmissProgressDialog()
                    when (errorCode){
                        is FirebaseNetworkException ->{Toast.makeText(ctx,R.string.network_error, Toast.LENGTH_LONG).show()}
                        is FirebaseAuthUserCollisionException -> {Toast.makeText(ctx,R.string.user_exist, Toast.LENGTH_LONG).show()}
                        is FirebaseAuthInvalidCredentialsException ->  {Toast.makeText(ctx,R.string.incorrect_email_format, Toast.LENGTH_LONG).show()}
                        else -> {
                            Toast.makeText(ctx, "Error : "+ task.getException()!!.message.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
}
fun validateLogin(uname: String, upass: String, ctx: Context): Boolean {
    val firebaseService = FirebaseAuth.getInstance()
    var session = SessionManager(ctx)
    firebaseService.signInWithEmailAndPassword(uname, upass)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                dissmissProgressDialog()
                val intent2 = Intent(ctx, HomeActivity::class.java)
                session.createLoginSession(true)
                dissmissProgressDialog()
                Toast.makeText(ctx,R.string.log_in_success, Toast.LENGTH_LONG).show()
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(ctx,intent2,null)
               true
            } else {
                var errorCode = task.exception
                dissmissProgressDialog()
                when (errorCode){
                    is FirebaseAuthInvalidCredentialsException ->{Toast.makeText(ctx, R.string.invalid_username_password, Toast.LENGTH_LONG).show()}
                    is FirebaseAuthInvalidUserException ->{Toast.makeText(ctx,R.string.user_not_found, Toast.LENGTH_LONG).show()}
                    is FirebaseNetworkException ->{Toast.makeText(ctx,R.string.network_error, Toast.LENGTH_LONG).show()}
                    else -> {
                        Toast.makeText(ctx, "Error : "+ task.getException()!!.message.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    return false
    }





