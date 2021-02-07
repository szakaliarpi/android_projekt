package com.example.android_projekt.restaurants

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.android_projekt.databinding.FragmentRestaurantsBinding
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.android_projekt.RestaurantsApi
import kotlinx.android.synthetic.main.fragment_restaurants.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class   RestaurantsFragment : Fragment() {

    private val viewModel: RestaurantsViewModel by lazy{
        ViewModelProvider(this).get(RestaurantsViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //val view : View = inflater.inflate(R.layout.fragment_restaurants, container, false)
        val binding = FragmentRestaurantsBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.photosRyc.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener{
            viewModel.displayPropertyDetails(it)
        })

        viewModel.navigateToSelectedProperty.observe(this, Observer {
            if ( null != it ) {
                this.findNavController().navigate(RestaurantsFragmentDirections.actionShowDetail(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })


        fun reload(){
            //reload fragment
            val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
            if (Build.VERSION.SDK_INT >= 26) {
                ft.setReorderingAllowed(false)
            }
            ft.detach(this).attach(this).commit()
        }

        val job = Job()
        val coroutineScope = CoroutineScope(job + Dispatchers.Main)


        coroutineScope.launch {
            val allReqres = RestaurantsApi.retrofitService.getProperties()
            val listResult = allReqres.await()

            binding.btnNext.setOnClickListener(){
                Log.d("pageBefore:", listResult.page.toString())

                if(listResult.page == 1){
                    Toast.makeText(context, "1st page", Toast.LENGTH_SHORT).show()
                    listResult.page++
                    Log.d("pageAfter:", listResult.page.toString())

                    reload()
                }
            }

        }

        setHasOptionsMenu(true)

        return binding.root

    }

}


