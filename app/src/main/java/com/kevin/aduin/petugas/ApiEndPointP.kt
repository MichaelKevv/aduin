package com.kevin.aduin.petugas

class ApiEndPointP {
    companion object{
        private val SERVER = "http://192.168.43.54/adu.in/petugas/"
        val CREATE = SERVER +"create.php"
        val READ = SERVER +"read.php"
        val DELETE = SERVER +"delete.php"
        val UPDATE = SERVER +"update.php"
    }
}