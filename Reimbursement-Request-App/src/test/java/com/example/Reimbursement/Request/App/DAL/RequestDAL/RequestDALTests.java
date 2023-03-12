package com.example.Reimbursement.Request.App.DAL.RequestDAL;

import com.example.Reimbursement.Request.App.Entities.Request;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class RequestDALTests {
    int currentRequestId = 1;
    RequestDALImplementation requestDAO = new RequestDALImplementation();
    Request successfulRequest = new Request(0, -1, -1, "test", 25.00);
    Request updateRequest = new Request(currentRequestId, successfulRequest.getEmployeeId(),
            successfulRequest.getCategoryId(), "updated", 20.00);

    @Test
    public void addRequestSuccess() {
        Request result = requestDAO.addRequest(successfulRequest);
        Assert.assertNotEquals(result.getRequestId(), 0);
    }

    @Test
    public void getAllRequestsSuccess() {
        List<Request> result = requestDAO.getAllRequests(-1);
        Assert.assertFalse(result.isEmpty());
    }

    @Test
    public void getRequestSuccess() {
        Request result = requestDAO.getRequest(-1);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateRequestSuccess() {
        Request result = requestDAO.updateRequest(updateRequest);
        Assert.assertEquals(updateRequest.getRequestId(), result.getRequestId());
        Assert.assertEquals(updateRequest.getEmployeeId(), result.getEmployeeId());
        Assert.assertEquals(updateRequest.getCategoryId(), result.getCategoryId());
        Assert.assertEquals(updateRequest.getComment(), result.getComment());
        Assert.assertEquals(updateRequest.getAmount(), result.getAmount());
    }

    @Test
    public void deleteRequestSuccess() {
        int result = requestDAO.deleteRequest(currentRequestId);
        Assert.assertTrue(result != 0);
    }
}
