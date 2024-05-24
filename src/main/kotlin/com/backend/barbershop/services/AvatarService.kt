package com.backend.barbershop.services

import com.backend.barbershop.exceptions.UnsupportedMediaTypeException
import com.backend.barbershop.files.S3Storage
import com.backend.barbershop.models.User
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class AvatarService(val storage: S3Storage) {
  companion object {
    private const val FOLDER = "avatars"
    const val DEFAULT_AVATAR = "$FOLDER/default-avatar.jpg"
    private val log = LoggerFactory.getLogger(AvatarService::class.java)
  }

  fun save(user: User, avatar: MultipartFile): String {
    try {
      val extension = when (avatar.contentType) {
        "image/jpeg" -> "jpg"
        "image/png" -> "png"
        else -> throw UnsupportedMediaTypeException("Unsupported media type")
      }

      val name = "${user.id}-avatar.$extension"
      storage.uploadFile(user, "$FOLDER/$name", avatar)
      return name
    } catch (e: Exception) {
      log.error("Error saving avatar", e.message)
      throw e
    }
  }

  fun urlFor(path: String): String {
    return storage.urlFor("$FOLDER/$path")
  }
}