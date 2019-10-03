package com.examples.joshuayingwhat.fastec;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.examples.joshuayingwhat.latte.app.Latte;
import com.examples.joshuayingwhat.latte.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * @author joshuayingwhat
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Latte.init(this).withApiHost("www.baidu.com").
                withIcon(new FontEcModule())
                .withIcon(new FontAwesomeModule()).configure();

        Toast.makeText(Latte.getContext(), "传入context了", Toast.LENGTH_SHORT).show();
    }
}
