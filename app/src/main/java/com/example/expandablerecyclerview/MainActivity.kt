package com.example.expandablerecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expandablerecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listData : MutableList<ParentData> = ArrayList()
        val parentData: Array<String> = arrayOf("Andhra Pradesh", "Telangana", "Karnataka", "TamilNadu")
        val childDataData1: MutableList<ChildData> = mutableListOf(
            ChildData("Anathapur"),ChildData("Chittoor"),ChildData("Nellore"),ChildData("Guntur"),
            ChildData("Anathapur"),ChildData("Chittoor"),ChildData("Nellore"),ChildData("Guntur"),
            ChildData("Anathapur"),ChildData("Chittoor"),ChildData("Nellore"),ChildData("Guntur"),
            ChildData("Anathapur"),ChildData("Chittoor"),ChildData("Nellore"),ChildData("Guntur"),
            ChildData("Anathapur"),ChildData("Chittoor"),ChildData("Nellore"),ChildData("Guntur"),
            ChildData("Anathapur"),ChildData("Chittoor"),ChildData("Nellore"),ChildData("Guntur"),
        )
        val childDataData2: MutableList<ChildData> = mutableListOf(
            ChildData("Rajanna Sircilla"), ChildData("Karimnagar"), ChildData("Siddipet"),
            ChildData("Rajanna Sircilla"), ChildData("Karimnagar"), ChildData("Siddipet"),
            ChildData("Rajanna Sircilla"), ChildData("Karimnagar"), ChildData("Siddipet"),
            ChildData("Rajanna Sircilla"), ChildData("Karimnagar"), ChildData("Siddipet"),
            ChildData("Rajanna Sircilla"), ChildData("Karimnagar"), ChildData("Siddipet"),
            ChildData("Rajanna Sircilla"), ChildData("Karimnagar"), ChildData("Siddipet"),
            ChildData("Rajanna Sircilla"), ChildData("Karimnagar"), ChildData("Siddipet")
        )
        val childDataData3: MutableList<ChildData> = mutableListOf(
            ChildData("Chennai"), ChildData("Erode"),
            ChildData("Chennai"), ChildData("Erode"),
            ChildData("Chennai"), ChildData("Erode")
        )

        val parentObj1 = ParentData(parentTitle = parentData[0], subList = childDataData1)
        val parentObj2 = ParentData(parentTitle = parentData[1], subList = childDataData2)
        val parentObj3 = ParentData(parentTitle = parentData[2])
        val parentObj4 = ParentData(parentTitle = parentData[1], subList = childDataData3)

        listData.add(parentObj1)
        listData.add(parentObj2)
        listData.add(parentObj3)
        listData.add(parentObj4)


        val adapter = ExapndleAdapter(this@MainActivity, listData)
        val exRecycleView = findViewById<RecyclerView>(R.id.exRecycle)
        exRecycleView.addItemDecoration(
            StickyHeaderDecoration(adapter, binding.root)
        )
        exRecycleView.layoutManager = LinearLayoutManager(this)
        exRecycleView.adapter =adapter

    }
}