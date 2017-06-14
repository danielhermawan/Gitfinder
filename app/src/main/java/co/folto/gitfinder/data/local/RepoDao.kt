package co.folto.gitfinder.data.local

import android.arch.persistence.room.*
import co.folto.gitfinder.data.model.Repo
import io.reactivex.Flowable

/**
 * Created by Daniel on 6/8/2017 for GitFInder project.
 */
@Dao
interface RepoDao {

    @Query("SELECT * FROM repo")
    fun getAll(): Flowable<List<Repo>>

    @Query("SELECT * FROM repo WHERE fullName = :arg0")
    fun getByName(fullName: String): Flowable<Repo>

    @Query("SELECT * FROM repo WHERE favorite = 1")
    fun getFavorite(): Flowable<List<Repo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg repos: Repo)

    @Update
    fun updateAll(vararg repos: Repo)

    @Delete
    fun delete(repos: Repo)
}