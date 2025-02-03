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
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    medicineData.clear()
                    for (doc in snapshot.documents) {
                        val medicine = doc.toObject(Medicine::class.java)
                        if (medicine != null) {
                            medicine.doc = doc.id
                            medicineData.add(medicine)
                        }
                    }
                }
            }
    }
}