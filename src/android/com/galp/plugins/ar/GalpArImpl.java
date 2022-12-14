package com.galp.plugins.ar;

import android.content.Intent;
import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GalpArImpl extends CordovaPlugin {

    static private CallbackContext _callbackContext;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        _callbackContext = callbackContext;

        if (action.equals("openObject")) {

            JSONObject params = args.optJSONObject(0);
            String resourcePath = params.optString("resourcePath");
            this.openObject(resourcePath);
            return true;
        }
        return false;
    }

    private void openObject(String resourcePath) {
        Intent intentAr = new Intent( this.cordova.getContext(), CustomArActivity.class);
        intentAr.putExtra("RESOURCE_PATH", resourcePath);
        this.cordova.startActivityForResult(null, intentAr, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (_callbackContext != null) {
            Log.d("AR", "Result Augumented Reality - AR");
        }
    }

}