package idv.jasonwangdev.dp.repository.database.example;

import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Date;
import java.util.List;

import idv.jasonwangdev.dp.repository.database.example.database.DatabaseRepository;
import idv.jasonwangdev.dp.repository.database.example.database.realm.RealmConfigurationProvider;
import idv.jasonwangdev.dp.repository.database.example.database.realm.RealmRepository;
import idv.jasonwangdev.dp.repository.database.example.database.realm.user.DeleteAllUserRealmSpecification;
import idv.jasonwangdev.dp.repository.database.example.database.realm.user.ReadAllUserRealmSpecification;
import idv.jasonwangdev.dp.repository.database.example.database.sqlite.SqliteOpenHelperProvider;
import idv.jasonwangdev.dp.repository.database.example.database.sqlite.user.DeleteAllUserSqliteSpecification;
import idv.jasonwangdev.dp.repository.database.example.database.sqlite.user.ReadAllUserSqliteSpecification;
import idv.jasonwangdev.dp.repository.database.example.database.sqlite.user.UserSqliteRepository;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
        setContentView(R.layout.activity_main);

//        testRealm();
        testSqlite();
    }


    private void testRealm() {
        RealmConfiguration realmConfiguration = RealmConfigurationProvider.getDefault();
        DatabaseRepository repository = new RealmRepository(realmConfiguration);

        Log.d(TAG, "---------- Read User ----------");
        List<User> users = repository.read(new ReadAllUserRealmSpecification());
        Log.d(TAG, "Size = " + users.size());

        for(int i = 0 ; i < 10 ; i++) {
            User user = new User();
            user.setId(i);
            user.setAddress("Address " + i);
            user.setName("Name " + i);
            user.setCreateDate(new Date());

            users.add(user);
        }

        Log.d(TAG, "---------- Create User ----------");
        repository.create(users);

        Log.d(TAG, "---------- Read User ----------");
        users = repository.read(new ReadAllUserRealmSpecification());
        Log.d(TAG, "Size = " + users.size());

        Log.d(TAG, "---------- Show User ----------");
        for(User user : users)
            Log.d(TAG, user.toString());

        Log.d(TAG, "---------- Update User ----------");
        User u = users.get(0);
        u.setName("Update Name 0");
        repository.update(u);

        Log.d(TAG, "---------- Read User ----------");
        users = repository.read(new ReadAllUserRealmSpecification());
        Log.d(TAG, "Size = " + users.size());

        Log.d(TAG, "---------- Show User ----------");
        for(User user : users)
            Log.d(TAG, user.toString());

        Log.d(TAG, "---------- Delete User ----------");
        repository.delete(new DeleteAllUserRealmSpecification());

        Log.d(TAG, "---------- Read User ----------");
        users = repository.read(new ReadAllUserRealmSpecification());
        Log.d(TAG, "Size = " + users.size());
    }

    private void testSqlite() {
        SQLiteOpenHelper helper = SqliteOpenHelperProvider.getDefault(this);
        DatabaseRepository repository = new UserSqliteRepository(helper);

        Log.d(TAG, "---------- Read User ----------");
        List<User> users = repository.read(new ReadAllUserSqliteSpecification());
        Log.d(TAG, "Size = " + users.size());

        for(int i = 0 ; i < 10 ; i++) {
            User user = new User();
            user.setId(i);
            user.setAddress("Address " + i);
            user.setName("Name " + i);
            user.setCreateDate(new Date());

            users.add(user);
        }

        Log.d(TAG, "---------- Create User ----------");
        repository.create(users);

        Log.d(TAG, "---------- Read User ----------");
        users = repository.read(new ReadAllUserSqliteSpecification());
        Log.d(TAG, "Size = " + users.size());

        Log.d(TAG, "---------- Show User ----------");
        for(User user : users)
            Log.d(TAG, user.toString());

        Log.d(TAG, "---------- Update User ----------");
        User u = users.get(0);
        u.setName("Update Name 0");
        repository.update(u);

        Log.d(TAG, "---------- Read User ----------");
        users = repository.read(new ReadAllUserSqliteSpecification());
        Log.d(TAG, "Size = " + users.size());

        Log.d(TAG, "---------- Show User ----------");
        for(User user : users)
            Log.d(TAG, user.toString());

        Log.d(TAG, "---------- Delete User ----------");
        repository.delete(new DeleteAllUserSqliteSpecification());

        Log.d(TAG, "---------- Read User ----------");
        users = repository.read(new ReadAllUserSqliteSpecification());
        Log.d(TAG, "Size = " + users.size());
    }

}
