package cubex.mahesh.googleplaces_nov10pm.beans

import com.google.gson.annotations.SerializedName

data class PlusCode(@SerializedName("compound_code")
                    val compoundCode: String = "",
                    @SerializedName("global_code")
                    val globalCode: String = "")