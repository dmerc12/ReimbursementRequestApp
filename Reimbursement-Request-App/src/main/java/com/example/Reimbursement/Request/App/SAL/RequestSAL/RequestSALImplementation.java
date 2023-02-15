package com.example.Reimbursement.Request.App.SAL.RequestSAL;

import com.example.Reimbursement.Request.App.DAL.RequestDAL.RequestDALImplementation;
import com.example.Reimbursement.Request.App.Entities.Request;

public class RequestSALImplementation implements RequestSALInterface{
    private RequestDALImplementation requestDAO;

    @Override
    public Request serviceAddRequest(Request request) {
        return null;
    }

    @Override
    public Request serviceGetRequest(int requestId) {
        return null;
    }

    @Override
    public Request serviceUpdateRequest(Request request) {
        return null;
    }

    @Override
    public boolean serviceDeleteRequest(int requestId) {
        return false;
    }
}
