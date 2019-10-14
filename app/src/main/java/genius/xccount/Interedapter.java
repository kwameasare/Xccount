package genius.xccount;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static genius.xccount.Interests.cRecyclerView;

public class Interedapter extends RecyclerView.Adapter <Interedapter.ViewHolder>{


    private Context mContext;
    private String[] mDataSet;
    static ArrayList<String> selected;


    Interedapter(Context mContext, String[] myDataset) {
        this.mContext = mContext;

        mDataSet = myDataset;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView mCardView;

        TextView mTextViewLabel;
        //TextView mTextViewPackage;
        //ImageView mImageViewIcon;

        ViewHolder(View v){
            super(v);

            mCardView = v.findViewById(R.id.int_card);
            mTextViewLabel = v.findViewById(R.id.int_text);
            //mImageViewIcon = v.findViewById(R.id.iv_icon);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.int_norm,viewGroup,false);
        return new ViewHolder(v);
    }


    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {

        holder.mTextViewLabel.setText(mDataSet[i]);

        holder.mCardView.setOnClickListener(v -> {
            RecyclerView.Adapter cAdapter=new chosenadapter(mContext);
            String tooo=mDataSet[i];
          //  selected= new ArrayList<>();
         //  selected.add(mDataSet[i]);
            chosenadapter.cDataSet.add(tooo);
           cAdapter.notifyDataSetChanged();
           String listString = TextUtils.join(", ", chosenadapter.cDataSet);

           if(chosenadapter.cDataSet.size()>4){

               Interests.cont.setVisibility(View.VISIBLE);
           }


            Toast.makeText(mContext,listString,Toast.LENGTH_SHORT).show();



           RecyclerView.LayoutManager cLayoutManager = new GridLayoutManager(mContext,8);
           cRecyclerView.setLayoutManager(cLayoutManager);

           cRecyclerView.setAdapter(cAdapter);

        });

    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
