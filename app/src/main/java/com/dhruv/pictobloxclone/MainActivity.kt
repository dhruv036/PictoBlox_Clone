package com.dhruv.pictobloxclone


import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.dhruv.pictobloxclone.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var  binding : ActivityMainBinding
    var list : MutableList<Tile> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        val db = Firebase.firestore
        binding.recycle.adapter = Adapter(mutableListOf(),applicationContext)
        binding.recycle.layoutManager = GridLayoutManager(applicationContext,2)

        db.collection("tiles").get().addOnSuccessListener {snapshot->
            snapshot?.let { it ->
                it.documents?.let {
                    for(i in it){
                       val note = i.toObject(Tile::class.java)
                        list.add(note!!)
//                        Tile(itemId = it["itemId"].t, itemTitle = it["itemTitle"], itemLocation = it["itemLocation"], itemBg = it["itemBg"], itemType = it["itemType"], videoUrl = it["videoUrl"])
                    }
                }
            }

            Log.e("Main", "$list")
            list.sortBy {
                it.itemId
            }

            val manager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
            binding.recycle.setLayoutManager(manager)
            binding.recycle.adapter = Adapter(list,applicationContext)
        }


    }
}