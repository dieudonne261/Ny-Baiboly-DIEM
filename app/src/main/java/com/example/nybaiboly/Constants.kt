package com.example.nybaiboly

object Constants {
    const val DB_NAME = "NyBaiboly"
    const val DB_VERSION = 1
    const val TABLE_NAME = "recent"
    const val C_ID = "ID"
    const val C_TEXT = "TEXT"
    const val C_ID_LIVRE = "ID_LIVRE"
    const val C_ID_CHAPITRE = "ID_CHAPITRE"
    const val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
            "$C_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "$C_TEXT TEXT,"+
            "$C_ID_LIVRE TEXT," +
            "$C_ID_CHAPITRE TEXT);"
}
