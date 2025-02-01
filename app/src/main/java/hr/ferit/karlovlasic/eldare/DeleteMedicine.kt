package hr.ferit.karlovlasic.eldare


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteMedicineScreen(
    viewModel: ViewIntakeTimesViewModel,
    navController: NavController
) {
    viewModel.fetchDatabaseData()
    val context = LocalContext.current
    var medicineName by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    )  {
        BackBar(
            text = "Obriši Lijek",
            navController
        )
        Row(
        ) {
            CustomisedOutlinedTextField(
                medicineName = medicineName,
                userInput = { medicineName = it },
                label = "Unesite Naziv Lijeka"
            )
        }
        MenuButton(
            text = "Obriši",
            onClick = {
                if (medicineName.isEmpty()){
                    Toast.makeText(
                        context,
                        "Molim Vas ispunite sva polja!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else {
                    MedicineManager().removeMedicine(
                        viewModel,
                        medicineName,
                        onCompletion = {
                            Toast.makeText(
                                context,
                                "Lijek je obrisan!",
                                Toast.LENGTH_SHORT
                            ).show()
                            medicineName = ""
                        }
                    )

                }
            }
        )
    }
}