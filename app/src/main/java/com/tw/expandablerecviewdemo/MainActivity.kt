package com.tw.expandablerecviewdemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tw.expandablerecviewdemo.adapters.HeaderAdapter
import com.tw.expandablerecviewdemo.adapters.NestedAdapter
import com.tw.expandablerecviewdemo.databinding.ActivityMainBinding
import com.tw.expandablerecviewdemo.model.BuildingsTable
import com.tw.expandablerecviewdemo.model.ExpandableModel
import com.tw.expandablerecviewdemo.model.MembersTable

class MainActivity : AppCompatActivity(), HeaderAdapter.OnItemClickListener, NestedAdapter.OnItemClickListener {

    private val TAG:String = this.javaClass.simpleName
    lateinit var mbinding: ActivityMainBinding

    val mExpandableList : MutableList<ExpandableModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mbinding.root)

        val building1 = BuildingsTable(1, "Shanti Niwas", false)
        val building2 = BuildingsTable(2, "Sharda Niwas", false)
        val building3 = BuildingsTable(3, "Rajput Niwas", false)


        val member1 = MembersTable(1, 1, "Ranjit Yadav", "15/08/1947", "Male", false)
        val member2 = MembersTable(2, 1, "Sunny Verma", "15/08/1947", "Female", false)
        val member3 = MembersTable(3, 1, "Sunil Vishwas", "15/08/1947", "Male", false)

        val member4 = MembersTable(4, 2, "Seema", "15/08/1947", "Female", false)
        val member5 = MembersTable(5, 2, "Shashi", "15/08/1947", "Male", false)
        val member6 = MembersTable(6, 2, "Pooja", "15/08/1947", "Female", false)
        val member7 = MembersTable(7, 2, "Priti", "15/08/1947", "Male", false)

        val member8 = MembersTable(8, 3, "Vinod Rajput", "15/08/1947", "Female", false)
        val member9 = MembersTable(9, 3, "Samar Rajput", "15/08/1947", "Male", false)
        val member10 = MembersTable(10, 3, "Bhumi Rajput", "15/08/1947", "Female", false)

        val members1: MutableList<MembersTable> = ArrayList()
        members1.add(member1)
        members1.add(member2)
        members1.add(member3)

        val members2: MutableList<MembersTable> = ArrayList()
        members2.add(member4)
        members2.add(member5)
        members2.add(member6)
        members2.add(member7)

        val members3: MutableList<MembersTable> = ArrayList()
        members3.add(member8)
        members3.add(member9)
        members3.add(member10)


        mExpandableList.add(0, ExpandableModel(members1, building1))
        mExpandableList.add(1, ExpandableModel(members2, building2))
        mExpandableList.add(2, ExpandableModel(members3, building3))

        setupAdapterData(mExpandableList)
    }

    private fun setupAdapterData(mExpandableList: MutableList<ExpandableModel>) {

        val adapter = HeaderAdapter(mExpandableList, this@MainActivity, this@MainActivity)
        mbinding.mainRecView.adapter = adapter
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        mbinding.mainRecView.layoutManager = llm

    }

    override fun onFamilyClicked(position: Int, data: ExpandableModel) {
        //Header Adapter
        Toast.makeText(this@MainActivity, "Header1 is Clicked.", Toast.LENGTH_SHORT).show()
        //You can reset some data in adapter and notify to adapter again here

    }

    override fun onFamilyClicked(position: Int, data: ExpandableModel, viewResp: Int) {
        //Header Adapter
        Toast.makeText(this@MainActivity, "Header2 is Clicked.", Toast.LENGTH_SHORT).show()
        //You can reset some data in adapter and notify to adapter again here
    }

    override fun onItemClicked(position: Int, data: MembersTable) {
        //Section Adapter
        Toast.makeText(this@MainActivity, "Child1 is Clicked.", Toast.LENGTH_SHORT).show()
        //You can reset some data in adapter and notify to adapter again here
    }

    override fun onItemClicked(position: Int, data: MembersTable, viewResp: Int) {
        //Section Adapter
        Toast.makeText(this@MainActivity, "Child2 is Clicked.", Toast.LENGTH_SHORT).show()
        //You can reset some data in adapter and notify to adapter again here
    }


}