package r.stookey.hikr.ui.activities


import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment


import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*
import r.stookey.hikr.R
import r.stookey.hikr.di.Injector
import r.stookey.hikr.ui.fragments.PostFragment
import r.stookey.hikr.ui.fragments.PostListFragment
import r.stookey.hikr.ui.fragments.ProfileFragment

class Main : AppCompatActivity(),
        BottomNavigationView.OnNavigationItemSelectedListener,
        LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener
        {

    private val TAG: String = "POST"

    private val LOCATION_REQUEST_CODE: Int = 10
    private val permissions = arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION)
    private val mLocationManager: LocationManager

    init {
        mLocationManager = Injector.get().locationManager
    }

    private lateinit var mUserID: String
    private lateinit var mUsername: String
    private lateinit var fragment: PostFragment



    //Location declarations
    private var mGoogleApiClient: GoogleApiClient? = null
    private var mLocation: Location? = null
    private var mLocationRequest: LocationRequest? = null
    private val UPDATE_INTERVAL = (2 * 1000).toLong()  /* 10 secs */
    private val FASTEST_INTERVAL: Long = 2000 /* 2 sec */
    lateinit var mapFragment: SupportMapFragment
    lateinit var googleMap: GoogleMap


    private var content: ConstraintLayout? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "Main activity started")
        if (!checkPermissions()) {
            requestPermissions()
        }


        getUserProperties()
        initializeLocation()
        setupUI()
        content = findViewById(R.id.content)
        fragment = PostFragment.newInstance(mUserID)
        addFragment(fragment)

    }



    override fun onStart() {
        super.onStart()
        if (mGoogleApiClient != null) {
            mGoogleApiClient!!.connect()
        }
    }

    override fun onStop() {
        super.onStop()
        if (mGoogleApiClient!!.isConnected()) {
            mGoogleApiClient!!.disconnect()
        }
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            //TODO Creating only one version of the fragment and not a new one whenever the bottom nav is used
            R.id.action_all_posts -> {
                val allPostsFragment = PostListFragment.newInstance(mUserID)
                addFragment(allPostsFragment)
            }
            R.id.action_user_profile -> {
                val profileFragment = ProfileFragment.newInstance(mUserID)
                addFragment(profileFragment)
            }
            R.id.action_new_post -> {
                addFragment(fragment)
            }
        }
        return false
    }

    private fun setupUI(){
        bottomNavigationView.menu.findItem(R.id.action_user_profile).title = mUsername
        bottomNavigationView.setOnNavigationItemSelectedListener(this)

    }



    private fun getUserProperties(){
        mUserID = intent.getStringExtra("userID")
        mUsername = intent.getStringExtra("username")
    }

    private fun initializeLocation() {
        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()


        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync {
            googleMap = it
        }
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.content, fragment, fragment.tag)
                .addToBackStack(fragment.javaClass.getSimpleName())
                .commit()
        Log.d(TAG, "addFragment(): fragment changed")
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        Log.i(TAG, "Starting location updates")
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL)
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this)
    }

    override fun onLocationChanged(location: Location?) {
        Log.i(TAG, location!!.latitude.toString() + " " + location!!.longitude.toString())
    }

    @SuppressLint("MissingPermission")
    override fun onConnected(p0: Bundle?) {
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)
        //Setting Location for the View Models
        googleMap.addMarker(MarkerOptions().position(LatLng(mLocation!!.latitude, mLocation!!.longitude)).title("Current Location"))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(mLocation!!.latitude, mLocation!!.longitude), 15f))
    }

    override fun onConnectionSuspended(p0: Int) {
        Log.i(TAG, "Connection Suspended")
        mGoogleApiClient!!.connect()
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.i(TAG, "Connection failed. Error: " + connectionResult.getErrorCode())
    }

    //Permissions remain in the activity, don't go to a ViewModel
    private fun checkPermissions(): Boolean {
        val fineLocationCheck = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
        val courseLocationCheck = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
        if (fineLocationCheck !== PackageManager.PERMISSION_GRANTED && courseLocationCheck !== PackageManager.PERMISSION_GRANTED) {
            requestPermissions()
        }
        return true
    }

    private fun requestPermissions() {
        requestPermissions(permissions, LOCATION_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.isEmpty()) {
                Log.i(TAG, "User interaction was cancelled")
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "Permissions granted")
                startLocationUpdates()
            } else {
                Log.i(TAG, "Permissions denied")
            }
        }
    }


}