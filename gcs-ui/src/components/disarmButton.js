import React, { useState } from 'react';

import Button from "./button"

function DisarmDrone() {
    fetch('http://localhost:8081/disarm', {
        method: 'GET',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
      });
}


function DisarmButton() {    

    return (
        <div>
            <Button
                onClick={() => DisarmDrone()}>Disarm Drone</Button>
        </div>
    )
}

export default DisarmButton
