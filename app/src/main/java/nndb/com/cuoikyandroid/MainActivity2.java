package nndb.com.cuoikyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity{
    ListView list;

    String[] maintitle ={
            "Danh sách loại xe","Liên hệ khách hàng qua gmail",
            "Vị trí bãi giữ xe ","Khách hàng",
    };

    String[] subtitle ={
            "List of vehicle types","Message",
            "Vehicle location","Customers",
    };

    Integer[] imgid={
            R.drawable.ic_baseline_menu_book_24,R.drawable.ic_baseline_message_24,
            R.drawable.ic_baseline_location_on_24,R.drawable.ic_baseline_menu_book_24,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        MyListAdapter adapter=new MyListAdapter(this, maintitle, subtitle,imgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                if(position == 0) {
                    //code specific to first list item
                    Toast.makeText(getApplicationContext(),"Welcome to page brand vehicle",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                    startActivity(intent);
                }

                else if(position == 1) {
                    //code specific to 2nd list item
                    Toast.makeText(getApplicationContext(),"Welcome to Gmail",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity2.this,MainActivity4.class);
                    startActivity(intent);
                }

                else if(position == 2) {

                    Toast.makeText(getApplicationContext(),"Welcome to Location",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity2.this,MapsActivity.class);
                    startActivity(intent);
                }
                else if(position == 3) {

                    Toast.makeText(getApplicationContext(),"Welcome to page customers",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity2.this,MainActivity5.class);
                    startActivity(intent);
                }

            }
        });
    }
}