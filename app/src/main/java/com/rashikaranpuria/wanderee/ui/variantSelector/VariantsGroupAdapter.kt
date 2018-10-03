package com.rashikaranpuria.wanderee.ui.variantSelector

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import com.rashikaranpuria.wanderee.AutoUpdatableAdapter
import com.rashikaranpuria.wanderee.R
import com.rashikaranpuria.wanderee.data.api.model.VariantGroupsItem
import com.rashikaranpuria.wanderee.data.api.model.VariationsItem
import kotlinx.android.synthetic.main.variant_group_item.view.*
import kotlinx.android.synthetic.main.variation_item.view.*
import kotlin.properties.Delegates
import org.greenrobot.eventbus.EventBus

class VariantsGroupAdapter(val context: Context) : RecyclerView.Adapter<VariantsGroupAdapter.VariantGroupViewHolder>(), AutoUpdatableAdapter {

    // diff utils for group VariantsGroupAdapter
    var variantGroups: List<VariantGroupsItem> by Delegates.observable(emptyList()) {
        _, oldList, newList ->
        autoNotify(oldList, newList) { o, n -> o.groupId == n.groupId }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VariantGroupViewHolder =
        VariantGroupViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.variant_group_item, parent, false))

    override fun getItemCount(): Int = variantGroups.size

    override fun onBindViewHolder(holder: VariantGroupViewHolder, position: Int) {
        holder.bind(variantGroups[holder.adapterPosition])
    }

    inner class VariantGroupViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sectionTitle: TextView = view.variant_section_title
        val childRecyclerView: RecyclerView = view.childRecyclerView
        lateinit var childAdapter: VariantChildAdapter
        val childLayoutManager = LinearLayoutManager(context)

        fun bind(variantGroupsItem: VariantGroupsItem) {
            sectionTitle.text = variantGroupsItem.name

            // child list adapter
            childAdapter = VariantChildAdapter(context, variantGroupsItem.groupId)
            childAdapter.childVariations = variantGroupsItem.variations
            childRecyclerView.layoutManager = childLayoutManager
            childRecyclerView.adapter = childAdapter
        }
    }

    class VariantChildAdapter(val context: Context, val groupId: String) : RecyclerView.Adapter<VariantChildAdapter.VariantChildViewholder>(), AutoUpdatableAdapter {

        // diff utils for VariantChildAdapter
        var childVariations: List<VariationsItem> by Delegates.observable(emptyList()) {
            _, oldList, newList ->
            autoNotify(oldList, newList) { o, n -> o.id == n.id }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VariantChildViewholder =
            VariantChildViewholder(LayoutInflater.from(parent.context).inflate(R.layout.variation_item, parent, false))

        override fun getItemCount(): Int = childVariations.size

        override fun onBindViewHolder(holder: VariantChildViewholder, position: Int) {
            holder.bind(childVariations[holder.adapterPosition])
        }

        inner class VariantChildViewholder(childView: View) : RecyclerView.ViewHolder(childView) {
            val variantTitle: TextView = childView.variant_title
            val vegMark: ImageView = childView.veg_icon
            val checkBox: RadioButton = childView.checkbox
            val price: TextView = childView.price
            val statusLabel: TextView = childView.status_label
            val rootView: View = childView

            fun bind(item: VariationsItem) {
                // set name of variant
                variantTitle.text = item.name

                // set mark for veg non veg type of variant
                if (item.isVeg == 1) {
                    vegMark.setImageResource(R.drawable.ic_veg_mark)
                } else {
                    vegMark.setImageResource(R.drawable.ic_nonveg_mark)
                }

                // set price of variant
                price.text = String.format(context.resources.getString(R.string.price_string), item.price)

                // default selection of variant
                checkBox.isChecked = item.isSelected

                // disabling item in special way if item is conflicting selection
                // or not in stock
                if (item.inStock == 0) {
                    statusLabel.setText(R.string.out_of_stock)
                    statusLabel.visibility = View.VISIBLE
                    checkBox.isEnabled = false
                } else if (item.isConflictingSelection) {
                    statusLabel.setText(R.string.conflicting_choice)
                    statusLabel.visibility = View.VISIBLE
                    checkBox.isEnabled = false
                } else {
                    statusLabel.visibility = View.GONE
                    checkBox.isEnabled = true
                }

                // child click listener prompts activity to update data according to new selection
                rootView.setOnClickListener {
                    EventBus.getDefault().post(UpdateDataMessage(groupId, item.id))
                }
            }
        }
    }
}