package org.oyvang.flashlight;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ToggleButton;


public class main extends Activity {
    Parameters params;
    private Camera camera;
    private boolean isFlashOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButton(View v) {
        getCamera();
        if (isFlashOn) {
            turnOffFlash();
        } else {
            turnOnFlash();
        }


    }

    private void getCamera() {
        if (camera == null) {
            try {
                camera = Camera.open();
                params = camera.getParameters();
            } catch (RuntimeException e) {
            }
        }
    }


    private void turnOnFlash() {
        if (!isFlashOn) {
            if (camera == null || params == null) {
                return;
            }
            params = camera.getParameters();
            toggleButtonImage();
            params.setFlashMode(Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            camera.startPreview();
            isFlashOn = true;
        }

    }


    private void turnOffFlash() {
        if (isFlashOn) {
            if (camera == null || params == null) {
                return;
            }
            params = camera.getParameters();
            toggleButtonImage();
            params.setFlashMode(Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
            camera.stopPreview();
            isFlashOn = false;
        }
    }

    private void toggleButtonImage() {
        if (isFlashOn) {
            ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
            toggleButton.setTextColor(Color.GREEN);
        } else {
            ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
            toggleButton.setTextColor(Color.RED);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (camera != null) {
            camera.release();
            camera = null;
        }
    }


}
