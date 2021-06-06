package com.se.framework.data.remote;

import com.rx2androidnetworking.Rx2AndroidNetworking;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppApiHelper implements ApiHelper{

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", request.getId());
            jsonObject.put("pw", request.getPw());
        } catch (JSONException e){
            e.printStackTrace();
        }
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_LOGIN)
                .addHeaders("accept", "*/*")
                .addHeaders("Content-Type", "application/json")
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    @Override
    public Single<SignupResponse> doSignupApiCall(SignupRequest request){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("answer", request.getAns());
            jsonObject.put("email", request.getEmail());
            jsonObject.put("id", request.getId());
            jsonObject.put("name", request.getName());
            jsonObject.put("nickname", request.getNickname());
            jsonObject.put("password", request.getPw());
            jsonObject.put("phoneNumber", request.getPhoneNumber());
            jsonObject.put("questionId", request.getQuestionid());
            jsonObject.put("studentId", request.getStuNum());
            jsonObject.put("type", "STUDENT");
        } catch (JSONException e){
            e.printStackTrace();
        }

        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_SIGNUP)
                .addHeaders("accept", "*/*")
                .addHeaders("Content-Type", "application/json")
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectSingle(SignupResponse.class);
    }

    @Override
    public Single<CategoryResponse> doCategoryApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SERVER_MENU)
                .addHeaders("accept", "*/*")
                .build()
                .getObjectSingle(CategoryResponse.class);
    }

    @Override
    public Single<PostResponse> doPostApiCall(int currentCategory){
        String url = "?boardId=" + Integer.toString(currentCategory) + "&direction=DESC&page=1&size=50";
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SERVER_POST + url)
                .addHeaders("accept", "*/*")
                .build()
                .getObjectSingle(PostResponse.class);
    }

    @Override
    public Single<LookupResponse> doLookupApiCall(int postId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SERVER_POST + "/" +Integer.toString(postId))
                .addHeaders("accept", "*/*")
                .addHeaders("X-AUTH-TOKEN", mApiHeader.getProtectedApiHeader().getAccessToken())
                .build()
                .getObjectSingle(LookupResponse.class);
    }

    @Override
    public Single<TagResponse> doTagApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SERVER_TAG)
                .addHeaders("accept", "*/*")
                .addHeaders("X-AUTH-TOKEN", mApiHeader.getProtectedApiHeader().getAccessToken())
                .build()
                .getObjectSingle(TagResponse.class);
    }

    @Override
    public Single<WriteResponse> doWriteApiCall(WriteRequest request) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject temp = new JSONObject();
            JSONArray array = new JSONArray();
            temp.put("anonymousNickname", request.getAnonymous().getAnonymousNickname());
            temp.put("anonymousPassword", request.getAnonymous().getAnonymousPassword());
            jsonObject.put("anonymous", temp);

            jsonObject.put("attachmentList", array);
            jsonObject.put("boardId", request.getBoardId());
            jsonObject.put("isNotice", request.getIsNotice());
            jsonObject.put("isSecret", request.getIsSecret());

            temp = new JSONObject();
            temp.put("text", request.getPostContent().getText());
            temp.put("title", request.getPostContent().getTitle());

            jsonObject.put("postContent",temp);

            array = new JSONArray();
            if(request.getTagList().size() != 0){
                for (WriteRequest.TagInt id : request.getTagList()){
                    temp = new JSONObject();
                    temp.put("tagId", id.getTagId());
                    array.put(temp);
                }
            }

            jsonObject.put("tagList", array);
        } catch (JSONException e){
            e.printStackTrace();
        }

        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_POST)
                .addHeaders("accept", "*/*")
                .addHeaders("Content-Type", "application/json")
                .addHeaders("X-AUTH-TOKEN", mApiHeader.getProtectedApiHeader().getAccessToken())
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectSingle(WriteResponse.class);
    }

    @Override
    public Single<MyResponse> doMyApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SERVER_MY)
                .addHeaders("accept", "*/*")
                .addHeaders("X-AUTH-TOKEN", mApiHeader.getProtectedApiHeader().getAccessToken())
                .build()
                .getObjectSingle(MyResponse.class);
    }

    @Override
    public Single<MyTagResponse> doMyTagApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SERVER_TAG_LISTEN_MY)
                .addHeaders("accept", "*/*")
                .addHeaders("X-AUTH-TOKEN", mApiHeader.getProtectedApiHeader().getAccessToken())
                .build()
                .getObjectSingle(MyTagResponse.class);
    }

    @Override
    public Single<MyTagAddResponse> doMyTagAddApiCall(MyTagAddRequest request) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("tagId", request.getTagId());
        } catch (JSONException e){
            e.printStackTrace();
        }

        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_TAG_LISTEN)
                .addHeaders("accept", "*/*")
                .addHeaders("Content-Type", "application/json")
                .addHeaders("X-AUTH-TOKEN", mApiHeader.getProtectedApiHeader().getAccessToken())
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectSingle(MyTagAddResponse.class);
    }

    @Override
    public Single<MyTagDelResponse> doMyTagDelApiCall(int delTagId) {
        return Rx2AndroidNetworking.delete(ApiEndPoint.ENDPOINT_SERVER_TAG_LISTEN + "/" +Integer.toString(delTagId))
                .addHeaders("accept", "*/*")
                .addHeaders("X-AUTH-TOKEN", mApiHeader.getProtectedApiHeader().getAccessToken())
                .build()
                .getObjectSingle(MyTagDelResponse.class);
    }

    @Override
    public Single<SearchResponse> doSearchApiCall(SearchRequest request) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("boardId", request.getBoardId());
            jsonObject.put("keyword", request.getKeyword());
            JSONObject tmp = new JSONObject();
            tmp.put("direction", "DESC");
            tmp.put("page", 1);
            tmp.put("size", 50);
            jsonObject.put("pageRequest", tmp);
            jsonObject.put("postSearchType", "TITLE_TEXT");
        } catch (JSONException e){
            e.printStackTrace();
        }

        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_SEARCH)
                .addHeaders("accept", "*/*")
                .addHeaders("Content-Type", "application/json")
                .addHeaders("X-AUTH-TOKEN", mApiHeader.getProtectedApiHeader().getAccessToken())
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectSingle(SearchResponse.class);
    }
}
