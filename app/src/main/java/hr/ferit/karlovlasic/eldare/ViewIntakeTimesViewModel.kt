package hr.ferit.karlovlasic.eldare

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ViewIntakeTimesViewModel: ViewModel() {
    private val db = Firebase.firestore
    val medicineData = mutableStateListOf<Medicine>()
    init {
        fetchDatabaseData()
    }
    fun fetchDatabaseData() {
        db.collection("medicalSchedule")
            .get()
            .addOnSuccessListener { result ->
                for (data in result.documents) {
                    val medicine = data.toObject(Medicine::class.java)
                    if (medicine != null) {
                        medicine.doc = data.id
                        medicineData.add(medicine)
                    }
                }
            }
    }
}