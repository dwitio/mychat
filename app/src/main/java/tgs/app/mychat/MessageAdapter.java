package tgs.app.mychat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private Context context;
    private List<MessageModel> messageModelList;

    public MessageAdapter(Context context) {
        this.context = context;
        messageModelList = new ArrayList<>();
    }

    public void add(MessageModel userModel){
        messageModelList.add(userModel);
        notifyDataSetChanged();
    }

    public void clear(){
        messageModelList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MyViewHolder holder, int position) {
        MessageModel messageModel = messageModelList.get(position);
        if (messageModel.getSenderId().equals(FirebaseAuth.getInstance().getUid())){
            holder.msgReceiver.setText(messageModel.getMessage());
            holder.msgSender.setVisibility(View.INVISIBLE);
        } else {
            holder.msgSender.setText(messageModel.getMessage());
            holder.msgReceiver.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return messageModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView msgSender, msgReceiver;
        private LinearLayout main;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            msgSender = itemView.findViewById(R.id.messageSender);
            msgReceiver = itemView.findViewById(R.id.messageReceiver);
            main = itemView.findViewById(R.id.mainMessageLayout);
        }
    }
}
