import React, { useState } from 'react';

import Button from "./button"

function TakeoffDrone() {
    fetch('http://localhost:8081/takeoff', {
        method: 'GET',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
      });
}


function TakeoffButton() {    

    return (
       
            <Button
                onClick={() => TakeoffDrone()}>Takeoff Drone</Button>

    )
}

export default TakeoffButton
