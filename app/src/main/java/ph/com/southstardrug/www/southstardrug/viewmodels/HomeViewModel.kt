package ph.com.southstardrug.www.southstardrug.viewmodels

import android.arch.lifecycle.ViewModel
import android.content.Context
import ph.com.southstardrug.www.southstardrug.util.SessionManager
class HomeViewModel: ViewModel{
    constructor() : super()
    lateinit var session: SessionManager
    fun logOut(ctx: Context){
        session= SessionManager(ctx)
        session.logout()
    }
}