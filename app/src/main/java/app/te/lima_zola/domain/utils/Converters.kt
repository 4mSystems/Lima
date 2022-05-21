package app.te.lima_zola.domain.utils

import androidx.room.TypeConverter
import app.te.lima_zola.domain.general.entity.countries.CityModel
import app.te.lima_zola.domain.general.entity.countries.CountryModel
import app.te.lima_zola.domain.general.entity.countries.GovernModel
import app.te.lima_zola.domain.home.models.CategoriesApiModel
import app.te.lima_zola.domain.home.models.Slider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {

  @TypeConverter
  fun fromInstructorString(value: String): List<CategoriesApiModel> {
    val listType: Type = object : TypeToken<List<CategoriesApiModel>>() {}.type
    return Gson().fromJson(value, listType)
  }

  @TypeConverter
  fun fromInstructorList(list: List<CategoriesApiModel>): String {
    val gson = Gson()
    return gson.toJson(list)
  }

  @TypeConverter
  fun fromSliderString(value: String): List<Slider> {
    val listType: Type = object : TypeToken<List<Slider>>() {}.type
    return Gson().fromJson(value, listType)
  }

  @TypeConverter
  fun fromSliderList(list: List<Slider>): String {
    val gson = Gson()
    return gson.toJson(list)
  }

  @TypeConverter
  fun fromCountriesString(value: String): List<CountryModel> {
    val listType: Type = object : TypeToken<List<CountryModel>>() {}.type
    return Gson().fromJson(value, listType)
  }

  @TypeConverter
  fun fromCountriesList(list: List<CountryModel>): String {
    val gson = Gson()
    return gson.toJson(list)
  }

  @TypeConverter
  fun fromGovernString(value: String): List<GovernModel> {
    val listType: Type = object : TypeToken<List<GovernModel>>() {}.type
    return Gson().fromJson(value, listType)
  }

  @TypeConverter
  fun fromGovernList(list: List<GovernModel>): String {
    val gson = Gson()
    return gson.toJson(list)
  }

  @TypeConverter
  fun fromCityString(value: String): List<CityModel> {
    val listType: Type = object : TypeToken<List<CityModel>>() {}.type
    return Gson().fromJson(value, listType)
  }

  @TypeConverter
  fun fromCityList(list: List<CityModel>): String {
    val gson = Gson()
    return gson.toJson(list)
  }

}