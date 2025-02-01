package hr.ferit.karlovlasic.eldare


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import hr.ferit.karlovlasic.eldare.ui.theme.Yellow


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<ViewIntakeTimesViewModel>()
        enableEdgeToEdge()
        setContent {
            Surface ( color = Yellow ) {
                NavigationController(viewModel, applicationContext)
            }
        }
    }
}