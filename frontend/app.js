const express = require('express');
const app = express();

var fs = require('fs')
var https = require('https')

const jsStringify = require('js-stringify');

const dotenv = require('dotenv');
dotenv.config();

app.use(express.static('public'));
app.set('view engine', 'ejs');
// app.set('view engine', 'pug');

const { auth, requiresAuth } = require('express-openid-connect');

let port = process.env.PORT;
let no_port = false; 
if (port == null || port == "") {
  no_port = true; 
  port = 3000;
}

let url = process.env.BASE_URL;
let config; 
if(url == null || url == "") {
  config = {
    authRequired: false,
    auth0Logout: true,
    secret: process.env.SECRET,
    baseURL: `https://localhost:${port}`,
    clientID: process.env.CLIENT_ID,
    issuerBaseURL: 'https://dev-2y4f0ojz.eu.auth0.com'
  };
} else {
  config = {
    authRequired: false,
    auth0Logout: true,
    secret: process.env.SECRET,
    baseURL: url,
    clientID: process.env.CLIENT_ID,
    issuerBaseURL: 'https://dev-2y4f0ojz.eu.auth0.com'
  };
}

// auth router attaches /login, /logout, and /callback routes to the baseURL
app.use(auth(config));

app.get('/',  function (req, res) {
  req.user = {
      isAuthenticated : req.oidc.isAuthenticated()
  };
  if (req.user.isAuthenticated) {
      req.user.name = req.oidc.user.name;
  }
  res.render('index', {jsStringify, user : req.user});
});

app.get("/log-in", (req, res) => {
  res.oidc.login({
    returnTo: '/',
    authorizationParams: {      
      screen_hint: "login",
    },
  });
});

app.get("/korisnicki-profil", function(req, res) {
  req.user = {
    isAuthenticated : req.oidc.isAuthenticated()
  };
  if (req.user.isAuthenticated) {
      req.user.name = req.oidc.user.name;
  }
  res.render('user-profile', {jsStringify, user : req.user});
})

if (no_port) {
  https.createServer({
    key: fs.readFileSync('server.key'),
    cert: fs.readFileSync('server.cert')
  }, app).listen(port, function () {
    console.log(`Server running at https://localhost:${port}`);
  });
} else {
  app.listen(port);
}
