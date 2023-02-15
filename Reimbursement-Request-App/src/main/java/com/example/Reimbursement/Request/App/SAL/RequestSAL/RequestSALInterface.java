package com.example.Reimbursement.Request.App.SAL.RequestSAL;

import com.example.Reimbursement.Request.App.Entities.Request;

public interface RequestSALInterface {
    Request serviceAddRequest(Request request);
    Request serviceGetRequest(int requestId);
    Request serviceUpdateRequest(Request request);
    boolean serviceDeleteRequest(int requestId);
}
