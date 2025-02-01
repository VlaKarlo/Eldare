package hr.ferit.karlovlasic.eldare


import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun ViewIntakeTimesScreen(
    viewModel: ViewIntakeTimesViewModel,
    navController: NavController,
    applicationContext: Context
) {
    LaunchedEffect(Unit) {
        viewModel.fetchDatabaseData()
    }

    val medicineName  = 0
    val medicineAmount  = 2
    val medicineIntakeTime  = 1


    val sortedMedicineSchedule = MedicineScheduleManager().getSortedSchedule(viewModel, applicationContext)

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    )  {
        BackBar(
            text = "Pogledaj Raspored",
            navController
        )
        LazyColumn (
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){
            items(sortedMedicineSchedule.size){
                ReminderCard(
                    sortedMedicineSchedule[it][medicineName],
                    sortedMedicineSchedule[it][medicineAmount],
                    sortedMedicineSchedule[it][medicineIntakeTime],
                )
            }
        }
    }
}
