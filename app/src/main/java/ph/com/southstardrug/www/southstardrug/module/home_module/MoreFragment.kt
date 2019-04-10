package ph.com.southstardrug.www.southstardrug.module.home_module
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_more.*
import kotlinx.android.synthetic.main.fragment_more.view.*
import ph.com.southstardrug.www.southstardrug.R
import ph.com.southstardrug.www.southstardrug.generated.callback.OnClickListener
import ph.com.southstardrug.www.southstardrug.module.HomeActivity
import ph.com.southstardrug.www.southstardrug.module.LogInActivity
import ph.com.southstardrug.www.southstardrug.util.SessionManager
import android.databinding.adapters.TextViewBindingAdapter.setText



class MoreFragment: Fragment() {
    lateinit var session: SessionManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, args: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_more, container, false)

        view.btnLogout.setOnClickListener{
           session = SessionManager(activity!!)
            session.logout()
            val intent2 = Intent(activity, LogInActivity::class.java)
            startActivity(intent2)
        }

        return view
    }


}
