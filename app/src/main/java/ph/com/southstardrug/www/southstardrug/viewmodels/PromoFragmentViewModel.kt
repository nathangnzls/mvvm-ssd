package ph.com.southstardrug.www.southstardrug.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.os.Handler
import android.provider.Settings.Global.getString
import android.util.Log
import android.view.View
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import kotlinx.coroutines.experimental.DisposableHandle
import ph.com.southstardrug.www.southstardrug.Model.PromoPOJO.Promo
import ph.com.southstardrug.www.southstardrug.Model.PromoPOJO.PromoPOJO
import ph.com.southstardrug.www.southstardrug.R
import ph.com.southstardrug.www.southstardrug.adapter.PromoAdapter
import ph.com.southstardrug.www.southstardrug.module.home_module.PromoFragment
import ph.com.southstardrug.www.southstardrug.network.APIClient
import ph.com.southstardrug.www.southstardrug.util.dissmissProgressDialog
import ph.com.southstardrug.www.southstardrug.util.showProgressDialog

class PromoFragmentViewModel: ViewModel{

    var resultdata =  MutableLiveData<PromoPOJO>()
    constructor():super()


    fun getPromoData(ctx: Context): MutableLiveData<PromoPOJO>{
        showProgressDialog(ctx.getString(R.string.fetching_promo), ctx)
        Fuel.request(APIClient.getPromo())
            .responseObject(PromoPOJO.Deserializer()){ request, response, result ->
                result.fold(success = {promopj ->
                   // resultdata.addAll()
                    dissmissProgressDialog()
                    resultdata.value = promopj

                }, failure = { error ->
                    dissmissProgressDialog()
                    Toast.makeText(ctx,ctx.getString(R.string.fetch_data_failed), Toast.LENGTH_LONG).show()
                })
            }
        return resultdata
    }


    fun loadMorePromoData(url: String, ctx: Context): MutableLiveData<PromoPOJO>{
            if (!url.isEmpty()){
                Fuel.get(url)
                        .responseObject(PromoPOJO.Deserializer()){ request, response, result ->
                            result.fold(success = {promopj ->
                                dissmissProgressDialog()
                                resultdata.value = promopj
                            }, failure = { error ->
                                dissmissProgressDialog()
                                Toast.makeText(ctx,ctx.getString(R.string.fetch_data_failed), Toast.LENGTH_LONG).show()
                            })
                        }
            }else{
                Toast.makeText(ctx,ctx.getString(R.string.fetching_promos_complete), Toast.LENGTH_LONG).show()
            }


        return resultdata
    }

    override fun onCleared() {
        super.onCleared()
    }
}
