const handler = async (req, res) => {
    const method = 'GET';

    const sessionId = req.query.sessionId;

    const requestOptions = {
        method: method,
        headers: {'Content-Type': 'application/json'}
    };

    const URL = `http://localhost:8080/get/employee/${sessionId}`;

    try {
        const response = await fetch(URL, requestOptions);
        const data = await response.json();
        if (response.status === 200) {
            return res.end(JSON.stringify({'success': data}))
        } else if (response.status === 400) {
            return res.end(JSON.stringify({'error': data}))
        } else {
            return res.end(JSON.stringify({'error': "Cannot connect to the back end, please try again!"}))
        }
    } catch (error) {
        return res.end(JSON.stringify({ 'error': error.message }));
    }
}

export default handler;