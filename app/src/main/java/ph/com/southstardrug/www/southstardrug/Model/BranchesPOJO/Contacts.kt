package ph.com.southstardrug.www.southstardrug.Model.BranchesPOJO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Contacts(
    @SerializedName("phone")
    @Expose
    var phone: String,
    @SerializedName("mobile")
    @Expose
    var mobile: String

)