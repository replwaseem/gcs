import React, { useState } from 'react';

import Button from "./button"

function LandDrone() {
    fetch('http://localhost:8081/land', {
        method: 'GET',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
      });
}


function LandButton() {    

    return (
        <div>
            <Button
                onClick={() => LandDrone()}>Land Drone</Button>
        </div>
    )
}

export default LandButton
