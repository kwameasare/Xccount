package genius.xccount;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class chosenadapter extends RecyclerView.Adapter <chosenadapter.Holder>{


private Context mContext;
static ArrayList<String> cDataSet=new ArrayList<>();



        chosenadapter(Context mContext) {
        this.mContext = mContext;

        //cDataSet = myDataset;
        }


static class Holder extends RecyclerView.ViewHolder{
    CardView mCardView;

    TextView mTextViewLabel;
    //TextView mTextViewPackage;
    //ImageView mImageViewIcon;

    Holder(View v){
        super(v);

        mCardView = v.findViewById(R.id.int_card);
        mTextViewLabel = v.findViewById(R.id.int_text);
        //mImageViewIcon = v.findViewById(R.id.iv_icon);
    }
}


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.int_norm,viewGroup,false);
        return new Holder(v);
    }


    public void onBindViewHolder(@NonNull final Holder holder, int i) {

        String[] item = cDataSet.toArray(new String[0]);

        holder.mTextViewLabel.setText(item[i]);




    }




    @Override
    public int getItemCount() {
        return cDataSet.size();
    }
}