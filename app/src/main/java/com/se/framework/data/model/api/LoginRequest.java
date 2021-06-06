package com.se.framework.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class LoginRequest {
    private LoginRequest() {
        // This class is not publicly instantiable
    }

    public static class ServerLoginRequest {
        @Expose
        @SerializedName("id")
        private String id;

        @Expose
        @SerializedName("pw")
        private String pw;

        public ServerLoginRequest(String id, String pw) {
            this.id = id;
            this.pw = pw;
        }

        public String getId() {
            return id;
        }

        public String getPw() {
            return pw;
        }
    }
}
