package nndb.com.cuoikyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    MaterialButton btndangnhap, btnhuy;
    TextView eduser,edpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        Dangnhap();
    }
    private void AnhXa() {
        btnhuy = (MaterialButton) findViewById(R.id.thoatbtn);
        btndangnhap = (MaterialButton) findViewById(R.id.dangnhapbtn);
        eduser = (TextView) findViewById(R.id.username);
        edpassword = (TextView) findViewById(R.id.password);
    }
    private void Dangnhap()
    {
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eduser.getText().toString().equals("admin") && edpassword.getText().toString().equals("admin"))
                {
                    Toast.makeText(MainActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Đăng nhập không thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}