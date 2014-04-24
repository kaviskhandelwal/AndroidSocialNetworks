package com.androidsocialnetworks.lib;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

public abstract class SocialNetwork {
    private static final String TAG = SocialNetwork.class.getSimpleName();

    private static final String SHARED_PREFERENCES_NAME = "social_networks";

    protected Fragment mSocialNetworkManager;
    protected SharedPreferences mSharedPreferences;

    protected OnLoginCompleteListener mOnLoginCompleteListener;
    protected OnRequestSocialPersonListener mOnRequestSocialPersonListener;

    /**
     * @param fragment ant not activity or context, as we will need to call startActivityForResult,
     *                 we will want to receice on onActivityResult in out SocialNetworkManager
     *                 fragment
     */
    protected SocialNetwork(Fragment fragment) {
        mSocialNetworkManager = fragment;

        mSharedPreferences = mSocialNetworkManager
                .getActivity().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void onCreate(Bundle savedInstanceState) {

    }

    public void onStart() {

    }

    public void onResume() {

    }

    public void onPause() {

    }

    public void onStop() {

    }

    public void onDestroy() {

    }

    public void onSaveInstanceState(Bundle outState) {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onRestoreInstanceState: " + requestCode + " : " + resultCode + " : " + data);
    }

    public void setOnLoginCompleteListener(OnLoginCompleteListener onLoginCompleteListener) {
        mOnLoginCompleteListener = onLoginCompleteListener;
    }

    public void setOnRequestSocialPersonListener(OnRequestSocialPersonListener onRequestSocialPersonListener) {
        mOnRequestSocialPersonListener = onRequestSocialPersonListener;
    }

    public abstract boolean isConnected();

    public abstract void login();

    public abstract void logout();

    public abstract int getID();

    public abstract void requestPerson();

    public static interface OnLoginCompleteListener {
        public void onLoginSuccess(int socialNetworkID);

        public void onLoginFailed(int socialNetworkID, String reason);
    }

    public static interface OnRequestSocialPersonListener {
        public void onRequestSocialPersonSuccess(int socialNetworkID, SocialPerson socialPerson);

        public void onRequestSocialPersonFailed(int socialNetworkID, String reason);
    }

}