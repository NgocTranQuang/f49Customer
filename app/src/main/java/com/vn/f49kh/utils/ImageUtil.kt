package com.vn.f49kh.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.util.Base64
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class ImageUtil {
    companion object {
        fun compressImage(
            imageFile: File,
            reqWidth: Int,
            reqHeight: Int,
            compressFormat: Bitmap.CompressFormat,
            quality: Int,
            destinationPath: String
        ): File {
            var fileOutputStream: FileOutputStream? = null
            val file = File(destinationPath).parentFile
            if (!file.exists()) {
                file.mkdirs()
            }
            try {
                fileOutputStream = FileOutputStream(destinationPath)
                // write the compressed bitmap at the destination specified by destinationPath.
                decodeSampledBitmapFromFile(imageFile, reqWidth, reqHeight).compress(
                    compressFormat,
                    quality,
                    fileOutputStream
                )
            } finally {
                if (fileOutputStream != null) {
                    fileOutputStream.flush()
                    fileOutputStream.close()
                }
            }

            return File(destinationPath)
        }

        fun decodeSampledBitmapFromFile(imageFile: File, reqWidth: Int, reqHeight: Int): Bitmap {
            // First decode with inJustDecodeBounds=true to check dimensions
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeFile(imageFile.absolutePath, options)

            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false

            var scaledBitmap = BitmapFactory.decodeFile(imageFile.absolutePath, options)

            //check the rotation of the image and display it properly
            val exif: ExifInterface
            exif = ExifInterface(imageFile.absolutePath)
            val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0)
            val matrix = Matrix()
            if (orientation == 6) {
                matrix.postRotate(90f)
            } else if (orientation == 3) {
                matrix.postRotate(180f)
            } else if (orientation == 8) {
                matrix.postRotate(270f)
            }
            scaledBitmap =
                Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.width, scaledBitmap.height, matrix, true)

            return scaledBitmap
        }

        fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
            // Raw height and width of image
            val height = options.outHeight
            val width = options.outWidth
            var inSampleSize = 1

            if (height > reqHeight || width > reqWidth) {

                val halfHeight = height / 2
                val halfWidth = width / 2

                // Calculate the largest inSampleSize value that is a power of 2 and keeps both
                // height and width larger than the requested height and width.
                while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                    inSampleSize *= 2
                }
            }

            return inSampleSize
        }

        fun convertImageFileToBase64(imageFile: File): String {
            val bm = BitmapFactory.decodeFile(imageFile.path)
            val baos = ByteArrayOutputStream()
            bm.compress(Bitmap.CompressFormat.JPEG, 80, baos) //bm is the bitmap object
            val b = baos.toByteArray()
            val encodedImage = Base64.encodeToString(b, Base64.DEFAULT)
            return encodedImage
        }

        fun animateArrowExpand(imageView: ImageView) {
            val rotate = RotateAnimation(360f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            rotate.duration = 300
            rotate.fillAfter = true
            imageView.startAnimation(rotate)
        }

        fun animateArrowCollapse(imageView: ImageView) {
            val rotate = RotateAnimation(180f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            rotate.duration = 300
            rotate.fillAfter = true
            imageView.startAnimation(rotate)
        }
    }


}