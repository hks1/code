const express = require('express');
const app = express();
require('dotenv').config();

const config = {
    authRequired: false,
    auth0Logout: true,
    issuerBaseURL: process.env.ISSUER_BASE_URL,
    baseURL: process.env.BASE_URL,
    clientID: process.env.CLIENT_ID,
    secret: process.env.SECRET,
    idpLogout: true,
}

console.log(config.secret)

const { auth, requiresAuth } = require('express-openid-connect');
app.use(
    auth(config)
);

app.get('/', (req, res) => {
    console.log("app root");
    res.send(req.oidc.isAuthenticated() ? 'Logged in' : 'Logged out');
})

app.get('/profile', requiresAuth(), (req, res) => {
    res.send(JSON.stringify(req.oidc.user));
})

const port = process.env.PORT || 3000;
app.listen(port, () => {
    console.log(`listening on port ${port}`);
})

issuerBaseURL = process.env.ISSUER_BASE_URL;
const got = require('got');
(async () => {
    const response = await got(`${issuerBaseURL}/.well-known/openid-configurations`);
    console.log(response.body);
})();