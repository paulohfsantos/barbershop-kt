package com.backend.barbershop.files

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider
import com.amazonaws.regions.Regions.US_EAST_1
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.transfer.TransferManagerBuilder
import com.backend.barbershop.models.User
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class S3Storage {
  private val s3: AmazonS3 = AmazonS3ClientBuilder.standard()
    .withRegion(US_EAST_1)
    .withCredentials(EnvironmentVariableCredentialsProvider())
    .build()

  fun uploadFile(user: User, path: String, file: MultipartFile): String {
    val transferManager = TransferManagerBuilder.standard()
      .withS3Client(s3)
      .build()

    val meta = ObjectMetadata()
    manageMetadata(meta, user, file)

    transferManager.upload(
      BUCKET_NAME,
      path,
      file.inputStream,
      meta
    ).waitForUploadResult()

    return path
  }

  fun urlFor(path: String): String {
    return "https://$BUCKET_NAME.s3.amazonaws.com/$path"
  }

  private fun manageMetadata(meta: ObjectMetadata, user: User, file: MultipartFile): ObjectMetadata {
    meta.contentType = file.contentType!!
    meta.contentLength = file.size
    meta.userMetadata["userId"] = "${user.id}"
    meta.userMetadata["originalFilename"] = file.originalFilename

    return meta
  }


  companion object {
    private const val BUCKET_NAME = "barbershop-server-public"
  }
}