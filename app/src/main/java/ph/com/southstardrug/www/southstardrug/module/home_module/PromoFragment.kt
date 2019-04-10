package ph.com.southstardrug.www.southstardrug.module.home_module

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.provider.Settings
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import kotlinx.coroutines.experimental.DisposableHandle
import ph.com.southstardrug.www.southstardrug.Model.PromoPOJO.Promo
import ph.com.southstardrug.www.southstardrug.Model.PromoPOJO.PromoPOJO
import ph.com.southstardrug.www.southstardrug.R
import ph.com.southstardrug.www.southstardrug.adapter.PromoAdapter
import ph.com.southstardrug.www.southstardrug.databinding.FragmentPromoBinding
import ph.com.southstardrug.www.southstardrug.network.APIClient
import ph.com.southstardrug.www.southstardrug.util.dismissProgressDialog
import ph.com.southstardrug.www.southstardrug.util.showProgressDialog
import ph.com.southstardrug.www.southstardrug.util.EndlessScrollListener
import ph.com.southstardrug.www.southstardrug.viewmodels.LogInViewModel
import ph.com.southstardrug.www.southstardrug.viewmodels.PromoFragmentViewModel


class PromoFragment: Fragment() {

    private var mPromoFragmentBinding: FragmentPromoBinding? = null
    var mLayoutManager: LinearLayoutManager? = null
    var setNextPromoUrl: String = ""
    lateinit var viewModel: PromoFragmentViewModel
    lateinit var mContext: Context
    var promoList1: MutableList<Promo> = mutableListOf()
    var count : Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mPromoFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_promo,container,false)
        val view = mPromoFragmentBinding!!.getRoot()
        viewModel = ViewModelProviders.of(this).get(PromoFragmentViewModel::class.java)
        viewModel.getPromoData(activity!!).observe(this, Observer {promoViewModel ->
                scrollListener.notifyMorePages()
                setNextPromoUrl = promoViewModel!!.next!!
                count = promoList1.size
                promoList1.addAll(promoViewModel!!.promos!!)
                mLayoutManager = LinearLayoutManager(mContext.applicationContext)
                mPromoFragmentBinding!!.recycleView!!.layoutManager = mLayoutManager as RecyclerView.LayoutManager?
                mPromoFragmentBinding!!.recycleView!!.adapter = PromoAdapter(context!!,promoList1)
                mPromoFragmentBinding!!.recycleView!!.adapter!!.notifyDataSetChanged()
                mPromoFragmentBinding!!.itemProgressBar1.setVisibility(View.INVISIBLE)
            mPromoFragmentBinding!!.itemProgressBar1.setVisibility(View.INVISIBLE)

        })
        mPromoFragmentBinding!!.recycleView!!.addOnScrollListener(scrollListener)
        return view
    }
    private val scrollListener = EndlessScrollListener(object : EndlessScrollListener.RefreshList {
        override fun onRefresh(pageNumber: Int) {
            mPromoFragmentBinding!!.itemProgressBar1.setVisibility(View.VISIBLE)
            viewModel.loadMorePromoData(setNextPromoUrl,activity!!).observe(activity!!, Observer {promoViewModel ->

                    if(promoViewModel!!.next!!.equals("")){
                        Toast.makeText(activity,promoViewModel!!.next.toString(),Toast.LENGTH_LONG).show()
                        mPromoFragmentBinding!!.recycleView!!.scrollToPosition(promoList1.size-1)
                    }else{
                        mPromoFragmentBinding!!.recycleView!!.scrollToPosition(count-1)

                    }


            })
        }
    })

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dismissProgressDialog()
        mContext = context
    }

    override fun onDetach() {
        super.onDetach()
        dismissProgressDialog()
        mContext = context!!
    }
}