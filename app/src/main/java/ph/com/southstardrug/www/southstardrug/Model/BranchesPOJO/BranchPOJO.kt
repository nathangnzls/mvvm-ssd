package ph.com.southstardrug.www.southstardrug.Model.BranchesPOJO

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class BranchPOJO(
    @SerializedName("status")
    @Expose
    var status: Boolean? = null,
    @SerializedName("message")
    @Expose
    var message: String? = null,
    @SerializedName("branches")
    @Expose
    var branches: List<Branches>? = null,
    @SerializedName("next")
    @Expose
    var next: String? = null,
    @SerializedName("prev")
    @Expose
    var prev: String? = null

) {

    class Deserializer: ResponseDeserializable<BranchPOJO> {
        override fun deserialize(content: String): BranchPOJO? = Gson().fromJson(content, BranchPOJO::class.java)
    }
}











