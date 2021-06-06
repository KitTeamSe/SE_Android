package com.se.framework.data.remote;

import com.se.framework.BuildConfig;

public final class ApiEndPoint {
    private ApiEndPoint() {

    }

    public static final String ENDPOINT_SERVER_LOGIN = BuildConfig.BASE_URL + "signin";

    public static final String ENDPOINT_SERVER_SIGNUP = BuildConfig.BASE_URL + "signup";

    public static final String ENDPOINT_SERVER_MENU = BuildConfig.BASE_URL + "menu";

    public static final String ENDPOINT_SERVER_POST = BuildConfig.BASE_URL + "post";

    public static final String ENDPOINT_SERVER_TAG = BuildConfig.BASE_URL + "tag?direction=ASC&page=1&size=50";

    public static final String ENDPOINT_SERVER_MY = BuildConfig.BASE_URL + "account/my";

    public static final String ENDPOINT_SERVER_TAG_LISTEN = BuildConfig.BASE_URL + "tag-listen";

    public static final String ENDPOINT_SERVER_TAG_LISTEN_MY = ENDPOINT_SERVER_TAG_LISTEN + "/account/my";

    public static final String ENDPOINT_SERVER_SEARCH = BuildConfig.BASE_URL + "post/search";


}
