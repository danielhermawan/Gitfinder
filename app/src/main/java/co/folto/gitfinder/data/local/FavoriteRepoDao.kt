package co.folto.gitfinder.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import co.folto.gitfinder.data.model.FavoriteRepo
import io.reactivex.Flowable

/**
 * Created by Daniel on 8/16/2017 for GitFInder project.
 */
@Dao
interface FavoriteRepoDao {

    @Query("SELECT * FROM favorite_repos")
    fun getAll(): Flowable<List<FavoriteRepo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favoriteRepo: FavoriteRepo)

    @Query("DELETE FROM favorite_repos WHERE fullName = :fullname")
    fun delete(fullname: String)

    @Query("DELETE FROM favorite_repos")
    fun clear()

    @Query("SELECT * FROM favorite_repos WHERE fullName = :fullname")
    fun getDetail(fullname: String): Flowable<FavoriteRepo>

    @Query("SELECT count(id) FROM favorite_repos WHERE fullName = :fullname")
    fun getCount(fullname: String): Flowable<Int>

}