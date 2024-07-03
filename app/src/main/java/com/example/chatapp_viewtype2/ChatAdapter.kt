package com.example.chatapp_viewtype2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp_viewtype2.databinding.ReceiverViewBinding
import com.example.chatapp_viewtype2.databinding.SenderViewBinding

class ChatAdapter (
    private val chatlist: List<Chat>
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private lateinit var senderViewBinding: SenderViewBinding
    private lateinit var receiverViewBinding: ReceiverViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if(viewType == SENDER_VIEW){   // if SENDER_VIEW =1  senderviewbinding is going to inflate the view
            senderViewBinding = SenderViewBinding.inflate(layoutInflater, parent, false)
            SenderViewHolder(senderViewBinding)
        }else{
            receiverViewBinding = ReceiverViewBinding.inflate(layoutInflater,parent, false)
            ReceiverViewHolder(receiverViewBinding)
        }
    }

        // segregates the item based on its view type here for sender and receiver
    override fun getItemViewType(position: Int): Int {
        return chatlist[position].viewtype

    }

    override fun getItemCount() =chatlist.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(chatlist[position].viewtype == SENDER_VIEW) { // it checks the viewtypeof each item to bind in background
            (holder as SenderViewHolder).bind(chatlist[position])
        }else{
            (holder as ReceiverViewHolder).bind(chatlist[position])
        }
    }


    inner class SenderViewHolder(private val view:SenderViewBinding):RecyclerView.ViewHolder(view.root){
        fun bind(chat:Chat){
           view.txtSender.text= chat.message
        }

    }

    inner class ReceiverViewHolder(private val view:ReceiverViewBinding): RecyclerView.ViewHolder(view.root){
        fun bind(chat: Chat){
            view.txtReceiver.text=chat.message
        }
    }

    companion object{
        const val SENDER_VIEW=1
    }

}