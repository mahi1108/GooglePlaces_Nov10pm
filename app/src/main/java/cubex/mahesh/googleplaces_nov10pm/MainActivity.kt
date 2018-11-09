package cubex.mahesh.googleplaces_nov10pm

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SeekBar
import cubex.mahesh.googleplaces_nov10pm.beans.PlacesBean
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCurrentPosition( )

        sbar.setOnSeekBarChangeListener(
                object:SeekBar.OnSeekBarChangeListener{
                    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                            value.text = p1.toString()
                    }
                    override fun onStartTrackingTouch(p0: SeekBar?) {
                    }
                    override fun onStopTrackingTouch(p0: SeekBar?) {
                    }
                })

        getPlaces.setOnClickListener {

            var r = Retrofit.Builder().
                    baseUrl("https://maps.googleapis.com/").
                    addConverterFactory(GsonConverterFactory.create()).
                    build()
            var api = r.create(PlacesAPI::class.java)
            var call = api.getPlaces("${tv1.text},${tv2.text}",
                    value.text.toString(),sp1.selectedItem.toString())
            call.enqueue(object : Callback<PlacesBean> {

                override fun onResponse(call: Call<PlacesBean>,
                                        response: Response<PlacesBean>) {
                    var bean = response.body()
                    var list = bean!!.results
                    var temp_list = mutableListOf<String>()
                    for ( item in list!!)
                    {
                        temp_list.add(item.name+"\n"+
                                    item.vicinity+"\n"+
                                    item.openingHours+"\n"+
                                    item.rating)
                    }
                    var adapter = ArrayAdapter<String>(this@MainActivity,
                            R.layout.indiview,temp_list)
                    lview.adapter = adapter

                }
                override fun onFailure(call: Call<PlacesBean>, t: Throwable) {
               }
            })



        }
    } // onCreate( )

    fun getCurrentPosition( )
    {

        var lManager = getSystemService(Context.LOCATION_SERVICE)
                                        as LocationManager
        lManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                1000.toLong(),
                1.toFloat(), object : LocationListener {
            override fun onLocationChanged(p0: Location?) {
                    tv1.text = p0!!.latitude.toString()
                    tv2.text = p0!!.longitude.toString()
            }

            override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
            }

            override fun onProviderEnabled(p0: String?) {
            }

            override fun onProviderDisabled(p0: String?) {
            }
        }
        )

    }
}
