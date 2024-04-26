package com.example.thithu3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Themdv extends AppCompatActivity {

    private EditText edtma;
    private EditText edtten;
    private EditText edtcannang;
    private EditText edtsoluong;
    private Button btnthem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themdv);
        getView();
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Themdv.this,Dongvat.class);

                String ma = edtma.getText().toString();
                String ten = edtten.getText().toString();
                String cannang = edtcannang.getText().toString();
                String soluong = edtsoluong.getText().toString();

                if(ma.isEmpty()||ten.isEmpty()||cannang.isEmpty()||soluong.isEmpty()){
                    Toast.makeText(Themdv.this, "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                } else if (!isso(soluong)) {
                    Toast.makeText(Themdv.this, "Số lượng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                }
                else{
                    double cn = Double.parseDouble(cannang);
                    int sl = Integer.parseInt(soluong);
                    Bundle bundle = new Bundle();
                    bundle.putString("ma",ma);
                    bundle.putString("ten",ten);
                    bundle.putDouble("cnang",cn);
                    bundle.putInt("soluong",sl);
                    intent.putExtras(bundle);
                    setResult(1,intent);
                    finish();
                    Toast.makeText(Themdv.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void getView(){
        edtma = findViewById(R.id.edtma);
        edtten = findViewById(R.id.edtten);
        edtcannang = findViewById(R.id.edtcannang);
        edtsoluong = findViewById(R.id.edtsoluong);
        btnthem = findViewById(R.id.btnthem);
    }
    private Boolean isso(String so){
        return  so.matches("[1-9](\\d+)?");
    }
}