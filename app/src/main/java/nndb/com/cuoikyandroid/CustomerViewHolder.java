package nndb.com.cuoikyandroid;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerViewHolder extends RecyclerView.ViewHolder {
    public TextView name, position, option;
    public CustomerViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tv_name);
        position = itemView.findViewById(R.id.tv_address);
        option = itemView.findViewById(R.id.option);
    }
}
