package r.stookey.hikr


import android.os.Bundle


import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.post.*
import org.jetbrains.anko.toast


//setting up the bottom nav bar
//https://medium.com/@hitherejoe/exploring-the-android-design-support-library-bottom-navigation-drawer-548de699e8e0

class Post: AppCompatActivity(), View.OnClickListener {

    private val TAG: String = "POST"

    private var postFragment = PostFragment.newInstance()
    private lateinit var message: Message

    private lateinit var userID: String
    private lateinit var username: String
    private lateinit var email: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post)
        Log.d(TAG, "Post activity started")
        fabAddPost.setOnClickListener(this)
    }


    override fun onStart() {
        super.onStart()
        determineUser()
    }


    private fun determineUser() {
        if (intent.hasExtra("email") && intent.hasExtra("username")) {
            username = intent.getStringExtra("username")
            email = intent.getStringExtra("email")
            userID = intent.getStringExtra("userID")
        } else if (intent.hasExtra("newUserEmail") && intent.hasExtra("newUserUsername")) {
            username = intent.getStringExtra("newUserUsername")
            email = intent.getStringExtra("newUserEmail")
            userID = intent.getStringExtra("userID")
        }
        val menuItem = bottomNavigationView.menu.findItem(R.id.action_profile)
        menuItem.title = username
    }

    override fun onClick(v: View?) {
        //TODO Show submenu for new Photo, Video, or Audio recording
        toast(postFragment.getText())
    }






}