package com.dicoding.kelassekolah

object EventsData {
    private val eventNames = arrayOf(
        "Latihan Musikal Drama Kelas",
        "Outing Class: Panjat Tebing",
        "Kemah di Buperta",
        "Outing Class: Olahraga Fisik",
        "Outing Class: Sesi Memasak",
        "Kerja Kelompok Matematika",
        "Classmeeting Sekolah",
        "Berbagi Takjil Ramadhan",
        "Praktek Olahraga: Berenang",
        "Presentasi Kelompok Matematika",
        "Berbagi Takjil Ramadhan 2",
        "Berbagi Takjil Ramadhan 3",
    )

    private val eventDates = arrayOf(
        "5 Januari 2022",
        "1 Februari 2022",
        "2 Maret 2022",
        "6 April 2022",
        "9 Mei 2022",
        "12 Juni 2022",
        "31 Juli 2022",
        "2 Agustus 2022",
        "4 September 2022",
        "3 Oktober 2022",
        "10 November 2022",
        "4 Desember 2022",
    )

    private val eventParticipants = arrayOf(
        "Siswa Siswi XI RPL 2 SMKN 1 GUNUNGPUTRI",
        "Siswa XI RPL 2 SMKN 1 GUNUNGPUTRI",
        "Siswi XI RPL 2 SMKN 1 GUNUNGPUTRI",
        "Siswa Siswi SMKN 1 GUNUNGPUTRI",
        "Siswa Siswi XI RPL 2 SMKN 1 GUNUNGPUTRI",
        "Siswi SMKN 1 GUNUNGPUTRI",
        "Siswa Siswi SMKN 1 GUNUNGPUTRI",
        "Siswi XI RPL 2 SMKN 1 GUNUNGPUTRI",
        "Siswa Siswi XI RPL 2 SMKN 1 GUNUNGPUTRI",
        "Siswa Siswi SMKN 1 GUNUNGPUTRI",
        "Siswi XI RPL 2 SMKN 1 GUNUNGPUTRI",
        "Siswa Siswi SMKN 1 GUNUNGPUTRI"
    )

    private val eventDetails = arrayOf(
        "Ini hanya mocking, seluruh aktivitas disini tidak dapat dipastikan kebenarannya, lorem ipsum dolor sit amet",
        "Ini hanya mocking, seluruh aktivitas disini tidak dapat dipastikan kebenarannya, lorem ipsum dolor sit amet",
        "Ini hanya mocking, seluruh aktivitas disini tidak dapat dipastikan kebenarannya, lorem ipsum dolor sit amet",
        "Ini hanya mocking, seluruh aktivitas disini tidak dapat dipastikan kebenarannya, lorem ipsum dolor sit amet",
        "Ini hanya mocking, seluruh aktivitas disini tidak dapat dipastikan kebenarannya, lorem ipsum dolor sit amet",
        "Ini hanya mocking, seluruh aktivitas disini tidak dapat dipastikan kebenarannya, lorem ipsum dolor sit amet",
        "Ini hanya mocking, seluruh aktivitas disini tidak dapat dipastikan kebenarannya, lorem ipsum dolor sit amet",
        "Ini hanya mocking, seluruh aktivitas disini tidak dapat dipastikan kebenarannya, lorem ipsum dolor sit amet",
        "Ini hanya mocking, seluruh aktivitas disini tidak dapat dipastikan kebenarannya, lorem ipsum dolor sit amet",
        "Ini hanya mocking, seluruh aktivitas disini tidak dapat dipastikan kebenarannya, lorem ipsum dolor sit amet",
        "Ini hanya mocking, seluruh aktivitas disini tidak dapat dipastikan kebenarannya, lorem ipsum dolor sit amet",
        "Ini hanya mocking, seluruh aktivitas disini tidak dapat dipastikan kebenarannya, lorem ipsum dolor sit amet",
    )

    private val eventPhotos = intArrayOf(
        R.drawable.school_activity_1,
        R.drawable.school_activity_2,
        R.drawable.school_activity_3,
        R.drawable.school_activity_4,
        R.drawable.school_activity_5,
        R.drawable.school_activity_6,
        R.drawable.school_activity_7,
        R.drawable.school_activity_8,
        R.drawable.school_activity_9,
        R.drawable.school_activity_10,
        R.drawable.school_activity_11,
        R.drawable.school_activity_12,
    )

    private val likes = intArrayOf(
        15,
        22,
        32,
        47,
        52,
        61,
        81,
        31,
        65,
        63,
        31,
        62,
    )

    private fun dataEvent(position: Int): Event {
        val event = Event()
        event.name = eventNames[position]
        event.date = eventDates[position]
        event.participant = eventParticipants[position]
        event.detail = eventDetails[position]
        event.likes = likes[position].toInt()
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