package com.example.manorroom

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.manorroom.databinding.ListItemZodiacBinding


class ZodiacHolder(
    private val binding: ListItemZodiacBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(zodiac: Zodiac, onZodiacClicked: (zodiacId:Int) -> Unit) {
        binding.zodiacName.text = zodiac.name

        // send to the ZodiacDetailFragment

        binding.root.setOnClickListener {
            onZodiacClicked((zodiac.id)!!.toInt())
        }

    }

}
class ZodiacListAdapter(
    private val zodiacs: List<Zodiac>,
    private val onZodiacClicked: (zodiacId: Int) -> Unit
) : RecyclerView.Adapter<ZodiacHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ZodiacHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemZodiacBinding.inflate(inflater, parent, false)
        return ZodiacHolder(binding)
    }

    override fun onBindViewHolder(holder: ZodiacHolder, position: Int) {
        val zodiac = zodiacs[position]
        holder.bind(zodiac, onZodiacClicked)
    }

    override fun getItemCount() = zodiacs.size
}
