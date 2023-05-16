const handler = async (req, res) => {
    const method = 'DELETE';

    const requestBody = JSON.stringify({'sessionId': req.body.sessionId})

    const requestOptions = {
        method: method,
        headers: {'Content-Type': 'application/json'},
        body: requestBody
    }

    const URL = 'http://localhost:8080/delete/employee/now';

    try {
        const response = await fetch(URL, requestOptions);
        const result = await response.json();
        if (response.status === 200) {
            return res.end(JSON.stringify({'success': result}));
        } else if (response.status === 400) {
            return res.end(JSON.stringify({'error': result}));
        } else {
            return res.end(JSON.stringify({'error': "Cannot connect to the back end, please try again!"}));
        }
    } catch (error) {
        return res.end(JSON.stringify({'error': error.message}));
    }
}

export default handler;