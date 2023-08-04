package org.find.one.data.model;

import androidx.annotation.NonNull;

public class Result {

    private Result() {}

    // Success sub-class
    public final static class Success extends Result {
        private final String user;

        public Success(String user) {
            this.user = user;
        }

        public String getUser() {
            return this.user;
        }
    }

    // Error sub-class
    public final static class Error extends Result {
        private final Exception error;

        public Error(Exception error) {
            this.error = error;
        }

        public Exception getError() {
            return this.error;
        }
    }

    @NonNull
    @Override
    public String toString() {
        if (this instanceof Result.Success) {
            Result.Success success = (Result.Success) this;
            return "Welcome! " + success.getUser();
        } else if (this instanceof Result.Error) {
            Result.Error error = (Result.Error) this;
            return "Error[exception=" + error.getError().getMessage() + "]";
        }
        return "";
    }
}
