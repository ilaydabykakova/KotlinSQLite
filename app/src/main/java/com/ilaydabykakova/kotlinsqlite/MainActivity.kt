package com.ilaydabykakova.kotlinsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            val myDatabase = this.openOrCreateDatabase("Musicians", MODE_PRIVATE,null)
            //execute Sql code
            //Create Table
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY,name VARCHAR, age INT)")

            //myDatabase.execSQL("INSERT INTO musicians(name, age) VALUES ('James',50)")
            //myDatabase.execSQL("INSERT INTO musicians(name, age) VALUES ('Lars',50)")
            //myDatabase.execSQL("INSERT INTO musicians(name, age) VALUES ('Kirk',45)")

            //myDatabase.execSQL("UPDATE musicians SET age = 55, name = 'David' WHERE  Id = 2 ")
            //myDatabase.execSQL("DELETE FROM musicians WHERE name = 'James'")
            val cursor = myDatabase.rawQuery("SELECT * FROM musicians  ",null)

            val nameIx = cursor.getColumnIndex("name")
            val ageIx = cursor.getColumnIndex("age")
            val idIx = cursor.getColumnIndex("id")

            //Sql Table
            while (cursor.moveToNext()){
                println("Id: "+ cursor.getInt(idIx))
                println("Name: "+ cursor.getString(nameIx))
                println("Age: "+cursor.getInt(ageIx))
            }
            cursor.close()




        }catch(e:Exception){
            e.printStackTrace()

        }
    }
}