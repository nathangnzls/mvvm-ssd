package ph.com.southstardrug.www.southstardrug.Model.PromoPOJO
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class Promo {
    @SerializedName("image_url")
    @Expose
    var imageUrl: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("desc")
    @Expose
    var desc: String? = null
    @SerializedName("due")
    @Expose
    var due: String? = null
    @SerializedName("date_posted")
    @Expose
    var datePosted: Int? = null
}
