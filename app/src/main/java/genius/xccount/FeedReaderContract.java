package genius.xccount;

import android.provider.BaseColumns;

final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}


    static class FeedEntry implements BaseColumns {
        static final String DBname="User.db";
        static final String Table_Name="User";
        static final String Col1="User_Name";
        static final String Col2="First_Name";
        static final String Col3="Middle_Name";
        static final String Col4="Last_Name";
        static final String Col5="DOB";
        static final String Col6="Cu_School";
        static final String Col7="Level";
        static final String Col8="Prog";
        static final String Col9="FSchool";
        static final String Col10="YearGroup";
        static final String Col11="Interests";
        static final String Col12="Badges";
    }
}

