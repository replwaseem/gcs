var cors = require('cors');

var express = require('express')
var app = express();

app.use(express.urlencoded({extended: true}));
app.use(express.json());


const UNSAFE_FRONT_END_URL= "*"; // Allow all origins

app.use(cors(
        { 
            origin: UNSAFE_FRONT_END_URL,
            methods: ["GET", "POST"] 
        }));

const http = require('http');
const server = http.createServer(app);

var MAVSDKDrone = require('./mavsdk-grpc.js')
var drone = new MAVSDKDrone()

app.get('/arm', function(req, res){

    console.log("Hellooo from arm!")
    
    drone.Arm()

    res.sendStatus(200);

});

app.get('/disarm', function(req, res){

    console.log("Hellooo from disarm!")
    
    drone.Disarm()

    res.sendStatus(200);

});

app.get('/takeoff', function(req, res){

    console.log("Hellooo from takeoff!")
    
    drone.Takeoff()

    res.sendStatus(200);

});

app.get('/land', function(req, res){

    console.log("Hellooo from land!")
    
    drone.Land()

    res.sendStatus(200);

});


app.get('/gps', function(req, res){

    console.log("fetching gps data!")
    res.send(drone.position)
});

server.listen(8081, function () {
    var host = server.address().address
    var port = server.address().port

    console.log("Example app listening at http://%s:%s", host, port)
});
