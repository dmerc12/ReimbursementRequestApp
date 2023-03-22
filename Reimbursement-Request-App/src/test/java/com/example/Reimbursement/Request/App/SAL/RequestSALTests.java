package com.example.Reimbursement.Request.App.SAL;

import com.example.Reimbursement.Request.App.DAL.RequestDAL.RequestDALImplementation;
import com.example.Reimbursement.Request.App.Entities.CustomExceptions.GeneralError;
import com.example.Reimbursement.Request.App.Entities.Request;
import com.example.Reimbursement.Request.App.SAL.RequestSAL.RequestSALImplementation;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class RequestSALTests {
    int currentRequestId = 1;
    RequestDALImplementation requestDAO = new RequestDALImplementation();
    RequestSALImplementation requestSAO = new RequestSALImplementation(requestDAO);
    Request successRequest = new Request(0, -1, -1, "success", 25.00);
    Request updateRequest = new Request(currentRequestId, -1, -1, "updated",
            successRequest.getAmount() + 25.00);

    @Test
    public void aa_addRequestCommentLeftEmpty() {
        try {
            Request testRequest = new Request(0, -1, -1, "", 25.00);
            requestSAO.addRequest(testRequest);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The comment field cannot be left empty, please try again!");
        }
    }

    @Test
    public void ab_addRequestCommentTooLong() {
        try {
            Request testRequest = new Request(0, -1, -1, "this is way too long " +
                    "and so it should fail and bring about a desired warning message, if not then I have a bug on " +
                    "my hands and hopefully this test should catch it and tell me 'hey! the problem is right here!'",
                    25.00);
            requestSAO.addRequest(testRequest);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The comment field cannot exceed 150 characters, please try again!");
        }
    }

    @Test
    public void ac_addRequestAmountNegativeOrZero() {
        try {
            Request testRequest = new Request(0, -1, -1, "test", -25.00);
            requestSAO.addRequest(testRequest);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The amount field cannot be below $0.01, please try again!");
        }
    }

    @Test
    public void ad_addRequestEmployeeNotFound() {
        try {
            Request testRequest = new Request(0, -500000, -1, "test",25.00);
            requestSAO.addRequest(testRequest);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "No employee found, please try again!");
        }
    }

    @Test
    public void ae_addRequestCategoryNotFound() {
        try {
            Request testRequest = new Request(0, -1, -50000, "test", 25.00);
            requestSAO.addRequest(testRequest);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category not found, please try again!");
        }
    }

    @Test
    public void af_addRequestSuccess() {
        Request result = requestSAO.addRequest(successRequest);
        Assert.assertNotEquals(result.getRequestId(), 0);
    }

    @Test
    public void ba_getAllRequestsNoneFound() {
        try {
            requestSAO.getAllRequests(-2);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "No requests found, please try again!");
        }
    }

    @Test
    public void bb_getAllRequestsEmployeeNotFound() {
        try {
            requestSAO.getAllRequests(-5000000);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "No employee found, please try again!");
        }
    }

    @Test
    public void bc_getAllRequestsSuccess() {
        List<Request> result = requestSAO.getAllRequests(successRequest.getEmployeeId());
        Assert.assertTrue(result.size() >= 1);
    }

    @Test
    public void ca_getRequestNotFound() {
        try {
            requestSAO.getRequest(-500000000);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "No request found, please try again!");
        }
    }

    @Test
    public void cb_getRequestSuccess() {
        Request result = requestSAO.getRequest(-1);
        Assert.assertNotNull(result);
    }

    @Test
    public void da_updateRequestNotFound() {
        try {
            Request testRequest = new Request(-50000, -1, -1, "test", 25.00);
            requestSAO.updateRequest(testRequest);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "No request found, please try again!");
        }
    }

    @Test
    public void db_updateRequestCommentEmpty() {
        try {
            Request testRequest = new Request(currentRequestId, -1, -1, "", 25.00);
            requestSAO.updateRequest(testRequest);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The comment field cannot be left empty, please try again!");
        }
    }

    @Test
    public void dc_updateRequestCommentTooLong() {
        try {
            Request testRequest = new Request(currentRequestId, -1, -1, "this is way too" +
                    " long and so it should fail and bring about a desired warning message, if not then I have a " +
                    "bug on my hands and hopefully this test should catch it and tell me 'hey! the problem is " +
                    "right here!'", 25.00);
            requestSAO.updateRequest(testRequest);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The comment field cannot exceed 150 characters, please try again!");
        }
    }

    @Test
    public void dd_updateRequestAmountNegativeOrZero() {
        try {
            Request testRequest = new Request(currentRequestId, -1, -1, "test", -25.00);
            requestSAO.updateRequest(testRequest);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The amount field cannot be below $0.01, please try again!");
        }
    }

    @Test
    public void de_updateRequestCategoryNotFound() {
        try {
            Request testRequest = new Request(currentRequestId, -1, -500000000, "test", 25.00);
            requestSAO.updateRequest(testRequest);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Category not found, please try again!");
        }
    }

    @Test
    public void df_updateRequestSuccess() {
        Request result = requestSAO.updateRequest(updateRequest);
        Assert.assertEquals(result.getCategoryId(), updateRequest.getCategoryId());
        Assert.assertEquals(result.getComment(), updateRequest.getComment());
        Assert.assertEquals(result.getAmount(), updateRequest.getAmount());
    }

    @Test
    public void e_deleteRequestSuccess() {

    }
}
