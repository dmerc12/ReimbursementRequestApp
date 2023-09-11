export const useFetch = () => {

    const fetchData = async (url:string, requestMethod:string, requestBody: object) => {
        try {
            const response = await fetch(`http://localhost:8080${url}`, {
                method: requestMethod,
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(requestBody)
            });
    
            const result = await response.json();
    
            return {
                data: result,
                responseStatus: response.status
            }
        } catch (error) {
            throw new Error("Failed to fetch")
        }
        
    };

    return {
        fetchData
    };
};