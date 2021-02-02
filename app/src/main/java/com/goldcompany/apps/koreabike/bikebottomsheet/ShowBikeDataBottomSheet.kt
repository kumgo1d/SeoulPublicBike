package com.goldcompany.apps.koreabike.bikebottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentBikeDataBottomSheetBinding
import com.goldcompany.apps.koreabike.favoritelist.FavoriteListAdapter
import com.goldcompany.apps.koreabike.favoritelist.FavoriteListItem
import com.goldcompany.apps.koreabike.favoritelist.FavoriteListItemHelper
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ShowBikeDataBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentBikeDataBottomSheetBinding

    private lateinit var viewModel: ShowBikeDataBottomSheetViewModel

    var helper: FavoriteListItemHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        helper = Room.databaseBuilder(requireContext(), FavoriteListItemHelper::class.java, "favorite_list")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentBikeDataBottomSheetBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(ShowBikeDataBottomSheetViewModel::class.java)

        val station = requireArguments().getString("station_name")
        val parking = "자전거 : " + requireArguments().getString("parking_bike")
        val rack = "주차가능 : " + requireArguments().getString("rack_bike")
        val no = station!!.split(".")[0].toLong()
        val bikeData = BottomSheetBikeData(no, station, parking, rack)

        viewModel.bottomSheetBikeData(bikeData)

        binding.viewModel = viewModel
        binding.addFavoriteButton.setOnClickListener {
            addFavoriteButton(bikeData)
        }

        return binding.root
    }

    private fun addFavoriteButton(bikeData: BottomSheetBikeData) {
        val adapter = FavoriteListAdapter()
        val item = FavoriteListItem(bikeData.stationNumber, bikeData.stationName, bikeData.parkingBike, bikeData.rackBike)

        helper?.FavoriteListItemDAO()?.insert(item)

        adapter.listItem.clear()
        adapter.listItem.addAll(helper?.FavoriteListItemDAO()?.getAll() ?: mutableListOf())
        adapter.notifyDataSetChanged()

        dismiss()

        Toast.makeText(context, R.string.add_favorite_item, Toast.LENGTH_SHORT).show()
    }
}