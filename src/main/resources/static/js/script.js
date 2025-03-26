function sendPostRequest(event) {
    if (event) event.preventDefault();

    const name = JSON.parse(localStorage.getItem("requestBody"))?.name || "";
    const command = document.getElementById('command').value.trim();
    const args = document.getElementById('arguments').value.trim().split(',');

    if (!name || !command || (args.length === 0) || args[0] === "") {
        document.getElementById('response').innerHTML = `<div class="alert alert-warning">Please fill out all fields.</div>`;
        return;
    }

    document.getElementById('loading').style.display = 'block';

    const requestBody = { name: name, command: command, arguments: args };

    fetch('http://localhost:8081/monkeypox/play', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(requestBody)
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('loading').style.display = 'none';

        if (data.status === "success") {
            appendMessages([`${data.message}`]);
            // Clear response only for success
            document.getElementById('response').innerHTML = '';
        } else {
            // Only update response for errors
            document.getElementById('response').innerHTML =
              `<div class="alert alert-danger"><strong>Error:</strong> ${data.message}</div>`;
        }

        updateUI(data);

    })
    .catch(error => {
            document.getElementById('loading').style.display = 'none';
        document.getElementById('response').innerHTML =
          `<div class="alert alert-danger">Error: ${error}</div>`;
    });
}

// This function will send the move command
function sendMoveCommand(direction) {
    const name = JSON.parse(localStorage.getItem("requestBody"))?.name || "";

    if (!name) {
        document.getElementById('response').innerHTML = `<div class="alert alert-warning">Please enter a player name.</div>`;
        return;
    }

    const requestBody = { name: name, command: 'move', arguments: [direction] };

    // Updated URL here:
    fetch('http://localhost:8081/monkeypox/play', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestBody)
    })
    .then(response => response.json())
    .then(data => {
        let resultHtml = '';
        if (data.status === "success") {
            appendMessages([`${data.message}`]);
        } else {
            resultHtml = `<div class="alert alert-danger"><strong>Error:</strong> ${data.message}</div>`;
        }

        // For img1
        document.getElementById('img1').src = `Assets/${data.grid.rooms[2].type}-01.svg`;
        document.getElementById('img1').onerror = function() {
            this.src = 'Assets/circle-01.svg';
        };
        document.getElementById('img01').style.backgroundColor = `#${data.grid.rooms[2].Color}`;
        document.getElementById('img1').style.width = "80%";
        if (data.grid.rooms[2].type.startsWith('M') || data.grid.rooms[2].type.startsWith('E')) {
            document.getElementById('img1').style.width = "100%";
        }

        // For img2
        document.getElementById('img2').src = `Assets/${data.grid.rooms[5].type}-01.svg`;
        document.getElementById('img2').onerror = function() {
            this.src = 'Assets/circle-01.svg';
        };
        document.getElementById('img02').style.backgroundColor = `#${data.grid.rooms[5].Color}`;
        document.getElementById('img2').style.width = "80%";
        if (data.grid.rooms[5].type.startsWith('M') || data.grid.rooms[5].type.startsWith('E')) {
            document.getElementById('img2').style.width = "100%";
        }

        // For img3
        document.getElementById('img3').src = `Assets/${data.grid.rooms[8].type}-01.svg`;
        document.getElementById('img3').onerror = function() {
            this.src = 'Assets/circle-01.svg';
        };
        document.getElementById('img03').style.backgroundColor = `#${data.grid.rooms[8].Color}`;
        document.getElementById('img3').style.width = "80%";
        if (data.grid.rooms[8].type.startsWith('M') || data.grid.rooms[8].type.startsWith('E')) {
            document.getElementById('img3').style.width = "100%";
        }

        // For img4
        document.getElementById('img4').src = `Assets/${data.grid.rooms[1].type}-01.svg`;
        document.getElementById('img4').onerror = function() {
            this.src = 'Assets/circle-01.svg';
        };
        document.getElementById('img04').style.backgroundColor = `#${data.grid.rooms[1].Color}`;
        document.getElementById('img4').style.width = "80%";
        if (data.grid.rooms[1].type.startsWith('M') || data.grid.rooms[1].type.startsWith('E')) {
            document.getElementById('img4').style.width = "100%";
        }

        // For img5
        document.getElementById('img5').src = `Assets/${data.grid.rooms[4].type}-01.svg`;
        document.getElementById('img5').onerror = function() {
            this.src = 'Assets/circle-01.svg';
        };
        document.getElementById('center-block').style.backgroundColor = `#${data.grid.rooms[4].Color}`;
        document.getElementById('img5').style.width = "80%";
        if (data.grid.rooms[4].type.startsWith('M') || data.grid.rooms[4].type.startsWith('E')) {
            document.getElementById('img5').style.width = "100%";
        }

        // For img6
        document.getElementById('img6').src = `Assets/${data.grid.rooms[7].type}-01.svg`;
        document.getElementById('img6').onerror = function() {
            this.src = 'Assets/circle-01.svg';
        };
        document.getElementById('img06').style.backgroundColor = `#${data.grid.rooms[7].Color}`;
        document.getElementById('img6').style.width = "80%";
        if (data.grid.rooms[7].type.startsWith('M') || data.grid.rooms[7].type.startsWith('E')) {
            document.getElementById('img6').style.width = "100%";
        }

        // For img7
        document.getElementById('img7').src = `Assets/${data.grid.rooms[0].type}-01.svg`;
        document.getElementById('img7').onerror = function() {
            this.src = 'Assets/circle-01.svg';
        };
        document.getElementById('img07').style.backgroundColor = `#${data.grid.rooms[0].Color}`;
        document.getElementById('img7').style.width = "80%";
        if (data.grid.rooms[0].type.startsWith('M') || data.grid.rooms[0].type.startsWith('E')) {
            document.getElementById('img7').style.width = "100%";
        }

        // For img8
        document.getElementById('img8').src = `Assets/${data.grid.rooms[3].type}-01.svg`;
        document.getElementById('img8').onerror = function() {
            this.src = 'Assets/circle-01.svg';
        };
        document.getElementById('img08').style.backgroundColor = `#${data.grid.rooms[3].Color}`;
        document.getElementById('img8').style.width = "80%";
        if (data.grid.rooms[3].type.startsWith('M') || data.grid.rooms[3].type.startsWith('E')) {
            document.getElementById('img8').style.width = "100%";
        }

        // For img9
        document.getElementById('img9').src = `Assets/${data.grid.rooms[6].type}-01.svg`;
        document.getElementById('img9').onerror = function() {
            this.src = 'Assets/circle-01.svg';
        };
        document.getElementById('img09').style.backgroundColor = `#${data.grid.rooms[6].Color}`;
        document.getElementById('img9').style.width = "80%";
        if (data.grid.rooms[6].type.startsWith('M') || data.grid.rooms[6].type.startsWith('E')) {
            document.getElementById('img9').style.width = "100%";
        }

        updateUI(data);
    })
    .catch(error => {
        document.getElementById('response').innerHTML = `<div class="alert alert-danger">Error: ${error}</div>`;
    });
}

// Map clicked square to move direction
function getMoveDirection(x, y) {
    const centerX = 1;
    const centerY = 1;

    if (x == centerX - 1 && y == centerY - 1) return 1; // Top-left diagonal
    if (x == centerX - 1 && y == centerY) return 2; // Top-center
    if (x == centerX - 1 && y == centerY + 1) return 3; // Top-right diagonal

    if (x == centerX && y == centerY - 1) return 4;  // Middle-left
    if (x == centerX && y == centerY + 1) return 6;  // Middle-right

    if (x == centerX + 1 && y == centerY - 1) return 7; // Bottom-left diagonal
    if (x == centerX + 1 && y == centerY) return 8;  // Bottom-center
    if (x == centerX + 1 && y == centerY + 1) return 9; // Bottom-right diagonal

    return null;
}

// This function will send the enter command
function sendEnterCommand() {
    const name = JSON.parse(localStorage.getItem("requestBody"))?.name || "";

    if (!name) {
        document.getElementById('response').innerHTML = `<div class="alert alert-warning">Player name not found. Please register first.</div>`;
        return;
    }

    const requestBody = { name: name, command: 'door', arguments: [] };

    fetch('http://localhost:8081/monkeypox/play', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestBody)
    })
    .then(response => response.json())
    .then(data => {
        let resultHtml = '';
        if (data.status === "success") {
            appendMessages([`<em>You </em> ${data.message}`]);
        } else {
            resultHtml = `<div class="alert alert-danger"><strong>Error:</strong> ${data.message}</div>`;
        }
        document.getElementById('response').innerHTML = resultHtml;

        updateUI(data);
    })
    .catch(error => {
        document.getElementById('response').innerHTML =
            `<div class="alert alert-danger">Error: ${error}</div>`;
    });
}

// This function will send the FORTIFY command
function sendFortifyCommand() {
    const name = JSON.parse(localStorage.getItem("requestBody"))?.name || "";

    if (!name) {
        document.getElementById('response').innerHTML = `<div class="alert alert-warning">Player name not found. Please register first.</div>`;
        return;
    }

    const requestBody = { name: name, command: 'barricade', arguments: [] };

    fetch('http://localhost:8081/monkeypox/play', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestBody)
    })
    .then(response => response.json())
    .then(data => {
        let resultHtml = '';
        if (data.status === "success") {
            appendMessages([`${data.message}`]);
        } else {
            resultHtml = `<div class="alert alert-danger"><strong>Error:</strong> ${data.message}</div>`;
        }
        document.getElementById('response').innerHTML = resultHtml;

        updateUI(data);
    })
    .catch(error => {
        document.getElementById('response').innerHTML =
            `<div class="alert alert-danger">Error: ${error}</div>`;
    });
}

// This function will send the BREAK command
function sendBreakCommand() {
    const name = JSON.parse(localStorage.getItem("requestBody"))?.name || "";

    if (!name) {
        document.getElementById('response').innerHTML = `<div class="alert alert-warning">Player name not found. Please register first.</div>`;
        return;
    }

    const requestBody = { name: name, command: 'break', arguments: [] };

    fetch('http://localhost:8081/monkeypox/play', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestBody)
    })
    .then(response => response.json())
    .then(data => {
        let resultHtml = '';
        if (data.status === "success") {
            appendMessages([`${data.message}`]);
        } else {
            resultHtml = `<div class="alert alert-danger"><strong>Error:</strong> ${data.message}</div>`;
        }
        document.getElementById('response').innerHTML = resultHtml;

        updateUI(data);
    })
    .catch(error => {
        document.getElementById('response').innerHTML =
            `<div class="alert alert-danger">Error: ${error}</div>`;
    });
}



document.querySelectorAll('.picture-holder').forEach(function(holder) {
    holder.addEventListener('click', function() {
        const position = this.getAttribute('data-position').split(',');
        const x = parseInt(position[0], 10);
        const y = parseInt(position[1], 10);
        const direction = getMoveDirection(x, y);

        if (direction) {
            sendMoveCommand(direction);
        } else {
            sendEnterCommand();
        }
    });
});

function appendMessages(messages) {
    const chatBox = document.getElementById('chatBox');

    messages.forEach(message => {
        const messageElement = document.createElement('p');
        messageElement.innerHTML = message;
        chatBox.insertBefore(messageElement, chatBox.firstChild);
    });
}



function updateUI(data) {
    // Update location and tag
    document.getElementById('location').innerText = data.roomState?.Location || "You're lost";
    document.getElementById('tag').innerText = data.roomState?.Tag || "";

    // Process and display items
    const itemsArray = data.roomState?.Items || [];
    if (itemsArray.length > 0) {
        const itemCounts = itemsArray.reduce((acc, item) => {
            acc[item] = (acc[item] || 0) + 1;
            return acc;
        }, {});

        const output = Object.entries(itemCounts)
            .map(([item, count]) => count > 1 ? `${item} x ${count}` : item)
            .join(', ');

        document.getElementById('items').innerText = output;
    } else {
        document.getElementById('items').innerText = "No Items";
    }

    // Update weapons and players
    document.getElementById('weapons').innerText =
        data.roomState?.Weapons?.length > 0 ? data.roomState.Weapons : "No Weapons";
    document.getElementById('players').innerText =
        data.roomState?.Players?.length > 0 ? data.roomState.Players : "You're alone";

    // Update player stats
    document.getElementById('hp').innerText =
        `${data.playerState?.Health}/${data.playerState?.MaxHealth}`;
    document.getElementById('stamina').innerText = data.playerState?.Stamina;
    document.getElementById('xp').innerText = data.playerState?.Experience;

    // Update inventories
    document.getElementById('weapons_list').innerText =
        data.playerState?.WeaponsInventory?.length > 0 ? data.playerState.WeaponsInventory : "No weapons";
    document.getElementById('weapon_in_hand').innerText =
        data.playerState?.Weapon_in_hand || "No weapon";
    document.getElementById('inventory').innerText =
        data.playerState?.ItemsInventory?.length > 0 ? data.playerState.ItemsInventory : "No items";
}


function fortifyAction() {
    sendFortifyCommand();
}

function breakAction() {
    sendBreakCommand();
}
