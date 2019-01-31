package com.skeleton.android.core.dao

import android.arch.persistence.room.*
import com.skeleton.android.features.events.EventEntity

@Dao
interface EventDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvent(eventEntity: EventEntity)

    @Update
    fun updateEvent(eventEntity: EventEntity)

    @Delete
    fun deleteEvent(eventEntity: EventEntity)

    @Query("SELECT * FROM EventEntity")
    fun getEvents(): List<EventEntity>

    @Query("SELECT * FROM EventEntity WHERE id == :eventId")
    fun getEventById(eventId: Int): List<EventEntity>
}