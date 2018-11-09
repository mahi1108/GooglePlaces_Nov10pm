package cubex.mahesh.googleplaces_nov10pm.beans

import com.google.gson.annotations.SerializedName

data class OpeningHours(@SerializedName("open_now")
                        val openNow: Boolean = false)