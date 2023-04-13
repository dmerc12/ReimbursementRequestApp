const handler = async (req, res) => {
    const method = 'POST';

    const requestBody = JSON.stringify({
        'email': req.body.email,
        'password': req.body.password
    })

    const requestOptions = {
        method: method,
        headers: {'Content-Type': 'application/json'},
        body: requestBody
    }

    const loginURL = 'http://localhost:8080/login/now';

    try {
        const response = await fetch(loginURL, requestOptions);
        const data = await response.json();

        if (response.status === 200) {
            return res.end(JSON.stringify({'success': data}))
        } else if (response.status === 400) {
            return res.end(JSON.stringify({'error': data}))
        } else {
            return "Cannot connect to the back end, please try again!"
        }
    } catch (error) {
        return res.end(JSON.stringify({'error': error.message}));
    }
}

export default handler;