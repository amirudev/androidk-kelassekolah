package com.dicoding.kelassekolah

object EventsData {
    private val eventNames = arrayOf(
        "Les Piano",
        "B in X and Y get Z then BCD",
        "C in X and Y get Z then BCD",
        "D in X and Y get Z then BCD"
    )

    private val eventDates = arrayOf(
        "1 September 2022",
        "2 September 2022",
        "3 September 2022",
        "4 September 2022"
    )

    private val eventParticipants = arrayOf(
        "Siswa Siswi XI RPL 2 SMKN 1 GUNUNGPUTRI",
        "Siswa XI RPL 2 SMKN 1 GUNUNGPUTRI",
        "Siswi XI RPL 2 SMKN 1 GUNUNGPUTRI",
        "Seluruh Siswa Siswi SMKN 1 GUNUNGPUTRI"
    )

    private val eventDetails = arrayOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce condimentum tincidunt arcu et mollis. In ornare varius cursus. Sed massa tortor, scelerisque et laoreet dignissim, faucibus vitae ante.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce condimentum tincidunt arcu et mollis. In ornare varius cursus. Sed massa tortor, scelerisque et laoreet dignissim, faucibus vitae ante.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce condimentum tincidunt arcu et mollis. In ornare varius cursus. Sed massa tortor, scelerisque et laoreet dignissim, faucibus vitae ante.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce condimentum tincidunt arcu et mollis. In ornare varius cursus. Sed massa tortor, scelerisque et laoreet dignissim, faucibus vitae ante.",
    )

    private val eventPhotos = intArrayOf(
        R.drawable.school_activity_1,
        R.drawable.school_activity_2,
        R.drawable.school_activity_3,
        R.drawable.school_activity_4,
    )

    private val likes = intArrayOf(
        105,
        202,
        302,
        407,
        502,
        601,
    )

    private fun dataEvent(position: Int): Event {
        val event = Event()
        event.name = eventNames[position]
        event.date = eventDates[position]
        event.participant = eventParticipants[position]
        event.detail = eventDetails[position]
        event.photo = eventPhotos[position]

        return event
    }

    val listData: ArrayList<Event> get(){
        val list = arrayListOf<Event>()
        for (position in eventNames.indices) {
            val event = dataEvent(position)
            list.add(event)
        }

        return list
    }

    fun getData(position: Int): Event {
        return dataEvent(position)
    }
}