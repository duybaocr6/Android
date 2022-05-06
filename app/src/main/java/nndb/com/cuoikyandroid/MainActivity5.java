package nndb.com.cuoikyandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity5 extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        final EditText name = findViewById(R.id.editname);
        final EditText position = findViewById(R.id.editsubject);
        Button btn = findViewById(R.id.submit);
        Button btn_open = findViewById(R.id.open);
        btn_open.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity5.this,MainActivity1.class);
            startActivity(intent);
        });
        DAOCustomer dao = new DAOCustomer();
        Customer cus_edit = (Customer) getIntent().getSerializableExtra("Edit");
        if (cus_edit !=null)
        {
            btn.setText("Edit");
            name.setText(cus_edit.getName());
            position.setText(cus_edit.getPosition());
            btn_open.setVisibility(View.GONE);
        }
        else
        {
            btn.setText("Submit");
            btn_open.setVisibility(View.VISIBLE);
        }
        btn.setOnClickListener(v -> {
            Customer cus = new Customer(name.getText().toString(), position.getText().toString());
            if (cus_edit==null) {
                dao.add(cus).addOnSuccessListener(suc -> {
                    Toast.makeText(this, "Insert", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er -> {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
            else
            {
                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("name",name.getText().toString());
                hashMap.put("position",position.getText().toString());
                dao.update(cus_edit.getKey(),hashMap).addOnSuccessListener(suc ->{
                    Toast.makeText(this,"Edit",Toast.LENGTH_SHORT).show();
                    finish();
                }).addOnFailureListener(er ->{
                    Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show();
                });
            }
//            HashMap<String,Object> hashMap = new HashMap<>();
//            hashMap.put("name",name.getText().toString());
//            hashMap.put("subject",subject.getText().toString());
    //            dao.update("",hashMap).addOnSuccessListener(suc ->{
//                Toast.makeText(this,"Insert",Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(er ->{
//                Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show();
//            });
//            dao.remove("-N18OcCdVWU1W-u1211z").addOnSuccessListener(suc ->{
//                Toast.makeText(this,"Insert",Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(er ->{
//                Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show();
//            });
        });

    }
}
