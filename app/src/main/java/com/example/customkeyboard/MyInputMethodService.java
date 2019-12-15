package com.example.customkeyboard;

import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.MediaPlayer;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyInputMethodService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    @Override
    public View onCreateInputView() {
        KeyboardView keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        Keyboard keyboard = new Keyboard(this, R.xml.page1);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }

    public View onCreateInputView2() {
        KeyboardView keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        Keyboard keyboard = new Keyboard(this, R.xml.page2);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }

    public View onCreateInputView3() {
        KeyboardView keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        Keyboard keyboard = new Keyboard(this, R.xml.page3);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection inputConnection = getCurrentInputConnection();


        if (inputConnection != null) {
            switch (primaryCode) {
                case 1:
                    inputConnection.commitText("This is example text", 0);
                    Toast textToast = Toast.makeText(this, "Text setted", Toast.LENGTH_SHORT);
                    textToast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0 , 1200);
                    textToast.show();
                    break;
                case 2:
                    final MediaPlayer SOUND_MEDIA_PLAYER = MediaPlayer.create(this, R.raw.sound);
                    SOUND_MEDIA_PLAYER.start();
                    Toast soundToast = Toast.makeText(this, "Playing sound", Toast.LENGTH_SHORT);
                    soundToast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0 , 1200);
                    soundToast.show();
                    break;
                case 3:
                    final Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivity(cameraIntent);
                    Toast cameraToast = Toast.makeText(this, "Opening camera", Toast.LENGTH_SHORT);
                    cameraToast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0 , 1200);
                    cameraToast.show();
                    break;
                case 4:
                    String toSave = "This text will be save";
                    File file = new File(getExternalFilesDir(null), "keyboard.txt");
                    try {
                        Toast beforeSaveToast = Toast.makeText(this, "Saving file", Toast.LENGTH_SHORT);
                        beforeSaveToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 1200);
                        beforeSaveToast.show();

                        file.createNewFile();
                        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                        fileOutputStream.write(toSave.getBytes());
                        fileOutputStream.flush();
                        fileOutputStream.close();

                        Toast afterSaveToast = Toast.makeText(this, "Saved", Toast.LENGTH_SHORT);
                        afterSaveToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 1200);
                        afterSaveToast.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast errorSaveToast = Toast.makeText(this, "Something went wrong with saving file", Toast.LENGTH_SHORT);
                        errorSaveToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 1200);
                        errorSaveToast.show();
                    }
                    break;
                case 5:
                    setInputView(onCreateInputView2());
                    break;
                case 6:
                    setInputView(onCreateInputView());
                    break;
                case 7:
                    Toast testToast = Toast.makeText(this, "This is toast message", Toast.LENGTH_SHORT);
                    testToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 1200);
                    testToast.show();
                    break;
                case 8:
                    final NfcAdapter NFC_ADAPTER = NfcAdapter.getDefaultAdapter(this);
                    if (NFC_ADAPTER == null) {
                        Toast toast = Toast.makeText(this, "NFC is not available for this device", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 1200);
                        toast.show();
                    } else if (NFC_ADAPTER.isEnabled()) {
                        Toast toast = Toast.makeText(this, "NFC is enabled", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 1200);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(this, "NFC is disabled", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 1200);
                        toast.show();
                    }
                    break;
                case 9:
                    Intent intent;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        intent = new Intent(Settings.ACTION_NFC_SETTINGS);
                    } else {
                        intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                    }
                    startActivity(intent);
                    Toast nfcToast = Toast.makeText(this, "Opening NFC options", Toast.LENGTH_SHORT);
                    nfcToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 1200);
                    nfcToast.show();
                    break;
                case 10:
                    setInputView(onCreateInputView3());
                    break;
                case 11:
                    setInputView(onCreateInputView2());
                    break;
                case 12:
                    final Intent BROWSER_INTENT = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                    startActivity(BROWSER_INTENT);
                    Toast browserToast = Toast.makeText(this, "Opening browser", Toast.LENGTH_SHORT);
                    browserToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 1200);
                    browserToast.show();
                    break;
                case 13:
                    final Intent CALL_INTENT = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:123456789"));
                    startActivity(CALL_INTENT);
                    Toast callingToast = Toast.makeText(this, "Calling mommy", Toast.LENGTH_SHORT);
                    callingToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 1200);
                    callingToast.show();
                    break;
            }
        }
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
