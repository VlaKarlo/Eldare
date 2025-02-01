package hr.ferit.karlovlasic.eldare

import android.icu.text.SimpleDateFormat
import java.util.Date
import android.content.Context
import android.icu.util.Calendar
import android.util.Log

class MedicineScheduleManager (

){

    fun getCleanData(
        viewModel: ViewIntakeTimesViewModel
    ): MutableList<List<Any>>
    {
        val medicine = viewModel.medicineData.toList()[0]
        var dataList = mutableListOf<List<Any>>()

        for(i in 0 until medicine.names.size){
            var cardInfo = mutableListOf(
                medicine.names[i],
                medicine.intakeTimes[i]
            )
            dataList.add(cardInfo)
        }
        return dataList
    }

    fun sortDataByTime(
        data: MutableList<List<Any>>,
        applicationContext: Context
    ) : List<List<String>>
    {
        val currentTime = SimpleDateFormat("HH:mm").format(Date())
        val currentTimeList = currentTime.split(":")
        val currentTimeNumber = currentTimeList[0].toInt() * 60 + currentTimeList[1].toInt()

        val medicineCardsSchedule = mutableListOf<List<String>>()

        for (item in data) {
            val medicineName = item[0] as String
            val intakeTimes = item[1] as Map<String, String>
            for ((time, amount) in intakeTimes) {
                medicineCardsSchedule.add(
                    mutableListOf(
                        medicineName,
                        time,
                        amount
                    )
                )
            }
        }

        val sortedMedicineCards = medicineCardsSchedule.sortedBy  { entry ->
            val entryTime = entry[1].split(":")[0].toInt() * 60 + entry[1].split(":")[1].toInt()
            if (entryTime < currentTimeNumber)
                entryTime + 3600
            else
                entryTime
        }

        val timeList = sortedMedicineCards.map { entry ->
            val entryTime = entry[1].split(":")[0].toInt() * 60 + entry[1].split(":")[1].toInt()
                     if(entryTime < currentTimeNumber)
                         entryTime + 3600
                    else
                         entryTime
        }.toMutableList()

        Log.d("times",sortedMedicineCards.toString())
        timeList.sort()
        scheduleMedicineIntakeNotification(applicationContext, timeList)

        Log.d("times",timeList.toString())
        return sortedMedicineCards

    }

    fun getSortedSchedule(
        viewModel: ViewIntakeTimesViewModel,
        applicationContext: Context
    ): List<List<String>>
    {
        var data = getCleanData(viewModel)
        var sortedMedicineSchedule = sortDataByTime(data,applicationContext)
        return sortedMedicineSchedule
    }

    fun scheduleMedicineIntakeNotification(
        applicationContext: Context,
        timeList : MutableList<Int>
    ){
        val currentCalendarTime = Calendar.getInstance()
        val nextNotificationTime = Calendar.getInstance()

        nextNotificationTime.set(Calendar.HOUR_OF_DAY, timeList[0]/60)
        nextNotificationTime.set(Calendar.MINUTE, timeList[0]%60)
        nextNotificationTime.set(Calendar.SECOND, 0)
        nextNotificationTime.set(Calendar.MILLISECOND, 0)

        if (currentCalendarTime>nextNotificationTime)
            nextNotificationTime.set(Calendar.DAY_OF_YEAR, 1)

        NotificationSupport().scheduleNotification(applicationContext,nextNotificationTime.timeInMillis)
        Log.d("NextNotificationTime", "Notification scheduled for: ${nextNotificationTime.time}")
    }

}