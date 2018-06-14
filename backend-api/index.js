const http = require('http');

http.createServer((request, response) => {
  let body = [];
  request.on('data', (chunk) => {
    body.push(chunk);
  }).on('end', () => {
    body = Buffer.concat(body).toString();
    response.end(body);
  });
  console.log("incoming headers: " + JSON.stringify(request.headers) + "\n");
  // console.log(request.rawHeaders);
}).listen(8080);