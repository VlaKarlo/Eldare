package hr.ferit.karlovlasic.eldare

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import hr.ferit.karlovlasic.eldare.ui.theme.Blue
import hr.ferit.karlovlasic.eldare.ui.theme.Purple
import hr.ferit.karlovlasic.eldare.ui.theme.White
import hr.ferit.karlovlasic.eldare.ui.theme.Yellow


@Composable
fun ScreenTitle(
    title: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 50.dp, bottom = 15.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = TextStyle(
                color = Blue,
                fontSize = 42.sp,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Normal
            )
        )
    }
}

@Composable
fun MenuButton(
    text: String,
    onClick:  () -> Unit
) {
    Button(
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Purple),
        onClick = { onClick() },
        modifier = Modifier
            .width(275.dp)
            .height(70.dp)
            .padding(vertical = 7.dp)
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun BackBar(
    text: String,
    navController: NavController
) {
    Row (
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 30.dp)
            .fillMaxWidth()
            .background(color = Purple)
            .height(60.dp)
    ){
        ArrowButton(
            navController
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = text,
            style = TextStyle(
                color = White,
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun ArrowButton(
    navController: NavController
) {
    Button(
        contentPadding = PaddingValues(),
        onClick = { navController.navigate(Routes.SCREEN_MAIN_MENU) },
        colors = ButtonDefaults.buttonColors(containerColor = Purple,
        ),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .width(38.dp)
            .height(38.dp)
            .padding(start = 5.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = null,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeDial(
    onConfirm: (TimePickerState) -> Unit,
) {
    val currentTime = java.util.Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(java.util.Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(java.util.Calendar.MINUTE),
        is24Hour = true,
    )
    val customColors = TimePickerDefaults.colors(
        clockDialColor = Purple,
        selectorColor = Blue,
        containerColor =  Yellow,
        periodSelectorBorderColor =  Blue,
        clockDialSelectedContentColor =  White,
        clockDialUnselectedContentColor =  White,
        timeSelectorSelectedContainerColor =  Blue,
        timeSelectorUnselectedContainerColor =  Purple,
        timeSelectorSelectedContentColor =  White,
        timeSelectorUnselectedContentColor =  White,
    )
    Column {
        TimePicker(
            state = timePickerState,
            colors = customColors
        )
        MenuButton(
            "Odaberite Vrijeme",
            onClick = { onConfirm(timePickerState) }
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun CustomisedOutlinedTextField(
    medicineName : String,
    label : String,
    userInput : (String) -> Unit
){
    val customColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedTextColor = Blue,
        unfocusedTextColor = Blue,
        cursorColor = Purple,
        unfocusedLabelColor = Blue,
        focusedLabelColor = Blue,
        focusedPlaceholderColor = Blue,
        unfocusedPlaceholderColor = Purple,
        focusedBorderColor = Blue,
        unfocusedBorderColor = Purple,
    )

    Row(
        modifier = Modifier
            .padding(vertical = 10.dp)
    ) {
        OutlinedTextField(
            value = medicineName,
            onValueChange = userInput ,
            label = {
                Text(label)
            },
            colors = customColors
        )
    }
}

@Composable
fun ReminderCard(
    medicineName : String,
    amount : String,
    intakeTime : String,
){
    Column (
        modifier = Modifier
            .padding(bottom = 10.dp)
            .border(
                border = BorderStroke(width = 10.dp, color = Purple),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(10.dp)
            .background(Purple)
            .fillMaxWidth()

    ) {
        Row {
            Text(
                text = medicineName,
                style = TextStyle(
                    color = White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontStyle = FontStyle.Normal,
                )
            )
        }
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        ){
            Text(
                text = amount,
                style = TextStyle(
                    color = White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontStyle = FontStyle.Normal
                )
            )
            Text(
                text = intakeTime,
                style = TextStyle(
                    color = White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontStyle = FontStyle.Normal
                ),
                modifier = Modifier
                    .padding(start = 10.dp)
            )
        }
    }
}