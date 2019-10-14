package genius.xccount;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;


public class School extends Fragment {

String CuSch,CuYr,CuProg,ForSch,ForYr;
    UserDB userDB;
    SQLiteDatabase db;


    public School() {
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
        View v=inflater.inflate(R.layout.fragment_school, container, false);


        ArrayList<String> years = new ArrayList<>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 2008; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_spinner_item, years);

        Spinner spinYear = v.findViewById(R.id.completion_year_spin);
        spinYear.setAdapter(adapter);

        userDB=new UserDB(getActivity());
        db=userDB.getWritableDatabase();

        Spinner schspin= v.findViewById(R.id.sch_spin);
        Spinner yearspin= v.findViewById(R.id.year_spin);
        Spinner progspin= v.findViewById(R.id.programme_spin);
        Spinner former=v.findViewById(R.id.former_sch_spin);

        ArrayAdapter<CharSequence> schdapter = ArrayAdapter
                .createFromResource(Objects.requireNonNull(getActivity()), R.array.School_array,
                        android.R.layout.simple_spinner_item);





        ArrayAdapter<CharSequence> yeardapter = ArrayAdapter
                .createFromResource(Objects.requireNonNull(getActivity()), R.array.Year_Group,
                        android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> progdapter = ArrayAdapter
                .createFromResource(Objects.requireNonNull(getActivity()), R.array.Programme,
                        android.R.layout.simple_spinner_item);



        schdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        yeardapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        progdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        former.setAdapter(schdapter);


        CardView snext=v.findViewById(R.id.snext);


        snext.setOnClickListener(v1 -> {

            CuSch=schspin.getSelectedItem().toString();
            CuYr=yearspin.getSelectedItem().toString();
            CuProg=progspin.getSelectedItem().toString();
            ForSch=former.getSelectedItem().toString();
            ForYr=spinYear.getSelectedItem().toString();





            ContentValues values = new ContentValues();
            values.put(FeedReaderContract.FeedEntry.Col6, CuSch);
            values.put(FeedReaderContract.FeedEntry.Col7, CuYr);
            values.put(FeedReaderContract.FeedEntry.Col8, CuProg);
            values.put(FeedReaderContract.FeedEntry.Col9, ForSch);
            values.put(FeedReaderContract.FeedEntry.Col10, ForYr);



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


            SetupActivity.vp.setCurrentItem(3);


        });



schspin.setAdapter(schdapter);
yearspin.setAdapter(yeardapter);
progspin.setAdapter(progdapter);

        return v;
    }



    public static School newInstance() {
        School sch = new School();
        Bundle args = new Bundle();
        sch.setArguments(args);
        return sch;
    }



}
