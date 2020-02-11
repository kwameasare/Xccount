package genius.xccount;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Security extends Fragment {

    CardView print;
    ImageView fini;
    TextView pText;
    FirebaseFirestore fbdb;
    UserDB userDB;
    public static final String TAG = "YOUR-TAG-NAME";
    String first_name,middle_name,last_name,DOB,user_name,cu_sch,cu_prog,cu_yr,for_sch,yr_grp,interestz,systemid;


    public Security() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("HardwareIds")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_security, container, false);

        print=v.findViewById(R.id.print);
        fini=v.findViewById(R.id.fini);
        pText=v.findViewById(R.id.set_print);

        print.setOnClickListener(v1 -> {
            gotoSecuritySettings(Objects.requireNonNull(getActivity()));
        });

        fbdb=FirebaseFirestore.getInstance();


userDB=new UserDB(getActivity());
        SQLiteDatabase db = userDB.getReadableDatabase();

        CardView getdb=v.findViewById(R.id.getdb);

        getdb.setOnClickListener(v12 -> {




            // Define a projection that specifies which columns from the database
// you will actually use after this query.
            String[] projection = {
                    BaseColumns._ID,
                    FeedReaderContract.FeedEntry.Col1,
                    FeedReaderContract.FeedEntry.Col2,
                    FeedReaderContract.FeedEntry.Col3,
                    FeedReaderContract.FeedEntry.Col3,
                    FeedReaderContract.FeedEntry.Col4,
                    FeedReaderContract.FeedEntry.Col5,
                    FeedReaderContract.FeedEntry.Col6,
                    FeedReaderContract.FeedEntry.Col7,
                    FeedReaderContract.FeedEntry.Col8,
                    FeedReaderContract.FeedEntry.Col9,
                    FeedReaderContract.FeedEntry.Col10,
                    FeedReaderContract.FeedEntry.Col11,
            };

            // Filter results WHERE "title" = 'My Title'
            String selection = FeedReaderContract.FeedEntry.Col1 + " = ?";
            String[] selectionArgs = { Personal.nckname };

            // How you want the results sorted in the resulting Cursor
            String sortOrder =
                    FeedReaderContract.FeedEntry._ID + " DESC";

            Cursor cursor = db.query(
                    FeedReaderContract.FeedEntry.Table_Name,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    sortOrder               // The sort order
            );


            if( cursor != null && cursor.moveToFirst() ){

                first_name=cursor.getString(2);
                middle_name=cursor.getString(3);
                last_name=cursor.getString(5);
                user_name=cursor.getString(1);
                DOB=cursor.getString(6);
                cu_sch=cursor.getString(7);
                cu_prog=cursor.getString(9);
                cu_yr=cursor.getString(8);
                for_sch=cursor.getString(10);
                yr_grp=cursor.getString(11);
                interestz=cursor.getString(12);
                systemid=Settings.Secure.getString(Objects.requireNonNull(getActivity()).getContentResolver(),
                        Settings.Secure.ANDROID_ID);

               ArrayList<String> int_array = new ArrayList<>(Arrays.asList(interestz.split("\\s*,\\s*")));


              // String[] useINT=int_array.toArray(new String[0]);

               // Toast.makeText(getActivity(),systemid,Toast.LENGTH_LONG).show();




                Map<String, Object> profile = new HashMap<>();
                profile.put("First Name", first_name);
                profile.put("Middle Name", middle_name);
                profile.put("Last Name", last_name);
                profile.put("Nick Name", user_name);
                profile.put("Date of Birth", DOB);
                profile.put("Current School", cu_sch);
                profile.put("Current Program", cu_prog);
                profile.put("Current level", cu_yr);
                profile.put("Former School", for_sch);
                profile.put("Year Completed", yr_grp);
                profile.put("Interests", int_array);
                profile.put("Device ID", systemid);


                fbdb.collection("Users").document(Personal.nckname)
                        .set(profile)
                        .addOnSuccessListener(aVoid ->Toast.makeText(getActivity(),"DocumentSnapshot successfully written!",Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e
                                -> Toast.makeText(getActivity(),"Error writing document",Toast.LENGTH_SHORT).show());




















            }




        });











        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)

    private void gotoSecuritySettings(@NonNull FragmentActivity activity) {
        Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
        activity.startActivityForResult(intent, getTargetRequestCode());
    }







    public static Security newInstance() {
        Security sec = new Security();
        Bundle args = new Bundle();
        sec.setArguments(args);
        return sec;
    }


    @Override
    public void onResume() {
        super.onResume();
if(isFingerprintAvailable(Objects.requireNonNull(getActivity()).getApplicationContext())){

    Toast.makeText(getActivity(),"Print registered",Toast.LENGTH_LONG).show();
fini.setVisibility(View.VISIBLE);

    Glide.with(this).load(R.drawable.fingerprint_800x600).into(fini);
pText.setText("Your Print ID HasBeen Set Up");
}

else {

    Toast.makeText(getActivity(),"No Print found",Toast.LENGTH_LONG).show();
    fini.setVisibility(View.GONE);
    pText.setText("Let's set up your print ID");

}

    }


    public static boolean isFingerprintAvailable(Context context) {
        FingerprintManagerCompat fingerprintManager = FingerprintManagerCompat.from(context);
        return fingerprintManager.hasEnrolledFingerprints();
    }


}
