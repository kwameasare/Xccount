package genius.xccount;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch(position){

                case 0:
                    return Welcome.newInstance();
                case 1:
                    return Personal.newInstance();
                case 2:
                    return School.newInstance();
                case 3:
                    return Interests.newInstance();

                case 4:
                    return Security.newInstance();

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
