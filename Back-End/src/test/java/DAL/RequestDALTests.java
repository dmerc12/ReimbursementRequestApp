package DAL;

import DAL.RequestDAL.RequestDALImplementation;
import Entities.Data.Request;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RequestDALTests {
    int currentRequestId = 1;
    RequestDALImplementation requestDAO = new RequestDALImplementation();
    Request successfulRequest = new Request(0, -1, -1, "test", 25.00);
    Request updateRequest = new Request(currentRequestId, successfulRequest.getEmployeeId(),
            successfulRequest.getCategoryId(), "updated", 20.00);

    @Test
    public void a_addRequestSuccess() {
        Request result = requestDAO.addRequest(successfulRequest);
        Assert.assertNotEquals(result.getRequestId(), 0);
    }

    @Test
    public void b_getAllRequestsSuccess() {
        List<Request> result = requestDAO.getAllRequests(-1);
        Assert.assertFalse(result.isEmpty());
    }

    @Test
    public void c_getRequestSuccess() {
        Request result = requestDAO.getRequest(-1);
        Assert.assertNotNull(result);
    }

    @Test
    public void d_updateRequestSuccess() {
        Request result = requestDAO.updateRequest(updateRequest);
        Assert.assertEquals(updateRequest.getRequestId(), result.getRequestId());
        Assert.assertEquals(updateRequest.getEmployeeId(), result.getEmployeeId());
        Assert.assertEquals(updateRequest.getCategoryId(), result.getCategoryId());
        Assert.assertEquals(updateRequest.getComment(), result.getComment());
        Assert.assertEquals(updateRequest.getAmount(), result.getAmount(), 0);
    }

    @Test
    public void e_deleteRequestSuccess() {
        int result = requestDAO.deleteRequest(currentRequestId);
        Assert.assertTrue(result != 0);
    }

    @Test
    public void f_deleteAllRequestsSuccess() {
        Request request = new Request(0, -2, -1, "comment", 50.00);
        Request newRequest = requestDAO.addRequest(request);
        int result = requestDAO.deleteAllRequests(newRequest.getEmployeeId());
        Assert.assertTrue(result != 0);
    }
}
