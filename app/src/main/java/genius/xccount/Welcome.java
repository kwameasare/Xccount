package genius.xccount;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Welcome extends Fragment {



    public Welcome() {
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
        View v= inflater.inflate(R.layout.fragment_welcome, container, false);


        TextView welc= v.findViewById(R.id.welc);

        welc.setOnClickListener(v1 -> {

            SetupActivity.vp.setCurrentItem(1);

        });


        return v;
    }




    public static Welcome newInstance() {
        Welcome welc = new Welcome();
        Bundle args = new Bundle();
        welc.setArguments(args);
        return welc;
    }





}
