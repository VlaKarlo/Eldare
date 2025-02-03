package hr.ferit.karlovlasic.eldare

import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore

class MedicineManager(
) {
    fun deleteMedicineByTime(
        viewModel: ViewIntakeTimesViewModel,
        medicineName : String,
        medicineHours : String,
        medicineMinutes : String,
        onCompletion: () -> Unit
    ){
        val medicineNames = viewModel.medicineData.toList()[0].names
        val medicineIntakes = viewModel.medicineData.toList()[0].intakeTimes
        val index = medicineNames.indexOf(medicineName)
        val newMedicineHours =
            if (medicineHours.toInt()<10) {
                "0${medicineHours}"
            }
            else {
                medicineHours
            }
        val newMedicineMinutes =
            if (medicineMinutes.toInt()<10) {
                "0${medicineMinutes}"
            }
            else {
                medicineMinutes
            }
        val intakeTimeKey = "${newMedicineHours}:${newMedicineMinutes}"
        if (index == -1) {
            return
        }
        if(medicineIntakes[index].isNotEmpty()) {
            medicineIntakes[index].remove(intakeTimeKey)
        }
        if (medicineIntakes[index].isEmpty()) {
            medicineIntakes.removeAt(index)
            medicineNames.removeAt(index)
        }
        val updateData = hashMapOf<String, Any>(
            "intakeTimes" to medicineIntakes,
            "names" to medicineNames
        )
        val db = Firebase.firestore.collection("medicalSchedule").document("pillIntake")
        db.update(updateData).addOnSuccessListener {
            viewModel.fetchDatabaseData()
            onCompletion()
        }
    }

    fun removeMedicine(
        viewModel: ViewIntakeTimesViewModel,
        medicineName : String,
        onCompletion: () -> Unit
    ){
        val medicineNames = viewModel.medicineData.toList()[0].names
        val medicineIntakes = viewModel.medicineData.toList()[0].intakeTimes
        val index = medicineNames.indexOf(medicineName)
        if (index == -1) {
            return
        }
        if(medicineNames[index].isNotEmpty()) {
            medicineIntakes.removeAt(index)
            medicineNames.remove(medicineName)
        }
        val updateData = hashMapOf<String, Any>(
            "intakeTimes" to medicineIntakes,
            "names" to medicineNames
        )
        val db = Firebase.firestore.collection("medicalSchedule").document("pillIntake")
        db.update(updateData).addOnSuccessListener {
            viewModel.fetchDatabaseData()
            onCompletion()
        }
    }

    fun addMedicine(
        viewModel: ViewIntakeTimesViewModel,
        medicineName : String,
        medicineAmount : String,
        medicineHours : String,
        medicineMinutes : String,
        onCompletion: () -> Unit
    ){
        val db = Firebase.firestore.collection("medicalSchedule").document("pillIntake")
        val medicineNames = viewModel.medicineData.toList()[0].names
        val medicineIntakes = viewModel.medicineData.toList()[0].intakeTimes

        val newMedicineHours =
            if (medicineHours.toInt()<10) {
                "0${medicineHours}"
            }
            else {
                medicineHours
            }

        val newMedicineMinutes =
        if (medicineMinutes.toInt()<10) {
             "0${medicineMinutes}"
        }
        else {
             medicineMinutes
        }

        val newIntakeTimeKey = "${newMedicineHours}:${newMedicineMinutes}"

        var updateData =
            if(medicineNames.contains(medicineName)){
                var mapIndex = medicineNames.indexOf(medicineName)
                medicineIntakes[mapIndex].put(
                    newIntakeTimeKey , medicineAmount
                )
                hashMapOf<String, Any>("intakeTimes" to medicineIntakes)
            }
            else{
                hashMapOf<String, Any>(
                    "names" to FieldValue.arrayUnion(medicineName),
                    "intakeTimes" to FieldValue.arrayUnion(mapOf(newIntakeTimeKey to medicineAmount)),
                )
            }

        db.update(updateData)
            .addOnSuccessListener {
                viewModel.fetchDatabaseData()
                onCompletion()
            }
    }
}