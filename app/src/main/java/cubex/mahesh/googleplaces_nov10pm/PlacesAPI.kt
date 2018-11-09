package cubex.mahesh.googleplaces_nov10pm

import cubex.mahesh.googleplaces_nov10pm.beans.PlacesBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesAPI {

    @GET("maps/api/place/nearbysearch/json?key=AIzaSyDdCGdR2cnWw0AB0LeN3jOTjKmkKag4tew")
    fun getPlaces(
            @Query("location") loc:String,
            @Query("radius") radius:String,
            @Query("type") type:String) : Call<PlacesBean>

}