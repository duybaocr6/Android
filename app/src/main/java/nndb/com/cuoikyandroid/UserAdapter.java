package nndb.com.cuoikyandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> implements Filterable {
    private List<User> mListUsers;
    private List<User> mListUsersOld;

    public UserAdapter(List<User> mListUsers) {
        this.mListUsers = mListUsers;
        this.mListUsersOld =mListUsers;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mListUsers.get(position);
        if (user == null){
            return;
        }
        holder.imgUser.setImageResource(user.getImage());
        holder.tvName.setText(user.getName());
        holder.tvAddress.setText(user.getAddress());
    }

    @Override
    public int getItemCount() {
        if (mListUsers != null){
            return  mListUsers.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    mListUsers=mListUsersOld;
                }else {
                    List<User> list = new ArrayList<>();
                    for (User user : mListUsersOld){
                        if(user.getName().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(user);
                        }
                    }
                    mListUsers = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mListUsers;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mListUsers = (List<User>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class  UserViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView imgUser;
        private TextView tvName;
        private TextView tvAddress;
        public UserViewHolder(@Nullable View itemView){
            super(itemView);
            imgUser = itemView.findViewById(R.id.img_user);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
        }
    }
}

