package hr.ferit.karlovlasic.eldare


import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


object Routes {
    const val SCREEN_MAIN_MENU = "mainMenu"
    const val SCREEN_DODAJ_LIJEK = "addMedicine"
    const val SCREEN_OBRISI_LIJEK = "deleteMedicine"
    const val SCREEN_OBRISI_VRIJEME_UZIMANJA = "deleteIntakeTimes"
    const val SCREEN_POGLEDAJ_RASPORED = "viewIntakeTimes"
}

@Composable
fun NavigationController(
    viewModel: ViewIntakeTimesViewModel,
    applicationContext: Context
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.SCREEN_MAIN_MENU
    ) {
        composable(
            route = Routes.SCREEN_MAIN_MENU
        ){
            MenuScreen(
                navController = navController
            )
        }
        composable(
            route = Routes.SCREEN_DODAJ_LIJEK,
        ){
            AddMedicineScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable(
            route = Routes.SCREEN_OBRISI_LIJEK
        ){
            DeleteMedicineScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable(
            route = Routes.SCREEN_OBRISI_VRIJEME_UZIMANJA
        ){
            DeleteIntakeTimesScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable(
            route = Routes.SCREEN_POGLEDAJ_RASPORED
        ){
            ViewIntakeTimesScreen(
                viewModel = viewModel,
                navController = navController,
                applicationContext
            )
        }
    }
}