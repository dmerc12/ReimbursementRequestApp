package com.example.Reimbursement.Request.App.SAL;

import com.example.Reimbursement.Request.App.DAL.RequestDAL.RequestDALImplementation;
import com.example.Reimbursement.Request.App.Entities.Request;
import com.example.Reimbursement.Request.App.SAL.RequestSAL.RequestSALImplementation;
import org.junit.Test;

public class RequestSALTests {
    int currentRequestId = 1;
    RequestDALImplementation requestDAO = new RequestDALImplementation();
    RequestSALImplementation requestSAO = new RequestSALImplementation(requestDAO);
    Request successRequest = new Request(0, -1, -1, "success", 25.00);
    Request updateRequest = new Request(currentRequestId, -1, -1, "updated",
            successRequest.getAmount() + 25.00);
    @Test
    public void a_addRequestSuccess() {
        
    }

    @Test
    public void b_getAllRequestsSuccess() {

    }

    @Test
    public void c_getRequestSuccess() {

    }

    @Test
    public void d_updateRequestSuccess() {

    }

    @Test
    public void e_deleteRequestSuccess() {

    }
}
