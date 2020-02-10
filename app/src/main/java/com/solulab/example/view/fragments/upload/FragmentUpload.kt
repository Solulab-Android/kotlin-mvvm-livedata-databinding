package com.solulab.example.view.fragments.upload

import android.app.ProgressDialog
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.solulab.example.R
import com.solulab.example.databinding.FragmentUploadBinding
import com.solulab.example.view.fragments.BaseFragment
import gun0912.tedimagepicker.builder.TedImagePicker
import id.zelory.compressor.Compressor
import java.io.File
import java.io.IOException


class FragmentUpload : BaseFragment() {
    private val viewModel by lazy { UploadViewModel(this.mContext) }
    private lateinit var uplodBinding: FragmentUploadBinding


    lateinit var name: String

    var fileArray: ArrayList<File> = ArrayList<File>()
    var uriList: ArrayList<Uri> = ArrayList<Uri>()
    lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        uplodBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_upload,
            container,
            false
        )

        progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Uploading...")
        progressDialog.setCanceledOnTouchOutside(false)
        uplodBinding.buttonChoose.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
//                onClickCamera()

                uriList.clear()
                context?.let {
                    TedImagePicker.with(it)
                        .startMultiImage { uriList -> showMultiImage(uriList) }
                }
            }

        })

        uplodBinding.buttonUpload.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (uriList.size > 0) {
                    try {
                        for (i in uriList) {
                            /* val path: String =
                                 Utils.getRealPathFromURI(activity, i.path)*/
                            val file: File = Compressor(activity)
                                .compressToFile(File(i.path))
                            fileArray.add(file)

                        }
                        if (uplodBinding.etImageNme.text.isNotEmpty()) {
                            name = uplodBinding.etImageNme.text.toString()
                            viewModel.UploadImage(fileArray, name)
                        } else {
                            Toast.makeText(activity, "Please Enter name", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                        Log.v("uploadFragment", e.message)
                        return
                    }
                }

            }
        })

        viewModel.uploadLiveData.observe(this, androidx.lifecycle.Observer {
            if (it != null && it) {
                Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show()

            }
        })

        viewModel.isLoading.observe(this, Observer {
            if (it) {
                progressDialog.show()
            } else {
                progressDialog.dismiss()
            }
        })
        return uplodBinding.getRoot()
    }

    private fun showMultiImage(uriList: List<Uri>) {


        this.uriList = uriList as ArrayList<Uri>

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}