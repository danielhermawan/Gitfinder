package co.folto.gitfinder.data.model.serializer

import co.folto.gitfinder.data.model.Repo
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

/**
 * Created by Daniel on 5/31/2017 for GitFInder project.
 */
class RepoSerializer: JsonSerializer<Repo> {
    override fun serialize(src: Repo, typeOfSrc: Type?, context: JsonSerializationContext): JsonElement {
        val jsonObject = JsonObject()
        jsonObject.addProperty("id", src.id)
        jsonObject.addProperty("name", src.name)
        jsonObject.add("owner", context.serialize(src.owner))
        jsonObject.addProperty("fullName", src.fullName)
        jsonObject.addProperty("description", src.description)
        jsonObject.addProperty("private", src.private)
        jsonObject.addProperty("fork", src.fork)
        jsonObject.addProperty("url", src.url)
        jsonObject.addProperty("htmlUrl", src.htmlUrl)
        return jsonObject
    }
}