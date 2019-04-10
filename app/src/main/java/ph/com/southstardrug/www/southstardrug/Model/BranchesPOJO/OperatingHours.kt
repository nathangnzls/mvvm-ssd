package ph.com.southstardrug.www.southstardrug.Model.BranchesPOJO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class OperatingHours(
    @SerializedName("opening")
    @Expose
    var opening: String?,
    @SerializedName("closing")
    @Expose
    var closing: String?

)