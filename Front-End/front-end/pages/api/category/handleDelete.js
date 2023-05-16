const handler = async (req, res) => {
    const sessionId = req.body.sessionId;
    const categoryId = req.body.categoryId;

    const method = 'DELETE';

    const requestOptions = {
        method: method,
        headers: {'Content-Type': 'application/json'},
    };

    const URL = `http://localhost:8080/delete/category/${categoryId}/${sessionId}`;

    try {
        const response = await fetch(URL, requestOptions);
        const data = await response.json();
        if (response.status === 200) {
            return res.end(JSON.stringify({'success': data}));
        } else if (response.status === 400) {
            return res.end(JSON.stringify({'error': data}));
        } else {
            return res.end(JSON.stringify({'error': "Cannot connect to the back end, please try again!"}));
        }
    } catch (error) {
        return res.end(JSON.stringify({'error': error.message}));
    }
}