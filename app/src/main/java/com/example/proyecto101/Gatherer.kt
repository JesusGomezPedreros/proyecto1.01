package com.example.proyecto101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class Gatherer : AppCompatActivity() {

		private lateinit var dataBaseReference : DatabaseReference
		private lateinit var scheduleRecyclerView :RecyclerView
		private lateinit var scheduleArrayList : ArrayList<ScheduleFirebase>

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_gatherer)

		scheduleRecyclerView = findViewById(R.id.GathererList)
		scheduleRecyclerView.layoutManager=LinearLayoutManager(this)
		scheduleRecyclerView.setHasFixedSize(true)

		scheduleArrayList = arrayListOf<ScheduleFirebase>()
		getScheduleData()


	}

	private fun getScheduleData() {
		dataBaseReference=FirebaseDatabase.getInstance().getReference("GatherMaterials")
		dataBaseReference.addValueEventListener(object: ValueEventListener{
			override fun onDataChange(snapshot: DataSnapshot) {
				if (snapshot.exists()){
					for (scheduleSnapshop in snapshot.children){
						val scheduler = scheduleSnapshop.getValue(ScheduleFirebase::class.java)
						scheduleArrayList.add(scheduler!!)
					}
					scheduleRecyclerView.adapter = MyAdapter(scheduleArrayList)
				}
			}

			override fun onCancelled(error: DatabaseError) {
				TODO("Not yet implemented")
			}


		})
	}
}