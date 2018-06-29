package r.stookey.hikr


import android.annotation.SuppressLint
import android.content.Context
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
import android.view.View
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.main_page.*
import org.jetbrains.anko.toast
import r.stookey.hikr.dummy.DummyContent

class Main: AppCompatActivity(), View.OnClickListener,
        BottomNavigationView.OnNavigationItemSelectedListener,
        MessageListFragment.OnListFragmentInteractionListener,
        LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private val TAG: String = "POST"
    private val LOCATION_REQUEST_CODE: Int = 10
    private val permissions = arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION)


    private lateinit var userID: String
    private lateinit var username: String
    private lateinit var email: String

    //Location declarations
    private var mGoogleApiClient: GoogleApiClient? = null
    private var mLocation: Location? = null
    private var mLocationManager: LocationManager? = null
    private var mLocationRequest: LocationRequest? = null
    private val listener: com.google.android.gms.location.LocationListener? = null
    private val UPDATE_INTERVAL = (2 * 1000).toLong()  /* 10 secs */
    private val FASTEST_INTERVAL: Long = 2000 /* 2 sec */


    private var content: ConstraintLayout? = null

    //TODO Loading the New Post Fragment on first load
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)
        Log.d(TAG, "Main activity started")
        setSupportActionBar(findViewById(R.id.appbar))
        if(!checkPermissions()){
            requestPermissions()
        }

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()

        mLocationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        email = intent.getStringExtra("email")
        userID = intent.getStringExtra("userID")
        username = intent.getStringExtra("username")
        bottomNavigationView.menu.findItem(R.id.action_user_profile).title = username
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        content = findViewById(R.id.content)
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
            R.id.action_all_posts -> {
                val allPostsFragment = MessageListFragment.newInstance(userID, email, 1)
                addFragment(allPostsFragment)
            }
            R.id.action_user_profile -> {
                val profileFragment = ProfileFragment.newInstance(userID, email)
                addFragment(profileFragment)
            }
            R.id.action_new_post -> {
                val postFragment = PostFragment.newInstance(userID, email)
                addFragment(postFragment)
            }
        }
        return false
    }

    fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.content, fragment, fragment.tag)
                .addToBackStack(fragment.javaClass.getSimpleName())
                .commit()
        Log.d(TAG, "addFragment(): fragment changed")
    }


    override fun onClick(v: View?) {
        //TODO Show submenu for new Photo, Video, or Audio recording
    }


    @SuppressLint("MissingPermission")
    private fun startLocationUpdates(){
        Log.i(TAG, "Starting location updates")
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL)
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this)
    }

    override fun onLocationChanged(location: Location?) {
        Log.i(TAG, location!!.latitude.toString() + " "+location!!.longitude.toString())
    }

    @SuppressLint("MissingPermission")
    override fun onConnected(p0: Bundle?) {
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)

    }

    override fun onConnectionSuspended(p0: Int) {
        Log.i(TAG, "Connection Suspended")
        mGoogleApiClient!!.connect()
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.i(TAG, "Connection failed. Error: " + connectionResult.getErrorCode())
    }


    private fun checkPermissions(): Boolean{
        val fineLocationCheck = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
        val courseLocationCheck = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
        if(fineLocationCheck !== PackageManager.PERMISSION_GRANTED && courseLocationCheck !== PackageManager.PERMISSION_GRANTED){
            requestPermissions()
        }
        return true
    }

    private fun requestPermissions(){
        requestPermissions(permissions, LOCATION_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == LOCATION_REQUEST_CODE){
            if(grantResults.isEmpty()){
                Log.i(TAG, "User interaction was cancelled")
            }
            else if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                Log.i(TAG, "Permissions granted")
                startLocationUpdates()
            } else{
                Log.i(TAG, "Permissions denied")
            }
        }
    }


    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
    }
}