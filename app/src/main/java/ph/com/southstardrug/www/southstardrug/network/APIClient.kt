package ph.com.southstardrug.www.southstardrug.network
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.util.FuelRouting
import ph.com.southstardrug.www.southstardrug.util.BASE_URL
import ph.com.southstardrug.www.southstardrug.util.BRANCHES_LIST_INITIAL_PAGE
import ph.com.southstardrug.www.southstardrug.util.PROMOS_LIST_INITIAL_PAGE

sealed class APIClient: FuelRouting {
    override val basePath = BASE_URL
    class getPromo(): APIClient() {
        override val body: String? = null
        override val bytes: ByteArray? = null
    }
    class getBranches(): APIClient() {
        override val body: String? = null
        override val bytes: ByteArray? = null
    }
    override val method: Method
        get() {
            when(this) {
                is getPromo -> return Method.GET
                is getBranches -> return Method.GET
            }
            return Method.GET
        }
    override val path: String
        get() {
            return when(this) {
                is getPromo -> PROMOS_LIST_INITIAL_PAGE
                is getBranches -> BRANCHES_LIST_INITIAL_PAGE

            }
        }
    override val params: List<Pair<String, Any?>>?
        get() {
            return when(this) {
                is getPromo -> return emptyList()
                is getBranches -> return emptyList()
            }
        }
    override val headers: Map<String, String>?
        get() {
            return null
        }
}
