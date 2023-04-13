package com.dhruv.pictobloxclone

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.squareup.picasso.Picasso

class Adapter(val list : MutableList<Tile>,val context: Context) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(R.id.bg)
        val title = itemView.findViewById<TextView>(R.id.label)
        val player = itemView.findViewById<YouTubePlayerView>(R.id.player)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.tile_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list.get(position)
        if (!item.itemBg.isNullOrEmpty()){
            Picasso.get().load(list.get(position).itemBg).into(holder.image);
        }else{
            holder.image.visibility = View.GONE
            holder.player.visibility = View.VISIBLE
            val lister = object : YouTubePlayerListener{
                override fun onApiChange(youTubePlayer: YouTubePlayer) {

                }

                override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {

                }

                override fun onError(
                    youTubePlayer: YouTubePlayer,
                    error: PlayerConstants.PlayerError,
                ) {

                }

                override fun onPlaybackQualityChange(
                    youTubePlayer: YouTubePlayer,
                    playbackQuality: PlayerConstants.PlaybackQuality,
                ) {

                }

                override fun onPlaybackRateChange(
                    youTubePlayer: YouTubePlayer,
                    playbackRate: PlayerConstants.PlaybackRate,
                ) {

                }

                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val videoId = item.videoUrl.toString()
                    youTubePlayer.loadVideo(videoId, 0F)
                    youTubePlayer.play()
                }

                override fun onStateChange(
                    youTubePlayer: YouTubePlayer,
                    state: PlayerConstants.PlayerState,
                ) {

                }

                override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {

                }

                override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {

                }

                override fun onVideoLoadedFraction(
                    youTubePlayer: YouTubePlayer,
                    loadedFraction: Float,
                ) {

                }
            }
            holder.player.enableAutomaticInitialization =false
            holder.player.initialize(lister)
        }
        holder.title.setText(item.itemTitle)
        holder.image.setOnClickListener {
            if (item.itemId != 6){
                holder.player.release()
                val intent =Intent(context,WebActivity::class.java)
                intent.putExtra("url",item.itemLocation)
                intent.flags =FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        }

    }


}