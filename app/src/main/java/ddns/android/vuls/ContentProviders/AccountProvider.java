package ddns.android.vuls.ContentProviders;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class AccountProvider extends ContentProvider {

    private static final int TYPE_ALL = 1;
    private static final int TYPE_SINGLE = 2;
    private static final int TYPE_QYERY = 3;
    private DBHelper dbHelper;
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        matcher.addURI("ddns.vuls.AccountProvider", "/account", TYPE_ALL);
        matcher.addURI("ddns.vuls.AccountProvider", "/account/#", TYPE_SINGLE);
        matcher.addURI("ddns.vuls.AccountProvider", "/account/*", TYPE_QYERY);
    }

    public AccountProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        int deleteCount = -1;

        switch (matcher.match(uri)) {
            case TYPE_ALL:
                deleteCount = database.delete("account", selection, selectionArgs);
                break;
            case TYPE_SINGLE:
                long id = ContentUris.parseId(uri);
                deleteCount = database.delete("account", "_id=" + id, null);
                break;
            default:
                break;
        }

        database.close();
        return deleteCount;
    }

    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Uri result = null;

        switch (matcher.match(uri)){
            case TYPE_ALL:
                long id = database.insert("account", null, values);
                result = ContentUris.withAppendedId(uri, id);
                database.close();
                break;
            default:
                database.close();
                break;
        }

        return result;

    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        try {
            FileOutputStream fileOutputStream = getContext().openFileOutput("token.txt", Context.MODE_PRIVATE);
            fileOutputStream.write("token: THISISIMPORTENT".getBytes("utf-8"));
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor result = null;

        switch (matcher.match(uri)){
            case TYPE_ALL:
                String name = uri.getQueryParameter("name");
                if (null != name){
                    result = database.query("account", projection, "name='" + name + "'" , null, null, null, null);
                }else {
                    result = database.query("account", projection, selection, selectionArgs, null, null, null);
                }
                break;
            case TYPE_SINGLE:
                long id = ContentUris.parseId(uri);
                result = database.query("account", projection, "_id=?", new String[]{id + ""}, null, null, null);
                break;
            default:
                break;
        }

        return result;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        int updateCount = -1;

        switch (matcher.match(uri)){
            case TYPE_ALL:
                updateCount = database.update("account", values, selection, selectionArgs);
                break;
            case TYPE_SINGLE:
                long id = ContentUris.parseId(uri);
                updateCount = database.update("account", values, "_id=" + id, null);
                break;
            default:
                break;
        }

        database.close();
        return updateCount;
    }

    @Nullable
    @Override
    public ParcelFileDescriptor openFile(@NonNull Uri uri, @NonNull String mode) throws FileNotFoundException {
        String file = uri.getPath();
        File filesDir = getContext().getFilesDir();
        file = filesDir + file;

        if ("w".equals(mode)){
            return ParcelFileDescriptor.open(
                    new File(file),
                    ParcelFileDescriptor.MODE_WRITE_ONLY);
        }else{
            return ParcelFileDescriptor.open(
                    new File(file),
                    ParcelFileDescriptor.MODE_READ_ONLY);
        }

    }
}
