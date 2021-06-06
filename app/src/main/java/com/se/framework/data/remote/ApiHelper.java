package com.se.framework.data.remote;

import com.se.framework.data.model.api.CategoryResponse;
import com.se.framework.data.model.api.LoginRequest;
import com.se.framework.data.model.api.LoginResponse;
import com.se.framework.data.model.api.LookupResponse;
import com.se.framework.data.model.api.MyResponse;
import com.se.framework.data.model.api.MyTagAddRequest;
import com.se.framework.data.model.api.MyTagAddResponse;
import com.se.framework.data.model.api.MyTagDelResponse;
import com.se.framework.data.model.api.MyTagResponse;
import com.se.framework.data.model.api.PostResponse;
import com.se.framework.data.model.api.SearchRequest;
import com.se.framework.data.model.api.SearchResponse;
import com.se.framework.data.model.api.SignupRequest;
import com.se.framework.data.model.api.SignupResponse;
import com.se.framework.data.model.api.TagResponse;
import com.se.framework.data.model.api.WriteRequest;
import com.se.framework.data.model.api.WriteResponse;

import io.reactivex.Single;

public interface ApiHelper {
    ApiHeader getApiHeader();

    Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request);

    Single<SignupResponse> doSignupApiCall(SignupRequest request);

    Single<CategoryResponse> doCategoryApiCall();

    Single<PostResponse> doPostApiCall(int currentCategory);

    Single<LookupResponse> doLookupApiCall(int postId);

    Single<TagResponse> doTagApiCall();

    Single<WriteResponse> doWriteApiCall(WriteRequest request);

    Single<MyResponse> doMyApiCall();

    Single<MyTagResponse> doMyTagApiCall();

    Single<MyTagAddResponse> doMyTagAddApiCall(MyTagAddRequest request);

    Single<MyTagDelResponse> doMyTagDelApiCall(int delTagId);

    Single<SearchResponse> doSearchApiCall(SearchRequest request);

}
