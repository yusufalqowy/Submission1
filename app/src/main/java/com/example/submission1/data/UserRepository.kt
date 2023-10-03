import android.app.Application
import androidx.lifecycle.LiveData
import com.example.submission1.data.entity.FavUser
import com.example.submission1.database.FavUserDao
import com.example.submission1.database.UserDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UserRepository(application: Application) {
    private val mfavUserDao: FavUserDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = UserDatabase.getDatabase(application)
        mfavUserDao = db.userGithubDao()
    }

    fun getAllUser(): LiveData<List<FavUser>> {
        return mfavUserDao.getFavUser()
    }

    fun insert(id: FavUser) {
        executorService.execute { mfavUserDao.addToFavorit(id) }
    }

    fun delete(id: FavUser) {
        executorService.execute { mfavUserDao.delete(id) }
    }

    fun findFavUserByUsername(username: String): LiveData<List<FavUser>> {
        return mfavUserDao.getFavUserByUsername(username)
    }
}