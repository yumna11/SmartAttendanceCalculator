package folio.jaagdeveloper.usman.a75criteria;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Usman on 4/17/2017.
 */
public class CustomAdapter extends FragmentPagerAdapter {
    Context mContext;

        public CustomAdapter(Context context,FragmentManager fm) {
            super(fm);
            mContext = context;
        }

        @Override
        public CharSequence getPageTitle(int position) {
           if (position == 0) {
               return "Criteria 1";
           }
            else
               return "Criteria 2";

        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return  new Criteria1();
            }
            else
                return new Criteria2();
        }
    }
