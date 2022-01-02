package ru.ialmostdeveloper.soulfire_mobile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import ru.ialmostdeveloper.soulfire_mobile.R
import ru.ialmostdeveloper.soulfire_mobile.SlideAdapter
import ru.ialmostdeveloper.soulfire_mobile.network.ApiClient
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager


class RegisterFragment1 : Fragment() {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient
    private lateinit var viewPager: ViewPager
    private lateinit var slideAdapter: SlideAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        apiClient = ApiClient()
        sessionManager = SessionManager(this.requireContext())
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)
        viewPager = view.findViewById(R.id.viewpager);
        slideAdapter = SlideAdapter(this.requireContext())
        viewPager.adapter = slideAdapter


//        ViewPager viewPager = findViewById(R.id.viewpager);
//        SlideAdapter slideAdapter = new SlideAdapter(this);
//        viewPager.setAdapter(slideAdapter);
        val self = this
        val btn_welcome: Button = view.findViewById<Button>(R.id.welcome_btn)
        btn_welcome.setOnClickListener(
            View.OnClickListener { v: View? ->
                val position = viewPager.currentItem
                btn_welcome.text = slideAdapter.lst_title[position]

                val fragment: Fragment = RegisterFragment2()

                val fm: FragmentManager = self.requireActivity().getSupportFragmentManager()
                val transaction: FragmentTransaction = fm.beginTransaction()
                transaction.replace(R.id.contentFragment, fragment)
                transaction.commit()
            })
            return view
    }
}