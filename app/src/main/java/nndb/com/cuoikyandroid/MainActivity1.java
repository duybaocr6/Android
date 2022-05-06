package nndb.com.cuoikyandroid;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity1 extends AppCompatActivity {
    private SwipeRefreshLayout swip;
    private RecyclerView rcv;
    private SearchView searchView;
    CustomerAdapter adapter;
    DAOCustomer dao;
    boolean isLoading = false;
    String key = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        swip= findViewById(R.id.swip);
        rcv=findViewById(R.id.rcy_user);
        rcv.setHasFixedSize(true);
        adapter = new CustomerAdapter(this);
        rcv.setAdapter(adapter);
        dao = new DAOCustomer();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(linearLayoutManager);
        loadData();
        rcv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int total = linearLayoutManager.getItemCount();
                int lastVisible = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if(total < lastVisible+3)
                {
                    if(!isLoading)
                    {
                        isLoading = true;
                        loadData();
                    }
                }
            }
        });


        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);//dòng kẻ giữa các dòng
        rcv.addItemDecoration(itemDecoration);

    }

    private void loadData() {
        swip.setRefreshing(true);
        dao.get(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Customer> cuss = new ArrayList<>();
                for(DataSnapshot data : snapshot.getChildren())
                {
                    Customer cus = data.getValue(Customer.class);
                    cus.setKey(data.getKey());
                    cuss.add(cus);
                    key = data.getKey();
                }
                adapter.setItem(cuss);
                adapter.notifyDataSetChanged();
                isLoading =false;
                swip.setRefreshing(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                swip.setRefreshing(false);
            }
        });
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setMaxWidth(Integer.MAX_VALUE);

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                userAdapter.getFilter().filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                userAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//        return true;
//    }
}