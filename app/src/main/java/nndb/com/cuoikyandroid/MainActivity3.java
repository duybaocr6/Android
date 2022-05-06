package nndb.com.cuoikyandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    private RecyclerView rcvUser;
    private UserAdapter userAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        rcvUser=findViewById(R.id.rcy_user);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvUser.setLayoutManager(linearLayoutManager);

        userAdapter = new UserAdapter(getListUsers());
        rcvUser.setAdapter(userAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);//dòng kẻ giữa các dòng
        rcvUser.addItemDecoration(itemDecoration);

    }

    public List<User> getListUsers() {         //Gán dữ liệu vào bên trong List
        List<User> list = new ArrayList<>();
        list.add(new User(R.drawable.banner1,"AUDI","Đức"));
        list.add(new User(R.drawable.banner2,"HINO","Nhật Bản"));
        list.add(new User(R.drawable.banner3,"HYUNDAI","Hàn Quốc"));
        list.add(new User(R.drawable.banner4,"ISUZU","Nhật Bản"));
        list.add(new User(R.drawable.banner1,"HONDA","Nhật Bản"));
        list.add(new User(R.drawable.banner2,"MERCEDES","Đức"));
        list.add(new User(R.drawable.banner3,"VOLVO","Thụy Điển"));
        list.add(new User(R.drawable.banner4,"VINFAST","Việt Nam "));
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                userAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                userAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}