package com.kevin.aduin.masyarakat

class ApiEndPointM {
    companion object{
        private val SERVER = "http://192.168.43.54/adu.in/masyarakat/"
        val CREATE = SERVER +"create.php"
        val READ = SERVER +"read.php"
        val DELETE = SERVER +"delete.php"
        val UPDATE = SERVER +"update.php"
    }
}