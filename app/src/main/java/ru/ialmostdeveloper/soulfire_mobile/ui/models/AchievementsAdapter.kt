package ru.ialmostdeveloper.soulfire_mobile.ui.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import ru.ialmostdeveloper.soulfire_mobile.R
import java.util.ArrayList


class AchievementsAdapter(private val mContext: Context,
                          list: MutableList<Achievement>) :
    ArrayAdapter<Achievement>(mContext, 0, list) {

    private var achievementsList: List<Achievement> = ArrayList()


    override fun getView(position: Int, @Nullable convertView: View?, parent: ViewGroup): View {
        var listItem = convertView
        if (listItem == null) listItem =
            LayoutInflater.from(mContext).inflate(R.layout.achievement_item, parent, false)

        val currentAchievement = achievementsList[position]

        val image = listItem!!.findViewById<View>(R.id.imageView_poster) as ImageView
        image.setImageResource(currentAchievement.getmImageDrawable())

        val name = listItem.findViewById<View>(R.id.textView_name) as TextView
        name.setText(currentAchievement.getmName())

        val release = listItem.findViewById<View>(R.id.textView_release) as TextView
        release.setText(currentAchievement.getmDescription())
        return listItem
    }

    init {
        achievementsList = list
    }
}