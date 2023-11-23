package com.unipiloto.project_appconv_final.DataBase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.unipiloto.project_appconv_final.Services.ReportsView;

public class Utilidades {


    // TABLA USUARIOS
    public static final String TABLE_USER = "users";
    public static final String ID = "id";
    public static final String FULL_NAME = "fullName";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";
    public static final String BIRTH_DATE = "birthDate";
    public static final String GENDER = "gender";

    // TABLA REPORTES
    public static final String TABLE_ACCIDENTS = "accidents";
    public static final String ACCIDENT_ID = "ID";
    public static final String ACCIDENT_ADDRESS = "address";
    public static final String ACCIDENT_DATE = "date";
    public static final String ACCIDENT_TIME = "time";
    public static final String ACCIDENT_DESCRIPTION = "description";
    public static final String ACCIDENT_STATUS = "status";
    public static final String ACCIDENT_CATEGORY = "category";

    public static final String CREAR_USUARIOS_TABLA = "CREATE TABLE " + TABLE_USER + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FULL_NAME + " TEXT,"
            + USERNAME + " TEXT,"
            + EMAIL + " TEXT,"
            + PASSWORD + " TEXT,"
            + ROLE + " TEXT,"
            + BIRTH_DATE + " TEXT,"
            + GENDER + " TEXT" + ")";

    public static final String CREATE_REPORTES_TABLE = "CREATE TABLE " + TABLE_ACCIDENTS + "("
            + ACCIDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ACCIDENT_ADDRESS + " TEXT,"
            + ACCIDENT_DATE + " TEXT,"
            + ACCIDENT_TIME + " TEXT,"
            + ACCIDENT_CATEGORY + " TEXT,"
            + ACCIDENT_DESCRIPTION + " TEXT,"
            + ACCIDENT_STATUS + " TEXT" + ")";
}
