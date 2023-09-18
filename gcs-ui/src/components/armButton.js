import Button from "./button"

function ArmDrone() {
    fetch('http://localhost:8081/arm', {
        method: 'GET',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
      });
}


function ArmButton() {

    return (
        <div >
            <Button
                onClick={() => ArmDrone()}>Arm Drone</Button>
        </div>
    )
}

export default ArmButton
