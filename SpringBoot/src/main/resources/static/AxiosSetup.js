// Set up Axios interceptor
axios.interceptors.request.use(async function(config) {
    
    
    console.log("Interceptor called, Token:", token);
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    console.log("Token added to Authorisation header")
    return config;
}, function(error) {
    return Promise.reject(error);
});
