package com.example.customkeyboard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;

public class MyKeyboard extends LinearLayout {

    private Button buttonText, buttonSound, buttonCamera, butttonSave, buttonToast, buttonExpand,
                   buttonCheckNFC, buttonNFC, buttonBrowser, buttonCall;

    public MyKeyboard(Context context) {
        this(context, null, 0);
    }

    public MyKeyboard(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MyKeyboard(Context context, AttributeSet attributeSet, int defStyleAttribute) {
        super(context, attributeSet, defStyleAttribute);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true);
        buttonText = (Button) findViewById(R.id.button_Text);
        buttonSound = (Button) findViewById(R.id.button_Sound);
        buttonCamera = (Button) findViewById(R.id.button_Camera);
        butttonSave = (Button) findViewById(R.id.button_Save);
        buttonToast = (Button) findViewById(R.id.button_Toast);
        buttonExpand = (Button) findViewById(R.id.button_Expand);

        buttonCheckNFC = (Button) findViewById(R.id.button_CheckNFC);
        buttonCheckNFC.setVisibility(INVISIBLE);
        buttonNFC = (Button) findViewById(R.id.button_NFC);
        buttonNFC.setVisibility(INVISIBLE);
        buttonBrowser = (Button) findViewById(R.id.button_Browser);
        buttonBrowser.setVisibility(INVISIBLE);
        buttonCall = (Button) findViewById(R.id.button_Call);
        buttonCall.setVisibility(INVISIBLE);
    }
}
