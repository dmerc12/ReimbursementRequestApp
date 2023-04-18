const handler = async (req, res) => {
    const method = 'POST';

    const requestBody = JSON.stringify({
        'firstName': req.body.firstName, 
        'lastName': req.body.lastName, 
        'email': req.body.email, 
        'password': req.body.password, 
        'phoneNumber': req.body.phoneNumber, 
        'address': req.body.address
    })

    const requestOptions = {
        method: method,
        headers: {'Content-Type': 'application/json'},
        body: requestBody
    }

    const registerURL = 'http://localhost:8080/register/now';

    try {
        const response = await fetch(registerURL, requestOptions);
        const data = await response.json();
        if (response.status === 201) {
            return res.end(JSON.stringify({'success': data}));
        } else if (response.status === 400) {
            return res.end(JSON.stringify({'error':data}));
        } else {
            return res.end(JSON.stringify({'error': "Cannot connect to the back end, please try again!"}));
        }
    } catch (error) {
        return res.end(JSON.stringify({'error': error.message}));
    }
}

export default handler;