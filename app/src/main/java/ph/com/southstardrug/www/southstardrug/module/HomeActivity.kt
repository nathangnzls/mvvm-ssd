package ph.com.southstardrug.www.southstardrug.module

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import ph.com.southstardrug.www.southstardrug.R
import ph.com.southstardrug.www.southstardrug.module.home_module.*

class HomeActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setCustomView(R.layout.app_actionbar)
        val bottomNavigationView = findViewById(R.id.navigation) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.itemPromo -> selectedFragment = PromoFragment()
                R.id.itemBranches -> selectedFragment = BranchesFragment()
                R.id.itemProfile -> selectedFragment = ProfileFragment()
                R.id.itemNotification -> selectedFragment = NotificationFragment()
                R.id.itemMore -> selectedFragment = MoreFragment()
            }
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, selectedFragment!!)
            transaction.commit()
            true
        }

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, PromoFragment())
        transaction.commit()
    }
    override fun onBackPressed() {
        val builder = android.support.v7.app.AlertDialog.Builder(this)
        builder.setPositiveButton("Yes") { dialog, which ->
            val homeIntent = Intent(Intent.ACTION_MAIN)
            homeIntent.addCategory(Intent.CATEGORY_HOME)
            homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(homeIntent)
        }
        builder.setNegativeButton("Cancel", null).setTitle("Do you really want to exit?")
        builder.show()
    }
}