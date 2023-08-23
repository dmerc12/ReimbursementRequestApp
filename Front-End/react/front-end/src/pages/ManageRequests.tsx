import { AddRequest } from "../components/ui/request/AddRequest";
import { RequestList } from "../components/ui/request/RequestList";

export const ManageRequests = () => {
    return (
        <>
            <h1>Manage Requests Below!</h1>
            <AddRequest />
            <RequestList />
        </>
    );
}