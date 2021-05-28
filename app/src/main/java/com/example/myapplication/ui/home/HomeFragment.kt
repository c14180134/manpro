package com.example.myapplication.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.myapplication.*
import com.example.myapplication.R
import com.example.myapplication.datalokasi.Location
import com.example.myapplication.datalokasi.LocationViewModel
import com.example.myapplication.ui.listsaveloc.ListlocAdapter
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_home.*
import java.lang.Math.*


class HomeFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var cameraPosition:CameraPosition? = null
    var latd:Double??= 0.0
    var longd:Double?? = 0.0
    var latitud:Double??=0.0
    var longitud:Double??=0.0
    lateinit var mLocationViewModel: LocationViewModel
    private val LOCATION_PERMISSION_REQUEST = 1
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private var LocList = emptyList<Location>()
    var x:Int = 0

    var listnear:MutableList<String> = mutableListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         x=0

        //fungsi get database ke fragment
        mLocationViewModel = ViewModelProvider(this).get(LocationViewModel::class.java)
        val adapter = ListlocAdapter()

        mLocationViewModel.readAllData.observe(viewLifecycleOwner, Observer { location ->
            //penggunaan yang berkaitan dengan database dan tidak dapat dilakukan di onviewcreated karena list yang diisi belum didapatkan dapat dilakukan didalam observer
            //seperti embark jika ditaruh dibagian luar observer maka embark tidak dapat berjalan karena tidak ada data yang disalurkan ke LocList akan tetapi dapat ditaruh di bagian
            //di bagian setonClicklistener yang berada pada onviewcreated karena data sudah dapat dipass
            adapter.setData(location)
            LocList = adapter.getList()


            embark()
        })

        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as  SupportMapFragment
        mapFragment.getMapAsync(this)
        val fab: FloatingActionButton =requireView().findViewById<FloatingActionButton>(R.id.fab)
        //fungsi floating pada home fragment
        fab.setOnClickListener {
            //fungsi disini memanggil fungsi sebelumnya untung mengambil longitude latitude dari lokasi sekarang untuk di intent ke halaman save
            getLocationUpdates()
            mLocationViewModel.readAllData.observe(viewLifecycleOwner, Observer { location ->
                adapter.setData(location)
                var total=0
                var indexjs=0
                LocList = adapter.getList()
                while (indexjs<LocList.size){
                    if(LocList[indexjs].Longitude==longd && LocList[indexjs].Latitude ==latd){
                        Log.d("latitude", latd.toString())
                        Log.d("longitude", longd.toString())
                        total++
                    }
                    indexjs++
                }
                if(total!=0){
                    Toast.makeText(requireContext(),
                            "Sudah Ada tempat yang tersimpan disini",Toast.LENGTH_LONG).show()
                }
                else{
                    val intent = Intent(activity, ActivityLocSave::class.java)
                    intent.putExtra("lat", this.latd)
                    intent.putExtra("long", this.longd)


                    startActivity(intent)
                }

                Log.d("total", total.toString())
            })

            Log.d("latitude", latd.toString())
            Log.d("longitude", longd.toString())



        }
        var index=0
        fabnear.setOnClickListener {

         while (index<adapter.itemCount-1){
             var lon1 = toRadians(longd!!)
             var lon2 = toRadians(LocList[index].Longitude)
             var lat1 = toRadians(latd!!)
             var lat2 = toRadians(LocList[index].Latitude)

             // Haversine formula

             // Haversine formula
             var dlon= lon2 - lon1
             var dlat= lat2 - lat1
             var a = (pow(sin(dlat / 2), 2.0)
                     + (cos(lat1) * cos(lat2)
                     * pow(sin(dlon / 2), 2.0)))

             var c = 2 * asin(sqrt(a))

             // Radius of earth in kilometers. Use 3956
             // for miles

             // Radius of earth in kilometers. Use 3956
             // for miles
             var r = 6371.0*c

             if(r<10){
                 Log.d("final",index.toString()+LocList[index].Name)

                 listnear.add(LocList[index].Name)
             }
             // calculate the result

             // calculate the result
             Log.d("final", r.toString())
             index++
         }
                if(listnear.size!=0){
                    Toast.makeText(requireContext(),
                            "Location Near YOU "+listnear,Toast.LENGTH_LONG).show()
                }
            else{
                    Toast.makeText(requireContext(),
                            "There is no Location near you",Toast.LENGTH_LONG).show()
                }



        }


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun getLocationAccess(){
        if(ContextCompat.checkSelfPermission(
                        requireContext(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                )==PackageManager.PERMISSION_GRANTED){
            mMap.isMyLocationEnabled=true
            getLocationUpdates()
            startLocationUpdates()

        }
        else{
            ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST
            )
        }
    }
    //permission pertama kali
    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray
    ) {
        if(requestCode==LOCATION_PERMISSION_REQUEST){
            if(grantResults.contains(PackageManager.PERMISSION_GRANTED)){
                mMap.isMyLocationEnabled=true
            }else{
                Toast.makeText(
                        requireContext(),
                        "User has not granted location permision",
                        Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    //fungsi digunakan untuk mengmark lokasi
    private fun getLocationUpdates() {
        locationRequest = LocationRequest()
        locationRequest.interval = 30000
        locationRequest.fastestInterval = 20000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                if (locationResult.locations.isNotEmpty()) {
                    val location = locationResult.lastLocation
                    val locationlogging = LocationLogging(location.latitude, location.longitude)
                    latd    =locationlogging.Latitude
                    longd   =locationlogging.Longitude
                    if(latd!=null){
                        latitud= latd!!
                    }
                    if(longd!=null){
                        longitud=longd!!
                    }

                    if (location != null) {
                        val latLng = LatLng(location.latitude, location.longitude)
                        val markerOptions = MarkerOptions().position(latLng)
                        mMap.addMarker(markerOptions)
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                    }
                }
            }
        }

    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                null
        )
    }

    //fungsi yang berjalan saat nantinya fragment dibuka dan menampilkan map
    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        getLocationAccess()
        //disini map mengset infowindow yang dicustom dengan class custominfowindowforgooglemap
        mMap.setInfoWindowAdapter(CustomInfoWindowForGoogleMap(this.requireContext()))

    }

    //fungsi untuk menampilkan seluru lokasi dan mengset infowindow agar clickable dan mengirimkan title ke lisloct agar dapat lgsg ketemu
    @SuppressLint("ResourceType")
    fun embark(){

            while( x < LocList.size){
                mMap.addMarker(
                        MarkerOptions()
                                .position(LatLng(LocList[x].Latitude, LocList[x].Longitude))
                                .title(LocList[x].Name)
                                .snippet(LocList[x].Notes)
                )
               x++
            }
        mMap.setOnInfoWindowClickListener {
            val bundle = Bundle()
            bundle.putString("message", it.title)
            this.view?.findNavController()?.navigate(R.id.action_nav_home_to_nav_slideshow, bundle)
        }

    }

}

