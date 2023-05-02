const handler = async (req, res) => {
    const method = '';

    const requestBody = JSON.stringify({
        'sessionId': req.body.sessionId,
        'categoryId': req.body.categoryId,
        'categoryName': req.body.categoryName
    });

    const requestOptions = {
        method: method,
        headers: {'Content-Type': 'application/json'},
        body: requestBody
    };

    const URL = 'http://localhost:8080/update/category/now';

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