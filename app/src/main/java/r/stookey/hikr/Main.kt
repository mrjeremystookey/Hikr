package r.stookey.hikr


import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.internal.BottomNavigationMenu
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment


import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.main_page.*
import r.stookey.hikr.dummy.DummyContent


class Main: AppCompatActivity(), View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener, MessageFragment.OnListFragmentInteractionListener {

    private val TAG: String = "POST"

    private lateinit var userID: String
    private lateinit var username: String
    private lateinit var email: String

    private var content: ConstraintLayout? = null

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_all_posts -> {
                val allPostsFragment = MessageFragment.newInstance(1)
                addFragment(allPostsFragment)
            }
            R.id.action_user_profile -> {
                val profileFragment = ProfileFragment.newInstance()
                addFragment(profileFragment)
            }
            R.id.action_new_post -> {
                val postFragment = PostFragment.newInstance()
                addFragment(postFragment)
            }
        }
        return false
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.content, fragment, fragment.tag)
                .addToBackStack(fragment.javaClass.getSimpleName())
                .commit()
        Log.d(TAG, "addFragment(): fragment changed" + fragment.tag)
    }

    //TODO Loading just the new post fragment and not switching to the others.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)
        Log.d(TAG, "Main activity started")
        email = intent.getStringExtra("email")
        userID = intent.getStringExtra("userID")
        username = intent.getStringExtra("username")
        bottomNavigationView.menu.findItem(R.id.action_user_profile).title = username
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        content = findViewById(R.id.content)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onClick(v: View?) {
        //TODO Show submenu for new Photo, Video, or Audio recording

    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
    }
}