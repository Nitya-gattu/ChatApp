package com.example.chatapp_viewtype2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp_viewtype2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var chatAdapter: ChatAdapter  //chatadapter reference object
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initviews()

    }

    private fun initviews() {
        with(binding){
            recyclerview.layoutManager =LinearLayoutManager(this@MainActivity)
            chatAdapter = ChatAdapter(chats)
            recyclerview.adapter = chatAdapter


            btnSender.setOnClickListener {
                chats.add(Chat(viewtype = 1, message = editTxt.text.toString()))
                chatAdapter.notifyItemInserted(chats.size)
                editTxt.text.clear()
            }

            btnReceiver.setOnClickListener {
                chats.add(Chat(viewtype = 2, message = editTxt.text.toString()))
                chatAdapter.notifyItemInserted(chats.size)
                editTxt.text.clear()
            }


            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                   val position = viewHolder.adapterPosition
                    chats.removeAt(position)
                    chatAdapter.notifyItemRemoved(position)
                    Toast.makeText(this@MainActivity, "chat deleted", Toast.LENGTH_SHORT).show()
                }


            }).attachToRecyclerView(binding.recyclerview)

        }
    }

    private val chats = mutableListOf(
        Chat(viewtype = 1, "hello"),
        Chat(viewtype = 2, "hey"),
        Chat(viewtype = 1, "Any plans?"),
        Chat(viewtype = 2, "planning of movie wanna join?"),
        Chat(viewtype = 1, "sounds good")
    )
}