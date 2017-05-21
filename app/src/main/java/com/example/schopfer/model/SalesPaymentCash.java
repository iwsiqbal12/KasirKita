package com.example.schopfer.model;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.talagasoft.schopfer.R;

import java.text.DecimalFormat;
import java.util.Date;


public class SalesPaymentCash extends AppCompatActivity{
    TextView txtAmountPaid,txtAmount,txtKembali,txtQty;
    private final int CASH_PAYMENT=6;
    int mNota=0,mTotalQty=0;
    float mTotalAmount=0;
    String by,hy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_cash);
        txtAmountPaid=(TextView)findViewById(R.id.txtAmountPaid);
        txtAmount=(TextView)findViewById(R.id.txtAmount);
        txtKembali=(TextView)findViewById(R.id.txtKembali);
        txtQty=(TextView)findViewById(R.id.txtQty);

        mNota= Integer.parseInt(getNewNota());
        com.example.schopfer.model.Recordset rst=new com.example.schopfer.model.Recordset(getBaseContext());
        rst.execute("select sum(amount) as zAmt, count(1) as zCnt " +
                "from ksr_sales_detail " +
                "where invoice_no='$$$'");
        mTotalAmount = rst.getFloat("zAmt");
        mTotalQty = rst.getInt("zCnt");
        DecimalFormat df = new DecimalFormat("###,###.##"); // or pattern "###,###.##$"
        txtAmount.setText(df.format(mTotalAmount));
        txtQty.setText("" + mTotalQty);

        Button cmdCancel=(Button)findViewById(R.id.cmdHitung);
        cmdCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 by = txtAmountPaid.getText().toString();
                int byr = Integer.parseInt(by);
                int hasil = (int) (byr - mTotalAmount );
                txtKembali.setText(Integer.toString(hasil));
            }
        });
        Button cmdSubmit=(Button)findViewById(R.id.cmdSubmit);
        cmdSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePayment();
            }
        });
    }
    private void savePayment(){
        double mAmount= Double.parseDouble(txtAmountPaid.getText().toString());
        Date currentDate = new Date(System.currentTimeMillis());
        String tgl=currentDate.toString();
        com.example.schopfer.model.Helper db=new com.example.schopfer.model.Helper(getBaseContext());
        db.Execute("insert into ksr_sales_payment(invoice_no,how_paid,date_paid,amount) " +
                "values('"+mNota+"','Cash','"+tgl+"','"+mAmount+"')");
        db.Execute("update ksr_sales_header set invoice_no='"+mNota+"',paid=1 where invoice_no='$$$'");
        db.Execute("update ksr_sales_detail set invoice_no='"+mNota+"' where invoice_no='$$$'");
        Intent intent=new Intent();
        intent.putExtra("status","OK");
        setResult(CASH_PAYMENT,intent);
        finish();
    }
    private String getNewNota(){
        com.example.schopfer.model.Recordset db=new Recordset(getBaseContext());
        db.execute("select count(1) as cnt from ksr_sales_header");
        int no = db.getInt("cnt");
        return String.valueOf(no);
    }
}
