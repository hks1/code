use this config for below error "unable to get local issuer certificate"
export NODE_TLS_REJECT_UNAUTHORIZED=0

$ nodemon app.js
[nodemon] 2.0.20
[nodemon] to restart at any time, enter `rs`
[nodemon] watching path(s): *.*
[nodemon] watching extensions: js,mjs,json
[nodemon] starting `node app.js`
lhasdpgidhasihsadvasdgasbgsadfbjklhasjhvbdasljbdaslvnasldufashgldasudnifluhasdulghasdlugnasdlgaldsblasdnasldnvadfhasifhasdgUsing 'form_post' for response_mode may cause issues for you logging in over http, see https://github.com/auth0/express-openid-connect/blob/master/FAQ.md
listening on port 3000
(node:20384) UnhandledPromiseRejectionWarning: RequestError: unable to get local issuer certificate
    at ClientRequest.<anonymous> (C:\workspace\github\code\node\nodejs-user-auth-10minutes\node_modules\got\dist\source\core\index.js:970:111)
    at Object.onceWrapper (events.js:422:26)
    at ClientRequest.emit (events.js:327:22)
    at ClientRequest.origin.emit (C:\workspace\github\code\node\nodejs-user-auth-10minutes\node_modules\@szmarczak\http-timer\dist\source\index.js:43:20)
    at TLSSocket.socketErrorListener (_http_client.js:426:9)
    at TLSSocket.emit (events.js:315:20)
    at emitErrorNT (internal/streams/destroy.js:92:8)
    at emitErrorAndCloseNT (internal/streams/destroy.js:60:3)
    at processTicksAndRejections (internal/process/task_queues.js:84:21)
    at TLSSocket.onConnectSecure (_tls_wrap.js:1496:34)
    at TLSSocket.emit (events.js:315:20)
    at TLSSocket._finishInit (_tls_wrap.js:938:8)
    at TLSWrap.ssl.onhandshakedone (_tls_wrap.js:696:12)
(node:20384) UnhandledPromiseRejectionWarning: Unhandled promise rejection. This error originated either by throwing inside of an async function without a catch block, or by rejecting a promise which was not handled with .catch(). To terminate the node process on unhandled promise rejection, use the CLI flag `--unhandled-rejections=strict` (see https://nodejs.org/api/cli.html#cli_unhandled_rejections_mode). (rejection id: 1)
(node:20384) [DEP0018] DeprecationWarning: Unhandled promise rejections are deprecated. In the future, promise rejections that are not handled will terminate the Node.js process with a non-zero exit code.