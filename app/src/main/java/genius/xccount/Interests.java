package genius.xccount;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class Interests extends Fragment {
    //

    UserDB userDB;
    SQLiteDatabase db;


  @SuppressLint("StaticFieldLeak")
  static   RecyclerView cRecyclerView;
  @SuppressLint("StaticFieldLeak")
  static CardView cont;


String[] interests;

    public Interests() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_interests, container, false);

        RecyclerView mRecyclerView = v.findViewById(R.id.int_rec);
        cRecyclerView = v.findViewById(R.id.chosen_int);


        // Define a layout for RecyclerView
        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(7,StaggeredGridLayoutManager.VERTICAL);

        userDB=new UserDB(getActivity());
        db=userDB.getWritableDatabase();

        mRecyclerView.setLayoutManager(mLayoutManager);



        interests=getResources().getStringArray(R.array.interests);


        // Initialize a new adapter for RecyclerView
        RecyclerView.Adapter mAdapter = new Interedapter(
                getActivity(),interests

        );



        // Set the adapter for RecyclerView
        mRecyclerView.setAdapter(mAdapter);

        cont=v.findViewById(R.id.int_continue);

        cont.setOnClickListener(v1 -> {

            String whatYouLove = TextUtils.join(", ", chosenadapter.cDataSet);
            ContentValues values = new ContentValues();
            values.put(FeedReaderContract.FeedEntry.Col11, whatYouLove);

            int count = db.update(
                    "User",
                    values,
                    null,
                    null);


            if(count == 0)
                Toast.makeText(getActivity(), "Something wrong ", Toast.LENGTH_SHORT).show();
            else if(count>=1){

                Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();


            }


            SetupActivity.vp.setCurrentItem(4);


        });





return v;
    }




    public static Interests newInstance() {
        Interests interests = new Interests();
        Bundle args = new Bundle();
        interests.setArguments(args);
        return interests;
    }


}
