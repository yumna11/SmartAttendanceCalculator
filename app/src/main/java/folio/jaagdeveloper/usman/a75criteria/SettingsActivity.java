package folio.jaagdeveloper.usman.a75criteria;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AdView adView = (AdView) findViewById(R.id.ads);
        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.setAdListener(new ToastListener(this));
        adView.loadAd(adRequest);

    }
    public static class CalculatorPreference extends PreferenceFragment implements Preference.OnPreferenceChangeListener{
        @Override
        public boolean onPreferenceChange(Preference preference, Object o) {
            String value = o.toString();
            preference.setSummary(value);
            return true;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.preference_layout);
            Preference preference = findPreference(getString(R.string.weeks_key));
            bindPreferenceSummaryToFragment(preference);

        }
        private void bindPreferenceSummaryToFragment(Preference preference)
        {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            String value = sharedPreferences.getString(getString(R.string.weeks_key),getString(R.string.weeks_value));
            onPreferenceChange(preference,value);
        }
    }
}
