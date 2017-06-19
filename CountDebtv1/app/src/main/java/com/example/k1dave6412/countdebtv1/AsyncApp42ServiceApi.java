package com.example.k1dave6412.countdebtv1;

import com.shephertz.app42.gaming.multiplayer.client.WarpClient;

public class AsyncApp42ServiceApi {

    private static WarpClient warpClient = null;

    private static AsyncApp42ServiceApi mInstance = null;
    private static String App42ApiKey = "028787cc650776dc57338c958c98e1db04e83f9f22747ae90f70f350e153bd8b";
    private static String App42ApiSecret = "2f4a6eef4702d8afb9e78892bc971be38f020b85e4009c5b61b96102da42bfd9";


    private AsyncApp42ServiceApi() {
        WarpClient.initialize(App42ApiKey, App42ApiSecret);
    }

    public static WarpClient getMyWarpClient() {
        if (warpClient == null) {
            try {
                warpClient = WarpClient.getInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return warpClient;
    }

    public static AsyncApp42ServiceApi instance() {
        if (mInstance == null) {
            mInstance = new AsyncApp42ServiceApi();
        }

        return mInstance;
    }
}