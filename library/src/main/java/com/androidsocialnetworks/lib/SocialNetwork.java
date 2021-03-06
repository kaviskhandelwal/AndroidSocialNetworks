package com.androidsocialnetworks.lib;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.io.File;

public abstract class SocialNetwork {
    private static final String TAG = SocialNetwork.class.getSimpleName();

    private static final String SHARED_PREFERENCES_NAME = "social_networks";

    protected Fragment mSocialNetworkManager;
    protected SharedPreferences mSharedPreferences;

    protected OnLoginCompleteListener mOnLoginCompleteListener;
    protected OnRequestSocialPersonListener mOnRequestSocialPersonListener;
    protected OnPostingListener mOnPostingListener;

    protected OnCheckingIsFriendListener mOnCheckingIsFriendListener;
    protected OnAddFriendListener mOnAddFriendListener;
    protected OnRemoveFriendListener mOnRemoveFriendListener;

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

    public void setOnPostingListener(OnPostingListener onPostingListener) {
        mOnPostingListener = onPostingListener;
    }

    public void setOnCheckingIsFriendListener(OnCheckingIsFriendListener onCheckingIsFriendListener) {
        mOnCheckingIsFriendListener = onCheckingIsFriendListener;
    }

    public void setOnAddFriendListener(OnAddFriendListener onAddFriendListener) {
        mOnAddFriendListener = onAddFriendListener;
    }

    public void setOnRemoveFriendListener(OnRemoveFriendListener onRemoveFriendListener) {
        mOnRemoveFriendListener = onRemoveFriendListener;
    }

    public abstract boolean isConnected();

    public abstract void requestLogin() throws SocialNetworkException;

    public abstract void logout();

    public abstract int getID();

    public abstract void requestPerson() throws SocialNetworkException;

    public abstract void requestPostMessage(String message) throws SocialNetworkException;

    public abstract void requestPostPhoto(File photo, String message) throws SocialNetworkException;

    public abstract void requestCheckIsFriend(String userID) throws SocialNetworkException;

    public abstract void requestAddFriend(String userID) throws SocialNetworkException;

    public abstract void requestRemoveFriend(String userID) throws SocialNetworkException;

    // cancel login requests

    public abstract void cancelLoginRequest();

    public abstract void cancelGetPersonRequest();

    public abstract void cancelPostMessageRequest();

    public abstract void cancelPostPhotoRequest();

    public abstract void cancenCheckIsFriendRequest();

    public abstract void cancelAddFriendRequest();

    public abstract void cancenRemoveFriendRequest();

    protected void checkRequestState(AsyncTask request) throws SocialNetworkException {
        if (request != null) {
            throw new SocialNetworkException("Request is already running");
        }
    }

    public static interface OnLoginCompleteListener {
        public void onLoginSuccess(int socialNetworkID);

        public void onLoginFailed(int socialNetworkID, String reason);
    }

    public static interface OnRequestSocialPersonListener {
        public void onRequestSocialPersonSuccess(int socialNetworkID, SocialPerson socialPerson);

        public void onRequestSocialPersonFailed(int socialNetworkID, String reason);
    }

    public static interface OnPostingListener {
        public void onPostSuccessfully(int socialNetworkID);

        public void onPostFailed(int socialNetworkID, String reason);
    }

    public static interface OnCheckingIsFriendListener {
        public void onCheckIsFriendSuccess(int socialNetworkID, String userID, boolean isFriend);

        public void onCheckIsFriendFailed(int socialNetworkID, String userID, String error);
    }

    public static interface OnAddFriendListener {
        public void onAddFriendSuccess(int socialNetworkID, String userID);

        public void onAddFriendFailed(int socialNetworkID, String userID, String error);
    }

    public static interface OnRemoveFriendListener {
        public void onRemoveFriendSuccess(int socialNetworkID, String userID);

        public void onRemoveFriendFailed(int socialNetworkID, String userID, String error);
    }

}
