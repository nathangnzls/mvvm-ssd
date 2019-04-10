package ph.com.southstardrug.www.southstardrug.Model.PromoPOJO
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class PromoPOJO {
    @SerializedName("status")
    @Expose
    var status: Boolean? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("promos")
    @Expose
    var promos: List<Promo>? = null
    @SerializedName("next")
    @Expose
    var next: String? = null
    @SerializedName("prev")
    @Expose
    var prev: String? = null
    class Deserializer : ResponseDeserializable<PromoPOJO> {
        override fun deserialize(content: String) = Gson().fromJson(content, PromoPOJO::class.java)
    }

}

