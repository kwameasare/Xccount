package genius.xccount;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;


public class Personal extends Fragment {
    private final Calendar myCalendar = Calendar.getInstance();

    private TextView DoB;
    private EditText fname;
    private EditText mname;
    private EditText lname;
    private EditText nickname;
    private String fstname;
    private String mdname;
    private String lstname;
   static String nckname;
    private String dob;
    private SQLiteDatabase db;
    private Boolean personalisfilled;

    public Personal() {
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
        View v= inflater.inflate(R.layout.fragment_personal, container, false);

CardView pnext=v.findViewById(R.id.pnext);
         DoB = v.findViewById(R.id.DoB);
         fname=v.findViewById(R.id.firstname);
         mname=v.findViewById(R.id.midname);
         lname=v.findViewById(R.id.lastname);
         nickname=v.findViewById(R.id.nickname);
        UserDB userDB = new UserDB(getActivity());
         db= userDB.getWritableDatabase();


        DoB.setOnClickListener(v1 -> new DatePickerDialog(Objects.requireNonNull(getActivity()),R.style.MySpinnerDatePickerStyle, date, myCalendar
               .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
               myCalendar.get(Calendar.DAY_OF_MONTH)).show());




        pnext.setOnClickListener(v12 -> {

            fstname=fname.getText().toString();
            mdname=mname.getText().toString();
            lstname=lname.getText().toString();
            nckname=nickname.getText().toString();


            ContentValues values = new ContentValues();
            values.put("User_Name", nckname);
            values.put("First_Name", fstname);
            values.put("Middle_Name", mdname);
            values.put("Last_Name", lstname);
            values.put("DOB", dob);
            personalisfilled=true;


            long rowInserted =             db.insert("User", "Prog", values);

            if(rowInserted != -1)
                Toast.makeText(getActivity(), "New row added, row id: " + rowInserted, Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getActivity(), "Something wrong", Toast.LENGTH_SHORT).show();


            SetupActivity.vp.setCurrentItem(2);

        });




        return v;


    }

    private DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
        // TODO Auto-generated method stub
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabel();
    };


    public static Personal newInstance() {

        Personal pers = new Personal();
        Bundle args = new Bundle();
        pers.setArguments(args);
        return pers;
    }


    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);
        dob=sdf.format(myCalendar.getTime());
        DoB.setText(dob);
    }



}
