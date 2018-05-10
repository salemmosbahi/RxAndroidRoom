package it.sharedservices.rxandroidroom.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import it.sharedservices.rxandroidroom.room.dao.CommentDao;
import it.sharedservices.rxandroidroom.room.entity.Comment;

@Database(entities = {Comment.class}, version = 1)
public abstract class RxDataBase extends RoomDatabase {
    public abstract CommentDao commentDao();

    private static RxDataBase INSTANCE;
    public static RxDataBase database(Context pContext) {
        if (INSTANCE == null)
            INSTANCE = Room.databaseBuilder(pContext.getApplicationContext(),
                    RxDataBase.class, "comment_db")
                    .allowMainThreadQueries()
                    .build();
        return INSTANCE;
    }

    public static void clean() {
        INSTANCE = null;
    }
}
