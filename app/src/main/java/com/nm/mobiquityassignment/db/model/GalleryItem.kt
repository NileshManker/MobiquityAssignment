package com.nm.mobiquityassignment.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gallery_item")
data class GalleryItem(

    @PrimaryKey(autoGenerate = false)
    var id: Int,

    @ColumnInfo(name = "albumId")
    var albumId: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "thumbnailUrl")
    var thumbnailUrl: String,
)