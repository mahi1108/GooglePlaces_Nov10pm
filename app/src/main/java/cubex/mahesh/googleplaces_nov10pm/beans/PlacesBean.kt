package cubex.mahesh.googleplaces_nov10pm.beans

import com.google.gson.annotations.SerializedName

data class PlacesBean(@SerializedName("next_page_token")
                      val nextPageToken: String = "",
                      @SerializedName("results")
                      val results: List<ResultsItem>?,
                      @SerializedName("status")
                      val status: String = "")