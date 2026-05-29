package me.fabiansuarez.compose.lab.netflixbasicnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.LoginScreen
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.RegisterScreen
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.RegisterStep2Screen
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.RegisterStep3Screen
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.theme.NetflixBasicNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
        val myNavController = rememberNavController()

        NavHost(
            navController = myNavController,
            startDestination = Routes.LOGIN,
            modifier = Modifier.fillMaxSize()
        ){
            composable(route = Routes.LOGIN.toString()){
                LoginScreen(
                    onClickSuscribirser = {
                        myNavController.navigate(Routes.REGISTER)
                    }
                )
            }

            composable(route = "register"){
                RegisterScreen(
                    onClickBack = {
                        myNavController.popBackStack()
                    },
                    onClickNext = {
                        myNavController.navigate(Routes.REGISTER_STEP_2)
                    }
                )
            }
            composable(route = Routes.REGISTER_STEP_2.toString()){
                RegisterStep2Screen(
                    onClickBack = { myNavController.popBackStack() },
                    onClickNext = { myNavController.navigate(Routes.REGISTER_STEP_3) }
                )
            }
            composable(route = Routes.REGISTER_STEP_3.toString()){
                RegisterStep3Screen(
                    onClickBack = { myNavController.popBackStack() }
                )
            }
        }

        }
    }

    enum class Routes(val route: String) {
        LOGIN("login"),
        REGISTER("register"),
        REGISTER_STEP_2("register_step_2"),
        REGISTER_STEP_3("register_step_3")
    }
}