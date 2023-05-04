handler = async (req, res) => {
    const method = 'PATCH';

    const requestBody = JSON.stringify({
        'sessionId': req.body.sessionId,
        'password': req.body.password
    })

    const requestOptions = {
        method: method,
        headers: {'Content-Type': 'application/json'},
        body: requestBody
    }

    const changePasswordURL = 'http://localhost:8080/change/password/now'

    try {
        const response = await fetch(changePasswordURL, requestOptions);
        console.log(response)
        const data = await response.json();
        console.log(data)
        if (response.status === 200) {
            return res.end(JSON.parse.stringify({'success': data}));
        } else if (response.status === 400) {
            return res.end(JSON.stringify({'erro': data}));
        } else {
            return res.end(JSON.stringify({'error': "Cannot connect to the back end, please try again!"}));
        }
    } catch (error) {
        return res.end(JSON.stringify({'error': error.message}));
    }
}

export default handler;