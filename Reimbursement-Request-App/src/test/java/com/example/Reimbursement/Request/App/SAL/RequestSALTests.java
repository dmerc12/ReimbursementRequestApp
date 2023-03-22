package com.example.Reimbursement.Request.App.SAL;

import com.example.Reimbursement.Request.App.DAL.RequestDAL.RequestDALImplementation;
import com.example.Reimbursement.Request.App.Entities.CustomExceptions.GeneralError;
import com.example.Reimbursement.Request.App.Entities.Request;
import com.example.Reimbursement.Request.App.SAL.RequestSAL.RequestSALImplementation;
import org.junit.Assert;
import org.junit.Test;

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
