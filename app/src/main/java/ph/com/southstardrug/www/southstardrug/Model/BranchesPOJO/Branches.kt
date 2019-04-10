package ph.com.southstardrug.www.southstardrug.Model.BranchesPOJO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Branches(

@SerializedName("name")
@Expose
var branchName: String? = null,
@SerializedName("operating_hours")
@Expose
var branchOperatingHours: List<OperatingHours>? = null,
@SerializedName("address")
@Expose
var branchAddress: List<Address>? = null,
@SerializedName("contact")
@Expose
var branchContact: List<Contacts>? = null,
@SerializedName("geotag")
@Expose
var branchGeotag: List<Geotag>? = null

)