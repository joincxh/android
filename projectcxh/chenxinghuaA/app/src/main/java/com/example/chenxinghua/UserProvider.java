package com.example.chenxinghua;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UserProvider extends ContentProvider {
    private static UriMatcher uriMatcher=new UriMatcher(-1);
    private static final int SUCCESS=1;
    private static final String path="com.example.chenxinghua";
    private static final String table="info";
    private UserDBOpenHelper helper;
    static {
        uriMatcher.addURI(path,table,SUCCESS);
    }
    @Override
    public boolean onCreate() {
        helper =new UserDBOpenHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int code=uriMatcher.match(uri);
        if (code==SUCCESS){
            SQLiteDatabase db=helper.getReadableDatabase();
            return db.query("info",projection,selection,selectionArgs,null,null,sortOrder);
        }else {
            throw new IllegalArgumentException("path is error,data can't search.");//路径错误
        }
/*        return null;*/
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int code=uriMatcher.match(uri);
        if (code==SUCCESS){
            SQLiteDatabase db =helper.getReadableDatabase();
            long rowID=db.insert("info",null,values);
            if (rowID>0){
                Uri insetUri= ContentUris.withAppendedId(uri,rowID);
                getContext().getContentResolver().notifyChange(insetUri,null);
                return insetUri;
            }
            db.close();
            return uri;
        }else {
            throw new IllegalArgumentException("path is error,data can't insert.");
        }
 /*       return null;*/
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int code=uriMatcher.match(uri);
        if (code==SUCCESS){
            SQLiteDatabase db =helper.getWritableDatabase();
            int count=db.delete("info",selection,selectionArgs);
            if (count>0){
                getContext().getContentResolver().notifyChange(uri,null);
            }
            db.close();
            return count;

        }else {
            throw new IllegalArgumentException("path is error,data can't delete.");
        }
/*        return 0;*/
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int code=uriMatcher.match(uri);
        if (code==SUCCESS){
            SQLiteDatabase db=helper.getWritableDatabase();
            int count=db.update("info",values,selection,selectionArgs);
            if (count>0){
                getContext().getContentResolver().notifyChange(uri,null);
            }
            db.close();
            return count;
        }else {
            throw new IllegalArgumentException("path is error,data can't update");
        }
/*        return 0;*/
    }
}
