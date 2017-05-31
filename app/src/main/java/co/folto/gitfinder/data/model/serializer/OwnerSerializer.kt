package co.folto.gitfinder.data.model.serializer

import co.folto.gitfinder.data.model.Owner
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

/**
 * Created by Daniel on 5/31/2017 for GitFInder project.
 */
class OwnerSerializer: JsonSerializer<Owner> {
    override fun serialize(src: Owner, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val jsonObject = JsonObject()
        jsonObject.addProperty("id", src.id)
        jsonObject.addProperty("login", src.login)
        jsonObject.addProperty("avatarUrl", src.avatarUrl)
        jsonObject.addProperty("gravatarId", src.gravatarId)
        return jsonObject
    }

}