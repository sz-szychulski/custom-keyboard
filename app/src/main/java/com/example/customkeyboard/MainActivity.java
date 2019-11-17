package com.example.customkeyboard;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText EDIT_TEXT = findViewById(R.id.editText);
        final MyKeyboard MY_KEYBOARD = findViewById(R.id.keyboard);


        MY_KEYBOARD.findViewById(R.id.button_Text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EDIT_TEXT.setText("Example text");
                Toast toast = Toast.makeText(MainActivity.this, "Setting text", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0 , 950);
                toast.show();
            }
        });

        final MediaPlayer SOUND_MEDIA_PLAYER = MediaPlayer.create(this, R.raw.sound);
        MY_KEYBOARD.findViewById(R.id.button_Sound).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SOUND_MEDIA_PLAYER.start();
                Toast toast = Toast.makeText(MainActivity.this, "Playing sound", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0 , 950);
                toast.show();
            }
        });

        final Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        MY_KEYBOARD.findViewById(R.id.button_Camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(cameraIntent);
                Toast toast = Toast.makeText(MainActivity.this, "Opening camera", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0 , 950);
                toast.show();
            }
        });

        MY_KEYBOARD.findViewById(R.id.button_Save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSave = EDIT_TEXT.getText().toString();
                File file = new File(getExternalFilesDir(null), "keyboard.txt");
                try {
                    Toast toast = Toast.makeText(MainActivity.this, "Saving file", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 950);
                    toast.show();

                    file.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                    fileOutputStream.write(toSave.getBytes());
                    fileOutputStream.flush();
                    fileOutputStream.close();

                    Toast toast_2 = Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT);
                    toast_2.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 950);
                    toast_2.show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast toast = Toast.makeText(MainActivity.this, "Something went wrong with saving file", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 950);
                    toast.show();
                }
            }
        });

        MY_KEYBOARD.findViewById(R.id.button_Toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "This is toast message", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 950);
                toast.show();
            }
        });

        MY_KEYBOARD.findViewById(R.id.button_Expand).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MY_KEYBOARD.findViewById(R.id.button_CheckNFC).getVisibility() == View.INVISIBLE) {
                    MY_KEYBOARD.findViewById(R.id.button_CheckNFC).setVisibility(View.VISIBLE);
                    MY_KEYBOARD.findViewById(R.id.button_NFC).setVisibility(View.VISIBLE);
                    MY_KEYBOARD.findViewById(R.id.button_Browser).setVisibility(View.VISIBLE);
                    MY_KEYBOARD.findViewById(R.id.button_Call).setVisibility(View.VISIBLE);
                    Toast toast = Toast.makeText(MainActivity.this, "Showed new options", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 950);
                    toast.show();
                    Button buttonExpand = MY_KEYBOARD.findViewById(R.id.button_Expand);
                    buttonExpand.setText("Hide");
                } else {
                    MY_KEYBOARD.findViewById(R.id.button_CheckNFC).setVisibility(View.INVISIBLE);
                    MY_KEYBOARD.findViewById(R.id.button_NFC).setVisibility(View.INVISIBLE);
                    MY_KEYBOARD.findViewById(R.id.button_Browser).setVisibility(View.INVISIBLE);
                    MY_KEYBOARD.findViewById(R.id.button_Call).setVisibility(View.INVISIBLE);
                    Toast toast = Toast.makeText(MainActivity.this, "Hid new options", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 950);
                    toast.show();
                    Button buttonExpand = MY_KEYBOARD.findViewById(R.id.button_Expand);
                    buttonExpand.setText("Expand");
                }
            }
        });

        final NfcAdapter NFC_ADAPTER = NfcAdapter.getDefaultAdapter(this);
        MY_KEYBOARD.findViewById(R.id.button_CheckNFC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NFC_ADAPTER == null) {
                    Toast toast = Toast.makeText(MainActivity.this, "NFC is not available for this device", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 950);
                    toast.show();
                } else if (NFC_ADAPTER.isEnabled()) {
                    Toast toast = Toast.makeText(MainActivity.this, "NFC is enabled", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 950);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(MainActivity.this, "NFC is disabled", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 950);
                    toast.show();
                }
            }
        });

        MY_KEYBOARD.findViewById(R.id.button_NFC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    intent = new Intent(Settings.ACTION_NFC_SETTINGS);
                } else {
                    intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                }
                startActivity(intent);
                Toast toast = Toast.makeText(MainActivity.this, "Opening NFC options", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 950);
                toast.show();
            }
        });

        final Intent BROWSER_INTENT = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        MY_KEYBOARD.findViewById(R.id.button_Browser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(BROWSER_INTENT);
                Toast toast = Toast.makeText(MainActivity.this, "Opening browser", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 950);
                toast.show();
            }
        });

        final Intent CALL_INTENT = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:123456789"));
        MY_KEYBOARD.findViewById(R.id.button_Call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CALL_INTENT);
                Toast toast = Toast.makeText(MainActivity.this, "Calling mommy", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 950);
                toast.show();
            }
        });

    }
}
