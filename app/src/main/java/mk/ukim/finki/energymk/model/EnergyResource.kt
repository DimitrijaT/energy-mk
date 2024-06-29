package mk.ukim.finki.energymk.model

import com.google.firebase.firestore.DocumentId

class EnergyResource {
    @DocumentId
    val id: String = ""
    val category: String = ""
    val name: String = ""
    val timeline: Map<String, Float> = mapOf()
    val unit: String = ""

    fun getLatestKey(): String {
        var dateList: List<String> = timeline.keys.toList()
        dateList = dateList.sorted()
        return dateList.last()
    }

    fun getLatestValue(): Float {
        return timeline[getLatestKey()] ?: 0.0f
    }

    //The idea behind this is that we receive something like this:
    //
    //[value1, value2, value3, value4]
    //
    //
    //And we convert it to:
    //
    //[(value1, value2), (value2, value3), (value3, value4)]
    fun getList(): List<Float> {
        return timeline.values.toList()
    }
}