package com.vn.f49kh.utils

import android.content.Context
import android.graphics.Bitmap
import io.reactivex.Flowable
import java.io.File
import java.io.IOException
import java.util.concurrent.Callable

class FileCompressor {
    //max width and height values of the compressed image is taken as 612x816
    private var maxWidth = 612
    private var maxHeight = 816
    private var compressFormat: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG
    private var quality = 80
    private var destinationDirectoryPath: String? = null

    constructor(context: Context?) {
        destinationDirectoryPath = context?.cacheDir?.path + File.separator + "images"
    }

    fun setMaxWidth(maxWidth: Int): FileCompressor {
        this.maxWidth = maxWidth
        return this
    }

    fun setMaxHeight(maxHeight: Int): FileCompressor {
        this.maxHeight = maxHeight
        return this
    }

    fun setCompressFormat(compressFormat: Bitmap.CompressFormat): FileCompressor {
        this.compressFormat = compressFormat
        return this
    }

    fun setQuality(quality: Int): FileCompressor {
        this.quality = quality
        return this
    }

    fun setDestinationDirectoryPath(destinationDirectoryPath: String): FileCompressor {
        this.destinationDirectoryPath = destinationDirectoryPath
        return this
    }

    fun compressToFile(imageFile: File): File {
        return compressToFile(imageFile, imageFile.name)
    }

    fun compressToFile(imageFile: File, compressedFileName: String): File {
        return ImageUtil.compressImage(
            imageFile, maxWidth, maxHeight, compressFormat, quality,
            destinationDirectoryPath + File.separator + compressedFileName
        )
    }

    fun compressToBitmap(imageFile: File): Bitmap {
        return ImageUtil.decodeSampledBitmapFromFile(imageFile, maxWidth, maxHeight)
    }

    fun compressToFileAsFlowable(imageFile: File): Flowable<File> {
        return compressToFileAsFlowable(imageFile, imageFile.name)
    }

    fun compressToFileAsFlowable(imageFile: File, compressedFileName: String): Flowable<File> {
        return Flowable.defer(Callable {
            try {
                return@Callable Flowable.just(compressToFile(imageFile, compressedFileName))
            } catch (e: IOException) {
                return@Callable Flowable.error<File>(e)
            }
        })
    }

    fun compressToBitmapAsFlowable(imageFile: File): Flowable<Bitmap> {
        return Flowable.defer(Callable {
            try {
                return@Callable Flowable.just(compressToBitmap(imageFile))
            } catch (e: IOException) {
                return@Callable Flowable.error<Bitmap>(e)
            }
        })
    }
}