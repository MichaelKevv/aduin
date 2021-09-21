package com.kevin.aduin.tanggapan

class ApiEndPointT {
    companion object{
        private val SERVER = "http://192.168.43.54/adu.in/tanggapan/"
        val CREATE = SERVER +"create.php"
        val READ = SERVER +"read.php"
        val DELETE = SERVER +"delete.php"
        val UPDATE = SERVER +"update.php"
    }
}