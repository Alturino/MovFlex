package com.onirutla.movflex.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.onirutla.movflex.favorite.movie.FavoriteMovieFragment
import com.onirutla.movflex.favorite.tv.FavoriteTvFragment

class ViewPagerAdapter(
    private val fa: FragmentActivity,
) : FragmentStateAdapter(fa) {

    private val fragments = listOf(FavoriteMovieFragment(), FavoriteTvFragment())

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

}
