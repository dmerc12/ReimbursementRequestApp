export default function RequestList({ sessionId, requests }) {
    let requestRows = [];
    if (requests.success) {
        for (let i=0; i<requests.success.length; i++) {
            const request = requests.success[i];
            requestRows.unshift(
                <tr key={request.requestId}>
                    <td className='request-table-data'>{request.requestId}</td>
                    <td className='request-table-data'>{request.categoryId}</td>
                    <td className='request-table-data'>{request.comment}</td>
                    <td className='request-table-data'>{request.amount}</td>
                    <td className='request-table-data flex gap-5'>

                    </td>
                </tr>
            )
        }
    }

    return (
        <>
            <div className="request-list overflow-x-auto">
                <table className='table w-full'>
                    <thead>
                        <tr>
                            <th className="request-table-head">Request ID</th>
                            <th className="request-table-head">Category</th>
                            <th className="request-table-head">Comment</th>
                            <th className="request-table-head">Amount</th>
                            <th className="request-table-head">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {requestRows}
                    </tbody>
                </table>
            </div>
        </>
    )
}