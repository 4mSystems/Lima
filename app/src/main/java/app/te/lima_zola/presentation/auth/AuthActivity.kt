package app.te.lima_zola.presentation.auth

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import app.te.lima_zola.R
import app.te.lima_zola.presentation.base.BaseActivity
import app.te.lima_zola.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding>() {
    private lateinit var nav: NavController

    override
    fun getLayoutId() = R.layout.activity_auth
    override fun setUpViews() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        nav = navHostFragment.findNavController()
        val navGraph = nav.navInflater.inflate(R.navigation.nav_auth)
        if (intent.extras != null && intent.hasExtra("extra")) {
            val destination = intent.getIntExtra("extra", R.id.splashFragment)
            navGraph.setStartDestination(destination)
            nav.graph = navGraph
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

//        splashScreen.setOnExitAnimationListener { splashScreenView ->
//            // Create your custom animation.
//            val slideUp = ObjectAnimator.ofFloat(
//                splashScreenView,
//                View.TRANSLATION_Z,
//                0f,
//                -splashScreenView.width.toFloat()
//            )
//            slideUp.interpolator = AnticipateInterpolator()
//            slideUp.duration = 100L
//
//            // Call SplashScreenView.remove at the end of your custom animation.
//            slideUp.doOnEnd { splashScreenView.remove() }
//
//            // Run your animation.
//            slideUp.start()
//        }
        super.onCreate(savedInstanceState)
//        installSplashScreen()

    }
}