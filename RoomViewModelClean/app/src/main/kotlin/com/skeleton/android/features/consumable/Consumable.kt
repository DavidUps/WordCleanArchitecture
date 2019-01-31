package com.skeleton.android.features.consumable

import com.skeleton.android.core.extension.empty


data class Consumable(val id: Int,
                      val name: String) {

    companion object {
        fun empty() = Consumable(0, String.empty())
    }

    fun toConsumableView() = ConsumableView(id, name)

    fun toConsumableEntity() = ConsumableEntity(id, name)

}
