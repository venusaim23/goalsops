package com.mlh.goalsops;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.view.View;

import com.mlh.goalsops.databinding.ActivityMainBinding;
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ResolutionsFragment resolutionsFragment;
    private AddResolution bottomSheet;
    private FragmentManager manager;

    private static final String ACC_SID = "ACf775c425a3a4a39298540477f0be080f";
    private static final String AUTH_TOKEN = "4af54a1485fca9efb380d35944d6da33";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        resolutionsFragment = new ResolutionsFragment();
        bottomSheet = new AddResolution();
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame_layout_main, resolutionsFragment).commit();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addResolution();
            }
        });

//        Twilio.init(ACC_SID, AUTH_TOKEN);
//        Message msg = Message.creator(new PhoneNumber("whatsapp:+918727064663"),
//                new PhoneNumber("whatsapp:+14155238886"), "Yooo you made it").create();
    }

    private void addResolution() {
        bottomSheet.show(manager, bottomSheet.getTag());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}