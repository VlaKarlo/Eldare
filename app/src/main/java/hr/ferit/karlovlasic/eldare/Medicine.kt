package hr.ferit.karlovlasic.eldare

data class Medicine(
    var doc: String = "",
    var names: MutableList<String> = mutableListOf(),
    var intakeTimes: MutableList<MutableMap<String,String>> = mutableListOf()
)

