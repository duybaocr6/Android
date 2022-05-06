package nndb.com.cuoikyandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<Customer> list = new ArrayList<>();
    public CustomerAdapter(Context ctx)
    {
        this.context=ctx;
    }
    public void setItem(ArrayList<Customer> cus)
    {
        list.addAll(cus);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user,parent,false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CustomerViewHolder cuvh = (CustomerViewHolder) holder;
        Customer cus = list.get(position);
        cuvh.name.setText(cus.getName());
        cuvh.position.setText(cus.getPosition());
        cuvh.option.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(context,cuvh.option);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId())
                {
                    case R.id.menu_edit:
                        Intent intent = new Intent(context,MainActivity5.class);
                        intent.putExtra("Edit", cus);
                        context.startActivity(intent);
                        break;
                    case R.id.menu_remove:
                        DAOCustomer dao = new DAOCustomer();
                        dao.remove(cus.getKey()).addOnSuccessListener(suc->{
                            Toast.makeText(context,"Remove",Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                        }).addOnFailureListener(er->{
                            Toast.makeText(context,""+er.getMessage(),Toast.LENGTH_SHORT).show();
                        });
                        break;
                }
                return false;
            });
            popupMenu.show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
