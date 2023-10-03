import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.submission1.data.entity.FavUser
import com.example.submission1.database.FavUserDao

class UserRepository(val mfavUserDao: FavUserDao) {

    fun getAllUser(): LiveData<List<FavUser>> {
        return mfavUserDao.getFavUser()
    }

    @WorkerThread
    suspend fun insert(username: FavUser) {
        mfavUserDao.addToFavorit(username)
    }

    suspend fun delete(username: FavUser) {
        mfavUserDao.delete(username)
    }
}
