package it.sharedservices.rxandroidroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import it.sharedservices.rxandroidroom.room.RxDataBase;
import it.sharedservices.rxandroidroom.room.entity.Comment;

public class MainView extends AppCompatActivity {

    private Button btnComment;
    private EditText etxtComment;
    private TextView txtComment;

    private RxDataBase mDataBase;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mDataBase = RxDataBase.database(getApplicationContext());

        btnComment = findViewById(R.id.main_btn_comment);
        etxtComment = findViewById(R.id.main_etxt_comment);
        txtComment = findViewById(R.id.main_txt_comment);

        btnComment.setOnClickListener(v -> updateComment());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDisposable.add(value()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(value -> txtComment.setText(value))
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDisposable.clear();
    }

    private void updateComment() {
        String comment = etxtComment.getText().toString();
        mDisposable.add(update(comment)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Toast.makeText(getApplicationContext(), "hhh",
                            Toast.LENGTH_LONG).show();
                })
        );
    }

    private Completable update(final String pComment) {
        return Completable.fromAction(() -> {
            Comment comment = new Comment();
            comment.value(pComment);
            mDataBase.commentDao().save(comment);
        });
    }

    private Comment cmt;
    public Flowable<String> value() {
        return mDataBase.commentDao().findOne()
                .map(comment -> {
                    cmt = comment;
                    return cmt.value();
                });

    }
}
