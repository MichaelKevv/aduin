package com.kevin.aduin.pengaduan

class ApiEndPointPengaduan {
    companion object{
        private val SERVER = "http://192.168.43.54/adu.in/pengaduan/"
        val CREATE = SERVER +"create.php"
        val READ = SERVER +"read.php"
        val DELETE = SERVER +"delete.php"
        val UPDATE = SERVER +"update.php"
    }
}