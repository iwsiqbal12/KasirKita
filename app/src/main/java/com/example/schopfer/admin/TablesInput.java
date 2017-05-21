package com.example.schopfer.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.schopfer.model.Recordset;
import com.talagasoft.schopfer.R;

/**
 * Created by andri on 03/02/2017.
 */

public class TablesInput extends AppCompatActivity {

    Recordset _item;
    String _mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);

        Button cmdDel= (Button) findViewById(R.id.cmdDelete);
        cmdDel.setVisibility(View.INVISIBLE);
        cmdDel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                DeleteRecord();
            }
        });

        Button cmdSave=(Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(SaveRecord()) {
                    finish();
                }
            }
        });
        Button cmdClose=(Button) findViewById(R.id.cmdClose);
        cmdClose.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });
        _mode="add";
    }

    private boolean SaveRecord() {
        EditText txtTableNo= (EditText) findViewById(R.id.txtTableNo);
        EditText txtDescription = (EditText) findViewById(R.id.txtDescription);
        EditText txtStatus = (EditText) findViewById(R.id.txtStatus);

        _item= new Recordset(getBaseContext());
        _item.OpenRecordset("select * from ksr_tables where table_no='" + txtTableNo.getText().toString()+ "'",
                "ksr_tables","table_no");
        if (_item.oef())  _item.addNew();
        _item.put("table_no",txtTableNo.getText().toString());
        _item.put("description",txtDescription.getText().toString());
        _item.put("status",txtStatus.getText().toString());
        int id= _item.save();
        return id>-1;
    }
    private void DeleteRecord(){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete this item?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText txtId = (EditText) findViewById(R.id.txtTableNo);
                        _item.delete(txtId.getText().toString());
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
