package hr.ferit.karlovlasic.eldare


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import hr.ferit.karlovlasic.eldare.ui.theme.Yellow


@Composable
fun MenuScreen(
    navController: NavController
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Yellow)
    ) {
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            ScreenTitle(
                title = "Eldare"
            )
            MenuButton(
                text = "Dodaj Lijek",
                onClick = { navController.navigate(Routes.SCREEN_DODAJ_LIJEK) }
            )
            MenuButton(
                text = "Obriši Lijek",
                onClick = { navController.navigate(Routes.SCREEN_OBRISI_LIJEK) }
            )
            MenuButton(
                text = "Obriši Vrijeme Uzimanja",
                onClick = { navController.navigate(Routes.SCREEN_OBRISI_VRIJEME_UZIMANJA) }
            )
            MenuButton(
                text = "Pogledaj Raspored",
                onClick = { navController.navigate(Routes.SCREEN_POGLEDAJ_RASPORED) }
            )
        }
    }
}
