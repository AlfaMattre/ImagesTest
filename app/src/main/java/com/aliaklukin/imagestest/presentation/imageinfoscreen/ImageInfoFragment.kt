package com.aliaklukin.imagestest.presentation.imageinfoscreen

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aliaklukin.imagestest.R
import com.aliaklukin.imagestest.databinding.FragmentImageInfoBinding
import com.aliaklukin.imagestest.presentation.base.BaseFragment
import com.aliaklukin.imagestest.presentation.model.HitLocal
import com.aliaklukin.imagestest.presentation.utils.loadImage

class ImageInfoFragment: BaseFragment<FragmentImageInfoBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val args: ImageInfoFragmentArgs by navArgs()
        val hitLocal = args.hitLocal

        setImageDetails(hitLocal)
    }

    private fun setImageDetails(hitLocal: HitLocal) {
        binding.run {
            with(hitLocal) {
                largeImageView.loadImage(largeImageURL)
                imageSizeTV.text = getString(R.string.image_size, imageSize)
                imageTypeTV.text = getString(R.string.image_type, type)
                imageTagsTV.text = getString(R.string.image_tags, tags)
                userNameTV.text = getString(R.string.image_uploaded_by, user)
                viewsTV.text = getString(R.string.image_views, views)
                likesTV.text = getString(R.string.image_likes, likes)
                commentsTV.text = getString(R.string.image_comments, comments)
                downloadsTV.text = getString(R.string.image_downloads, downloads)
            }
        }
    }
}