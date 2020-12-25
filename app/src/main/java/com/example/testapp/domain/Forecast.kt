package com.example.testapp.domain


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml


@Xml
@Entity
data class Forecast(
    @PrimaryKey(autoGenerate = false)
    @Attribute
    val created: String,
    @Element
    val direction: List<Direction>,
    @PropertyElement
    val message: String,
    @Attribute
    val stop: String,
    @Attribute
    val stopAbv: String
)

@Xml
@Entity
data class Direction(
    @SerializedName("name")
    @Attribute(name = "name", converter = TramDirectionConverters::class)
    val direction: TramDirection,
    @Element
    val tram: List<Tram>
)

@Xml
@Entity
data class Tram(
    @Attribute
    val dueMins: String,
    @Attribute
    val destination: String
)

enum class TramDirection {
    @SerializedName("Inbound")
    INBOUND,

    @SerializedName("Outbound")
    OUTBOUND
}