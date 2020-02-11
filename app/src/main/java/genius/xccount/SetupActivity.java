package genius.xccount;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SetupActivity extends FragmentActivity {
    UserDB userDB;

    static ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        vp = findViewById(R.id.pager);

        FragmentPagerAdapter adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(adapterViewPager);

        userDB=new UserDB(this);


    }










    public static class MyPagerAdapter extends FragmentPagerAdapter {

        MyPagerAdapter(FragmentManager fm) {
            super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {

            switch(position){
                case 1:
                    return Personal.newInstance();
                case 2:
                    return School.newInstance();
                case 3:
                    return Interests.newInstance();

                case 4:
                    return Security.newInstance();

                case 0:

                default:
                    return Welcome.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 5    ;
        }
    }




}
