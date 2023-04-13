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

        if (response.status === 200) {
            const data = await response.json();

            return res.end(JSON.stringify({'success': data}))
        } else if (response.status === 400) {
            const data = await response.json();

            return res.end(JSON.stringify({'error': data}))
        }
    } catch (error) {
        return res.end(JSON.stringify({'error': error.message}));
    }
}

export default handler;