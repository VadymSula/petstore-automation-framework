package com.test.requests;

import com.test.core.DeleteRequest;
import com.test.core.GetRequest;
import com.test.core.PostRequest;

public abstract class BaseController {
    protected final PostRequest postRequest;
    protected final GetRequest getRequest;
    protected final DeleteRequest deleteRequest;

    BaseController() {
        postRequest = new PostRequest();
        getRequest = new GetRequest();
        deleteRequest = new DeleteRequest();
    }
}
