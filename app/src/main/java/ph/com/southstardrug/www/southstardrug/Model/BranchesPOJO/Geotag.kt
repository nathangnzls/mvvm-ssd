package ph.com.southstardrug.www.southstardrug.Model.BranchesPOJO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Geotag(
    @SerializedName("lat")
    @Expose
    var lat: Double?,
    @SerializedName("lon")
    @Expose
    var lon: Double?
)