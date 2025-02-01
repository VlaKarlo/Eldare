package hr.ferit.karlovlasic.eldare


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMedicineScreen(
    viewModel: ViewIntakeTimesViewModel,
    navController: NavController
) {
    viewModel.fetchDatabaseData()
    val context = LocalContext.current
    var medicineName by remember { mutableStateOf("") }
    var medicineAmount by remember { mutableStateOf("") }
    var selectedTime: TimePickerState? by remember { mutableStateOf(null) }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    )  {
        BackBar(
            text = "Dodaj Lijek",
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
        Row(
            modifier = Modifier
                .padding(bottom = 20.dp)
        ) {
            CustomisedOutlinedTextField(
                medicineName = medicineAmount,
                userInput = { medicineAmount = it },
                label = "Unesite Količinu Lijeka"
            )
        }
        TimeDial(
            onConfirm = {
                    time -> selectedTime = time
            }
        )
        MenuButton(
            text = "Dodaj",
            onClick = {
                if(medicineName.isEmpty() || medicineAmount.isEmpty() || selectedTime == null) {
                    Toast.makeText(
                        context,
                        "Molim Vas ispunite sva polja i odaberite vrijeme!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else {
                    MedicineManager().addMedicine(
                        viewModel,
                        medicineName,
                        medicineAmount,
                        (selectedTime!!.hour).toString(),
                        (selectedTime!!.minute).toString(),
                        onCompletion = {
                            Toast.makeText(
                                context,
                                "Lijek uspješno dodan!",
                                Toast.LENGTH_SHORT).show()
                            medicineName = ""
                            medicineAmount = ""
                        }
                    )
                }
            }
        )
    }
}
