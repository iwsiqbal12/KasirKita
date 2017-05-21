package com.example.schopfer.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import static com.example.schopfer.model.Global.NAMA_DB;
import static com.example.schopfer.model.Global.VERSI_DB;

/**
 * Created by andri on 03/02/2017.
 */

public class Recordset {

    private Context _context;
    private SQLiteDatabase _db = null;
    private Cursor _cursor;
    private String[] _fields;
    private boolean _add=false;
    private ContentValues _data;
    private String _table, _fieldKey;
    public Recordset(Context c) {
        _context=c;
        com.example.schopfer.model.Helper dbHelper = new com.example.schopfer.model.Helper(c, NAMA_DB, null, VERSI_DB);
        try {
            _db = dbHelper.getWritableDatabase();
        }
        catch (SQLiteException e) {
            _db = dbHelper.getReadableDatabase();
        }

    }

    public boolean OpenRecordset(String sql,String table,String fieldKey) {
        _table = table;
        _fieldKey=fieldKey;
        _cursor = _db.rawQuery(sql,null);
        _fields=_cursor.getColumnNames();
        if(_fields == null) return false;
        if(_cursor.getCount()>0) {
            _cursor.moveToFirst();
            _data = new ContentValues();
            for(int i=0;i<_fields.length;i++){
                _data.put(_fields[i],_cursor.getString(i));
            }
        }
        return true;
    }
    public void moveNext(){
        _cursor.moveToNext();
        _data = new ContentValues();
        for(int i=0;i<_fields.length;i++){
            _data.put(_fields[i],_cursor.getString(i));
        }
    }
    public boolean OpenTable(String vTable,String[] vField,String vSelection){
        _cursor=_db.query(false,vTable,vField,vSelection,null,null,null,null,null);
        return true;
    }

    public boolean oef() {
        return _cursor.isAfterLast();
    }
    public boolean bof(){
        return _cursor.isBeforeFirst();
    }

    public void addNew() {
        _add = true;
        _data = new ContentValues();
    }

    public void put(String fieldName, String fieldValue) {
        _data.put(fieldName,fieldValue);
    }

    public int save() {
        try {
            if(_add){
                return (int)_db.insert(_table, null, _data);
            } else {
                String where="";
                where=_fieldKey + "='" + _data.get(_fieldKey) + "'";
                return _db.update(_table, _data, where, null);
            }
        }
        catch (SQLiteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void delete(String value) {
         String where=_fieldKey + "='" + _data.get(_fieldKey) + "'";
        _db.delete(_table, where, null);

    }

    public void execute(String sql) {
        _cursor = _db.rawQuery(sql,null);
        _fields=_cursor.getColumnNames();
        if(_cursor.getCount()>0) {
            _cursor.moveToFirst();
            _data = new ContentValues();
            for(int i=0;i<_fields.length;i++){
                _data.put(_fields[i],_cursor.getString(i));
            }
        }
    }
    public String getString(String fieldName){
        String s =  _data.getAsString(fieldName);
        if(s==null)s="";
        return s;
    }
    public int getInt(String fieldName){
        if(_data==null) return 0;
        String s=_data.getAsString(fieldName);
        if(s==null)s="0";
        if(s=="")s="0";
        return Integer.parseInt(s);
    }
    public float getFloat(String fieldName){
        if(_data==null) return 0;
        String s=_data.getAsString(fieldName);
        if(s==null)s="0";
        if(s=="")s="0";
        return Float.parseFloat(s);
    }

}
