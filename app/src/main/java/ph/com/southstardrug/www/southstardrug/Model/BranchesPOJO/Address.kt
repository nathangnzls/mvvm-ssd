package ph.com.southstardrug.www.southstardrug.Model.BranchesPOJO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Address(
    @SerializedName("street")
    @Expose
    var branchStreet: String? = null,
    @SerializedName("city")
    @Expose
    var branchCity: String? = null,
    @SerializedName("region")
    @Expose
    var branchRegion: String? = null,
    @SerializedName("zip")
    @Expose
    var branchZIPCode: Int? = null


)