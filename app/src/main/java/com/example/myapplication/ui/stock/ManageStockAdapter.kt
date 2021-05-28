package com.example.myapplication.ui.stock

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.datamanagestok.ManageStock
import kotlinx.android.synthetic.main.manage_stock.view.*

class ManageStockAdapter(
    var items: List<ManageStock>,
    private val viewModel: ManageViewModel
): RecyclerView.Adapter<ManageStockAdapter.ManageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.manage_stock, parent, false)
        return ManageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ManageViewHolder, position: Int) {
        val curManageStock = items[position]

        holder.itemView.tvName.text = curManageStock.name
        holder.itemView.tvAmount.text = "${curManageStock.amount}"
        holder.itemView.edReminder.setText("${curManageStock.reminder}")

        holder.itemView.edReminder.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(holder.itemView.edReminder.text.isEmpty()){
                    holder.itemView.edReminder.setText("0")
                }
                curManageStock.reminder = (holder.itemView.edReminder.text.toString()).toInt()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        holder.itemView.ivDelete.setOnClickListener{
            viewModel.delete(curManageStock)
        }

        holder.itemView.ivMinus.setOnClickListener{
            if(curManageStock.amount > 0)
                curManageStock.amount--
            viewModel.upsert(curManageStock)
            if(curManageStock.amount == curManageStock.reminder){
                Log.d("MASUK","adsfads")
                var builder = NotificationCompat.Builder(holder.itemView.context, "")
                    .setSmallIcon(R.drawable.ic_plus)
                    .setContentTitle("Item Reminder")
                    .setContentText("Your stock of ${curManageStock.name} is running low")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                val notificationManager: NotificationManager =
                        holder.itemView.context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val channelId = "Your_channel_id"
                    val channel = NotificationChannel(
                            channelId,
                            "Channel human readable title",
                            NotificationManager.IMPORTANCE_HIGH
                    )
                    notificationManager.createNotificationChannel(channel);
                    builder.setChannelId(channelId)
                }

                notificationManager.notify(position, builder.build());
            }



        }

        holder.itemView.ivPlus.setOnClickListener{
            curManageStock.amount++
            viewModel.upsert(curManageStock)
        }

    }

    inner class ManageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}