package reading.speed.improver.db;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import androidx.room.Room;

public class DatabaseCopier {
    private static final String TAG = DatabaseCopier.class.getSimpleName();

    private Context appContext;

    public DatabaseCopier(Context appContext) {
        this.appContext = appContext;
    }

    public AppDatabase getRoomDatabase() {
        String DATABASE_NAME = "chitaloid.db";
        copyAttachedDatabase(appContext, DATABASE_NAME);
        return Room.databaseBuilder(appContext,
                AppDatabase.class, DATABASE_NAME)
                .addMigrations(AppDatabase.MIGRATION_1_2)
                .build();
    }


    private void copyAttachedDatabase(Context context, String databaseName) {
        final File dbPath = context.getDatabasePath(databaseName);

        // If the database already exists, return
        if (dbPath.exists()) {
              dbPath.delete();
            //          return;
        }

        // Make sure we have a path to the file
        dbPath.getParentFile().mkdirs();

        // Try to copy database file
        try {
            final InputStream inputStream = context.getAssets().open(databaseName);
            final OutputStream output = new FileOutputStream(dbPath);

            byte[] buffer = new byte[8192];
            int length;

            while ((length = inputStream.read(buffer, 0, 8192)) > 0) {
                output.write(buffer, 0, length);
            }

            output.flush();
            output.close();
            inputStream.close();
        } catch (IOException e) {
            Log.d(TAG, "Failed to open file", e);
            e.printStackTrace();
        }
    }

}