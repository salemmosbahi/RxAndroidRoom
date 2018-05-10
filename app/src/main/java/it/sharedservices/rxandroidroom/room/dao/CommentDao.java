package it.sharedservices.rxandroidroom.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import io.reactivex.Flowable;
import it.sharedservices.rxandroidroom.room.entity.Comment;

@Dao
public interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Comment entity);

    @Query("SELECT * FROM comment LIMIT 1")
    Flowable<Comment> findOne();
}
