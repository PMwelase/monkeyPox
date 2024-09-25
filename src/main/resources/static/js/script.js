function sendPostRequest() {
        const name = document.getElementById('name').value.trim();
        const command = document.getElementById('command').value.trim();
        const args = document.getElementById('arguments').value.trim().split(',');

        if (!name || !command || args.length === 0 || args[0] === "") {
            document.getElementById('response').innerHTML = `<div class="alert alert-warning">Please fill out all fields.</div>`;
            return;
        }

        document.getElementById('loading').style.display = 'block';
        document.getElementById('response').innerHTML = '';

        const requestBody = { name: name, command: command, arguments: args };

        fetch('http://localhost:8081/monkeypox/play', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(requestBody)
        })
        .then(response => response.json())
        .then(data => {
            document.getElementById('loading').style.display = 'none';

            let resultHtml = '';

            if (data.status === "success") {
            appendMessages([`<em>You </em> ${data.message}`]);
            } else {
                resultHtml = `<div class="alert alert-danger"><strong>Error:</strong> ${data.message}</div>`;
            }

            // Display weapons, items, and players even if they are empty
            resultHtml += `<div><strong>Weapons:</strong> ${data.Weapons && data.Weapons.length > 0 ? data.Weapons.join(', ') : 'None'}</div>`;
            resultHtml += `<div><strong>Items on the floor:</strong> ${data["Items on the floor"] && data["Items on the floor"].length > 0 ? data["Items on the floor"].join(', ') : 'None'}</div>`;
            resultHtml += `<div><strong>Players:</strong> ${data.Players && data.Players.length > 0 ? data.Players.join(', ') : 'None'}</div>`;

            document.getElementById('response').innerHTML = resultHtml;
        })
        .catch(error => {
            document.getElementById('loading').style.display = 'none'; // Hide loading message
            document.getElementById('response').innerHTML = `<div class="alert alert-danger">Error: ${error}</div>`;
        });
    }

// This function will send the move command
function sendMoveCommand(direction) {
    const name = document.getElementById('name').value.trim();
    if (!name) {
        document.getElementById('response').innerHTML = `<div class="alert alert-warning">Please enter a player name.</div>`;
        return;
    }

    const requestBody = { name: name, command: 'move', arguments: [direction] };

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

       // After fetching and parsing the response:
//       resultHtml += `<div class="alert alert-success"><strong>Result:</strong> ${data.message}</div>`;

       document.getElementById('location').innerText = data.roomState && data.roomState.Location ? data.roomState.Location : "You're lost";
       document.getElementById('tag').innerText = data.roomState && data.roomState.Tag ? data.roomState.Tag: data.roomState.Type;
       let itemsArray = data.roomState.Items;
       if (itemsArray && itemsArray.length > 0) {
           let itemCounts = {};

           // Count occurrences of each item
           itemsArray.forEach(item => {
               if (itemCounts[item]) {
                   itemCounts[item]++;
               } else {
                   itemCounts[item] = 1;
               }
           });

           // Create the output string
           let output = Object.keys(itemCounts).map(item => {
               return itemCounts[item] > 1 ? `${item} x ${itemCounts[item]}` : item;
           }).join(', ');

           // Display the result
           document.getElementById('items').innerText = output;
       } else {
           document.getElementById('items').innerText = "No Items";
       }

       document.getElementById('weapons').innerText = data.roomState  && data.roomState.Weapons.length > 0 ? data.roomState.Weapons : "No Weapons";
       document.getElementById('players').innerText = data.roomState  && data.roomState.Players && data.roomState.Players.length > 0 ? data.roomState.Players : "You're alone";

      document.getElementById('hp').innerText = `${data.playerState.Health}/${data.playerState.MaxHealth}`;
      document.getElementById('stamina').innerText = data.playerState.Stamina;
      document.getElementById('xp').innerText = data.playerState.Experience;
      document.getElementById('weapons_list').innerText = data.playerState.WeaponsInventory && data.playerState.WeaponsInventory.length > 0 ? data.playerState.WeaponsInventory : "No weapons";
      document.getElementById('weapon_in_hand').innerText = data.playerState.Weapon_in_hand ? data.playerState.Weapon_in_hand : "No weapon";
      document.getElementById('inventory').innerText = data.playerState.ItemsInventory && data.playerState.ItemsInventory.length > 0 ? data.playerState.ItemsInventory : "No items";


       // For img1
       document.getElementById('img1').src = `Assets/${data.grid.rooms[2].type}-01.svg`;
       document.getElementById('img1').onerror = function() {
           this.src = 'Assets/circle-01.svg';  // Fallback image if not found
       };
       document.getElementById('img01').style.backgroundColor = `#${data.grid.rooms[2].Color}`;

       // For img2
       document.getElementById('img2').src = `Assets/${data.grid.rooms[5].type}-01.svg`;
       document.getElementById('img2').onerror = function() {
           this.src = 'Assets/circle-01.svg';
       };
       document.getElementById('img02').style.backgroundColor = `#${data.grid.rooms[5].Color}`;

       // For img3
       document.getElementById('img3').src = `Assets/${data.grid.rooms[8].type}-01.svg`;
       document.getElementById('img3').onerror = function() {
           this.src = 'Assets/circle-01.svg';
       };
       document.getElementById('img03').style.backgroundColor = `#${data.grid.rooms[8].Color}`;

       // For img4
       document.getElementById('img4').src = `Assets/${data.grid.rooms[1].type}-01.svg`;
       document.getElementById('img4').onerror = function() {
           this.src = 'Assets/circle-01.svg';
       };
       document.getElementById('img04').style.backgroundColor = `#${data.grid.rooms[1].Color}`;

       // For img5
       document.getElementById('img5').src = `Assets/${data.grid.rooms[4].type}-01.svg`;
       document.getElementById('img5').onerror = function() {
           this.src = 'Assets/circle-01.svg';
       };
       document.getElementById('center-block').style.backgroundColor = `#${data.grid.rooms[4].Color}`;

       // For img6
       document.getElementById('img6').src = `Assets/${data.grid.rooms[7].type}-01.svg`;
       document.getElementById('img6').onerror = function() {
           this.src = 'Assets/circle-01.svg';
       };
       document.getElementById('img06').style.backgroundColor = `#${data.grid.rooms[7].Color}`;

       // For img7
       document.getElementById('img7').src = `Assets/${data.grid.rooms[0].type}-01.svg`;
       document.getElementById('img7').onerror = function() {
           this.src = 'Assets/circle-01.svg';
       };
       document.getElementById('img07').style.backgroundColor = `#${data.grid.rooms[0].Color}`;

       // For img8
       document.getElementById('img8').src = `Assets/${data.grid.rooms[3].type}-01.svg`;
       document.getElementById('img8').onerror = function() {
           this.src = 'Assets/circle-01.svg';
       };
       document.getElementById('img08').style.backgroundColor = `#${data.grid.rooms[3].Color}`;

       // For img9
       document.getElementById('img9').src = `Assets/${data.grid.rooms[6].type}-01.svg`;
       document.getElementById('img9').onerror = function() {
           this.src = 'Assets/circle-01.svg';
       };
       document.getElementById('img09').style.backgroundColor = `#${data.grid.rooms[6].Color}`;

       document.getElementById('response').innerHTML = resultHtml;
    })
    .catch(error => {
        document.getElementById('response').innerHTML = `<div class="alert alert-danger">Error: ${error}</div>`;
    });
}

// Map clicked square to move direction
function getMoveDirection(x, y) {
    const centerX = 1;
    const centerY = 1;

    if (x == centerX - 1 && y == centerY - 1) return 1; //Top-left diagonal
    if (x == centerX - 1 && y == centerY) return 2; // Top-center
    if (x == centerX - 1 && y == centerY + 1) return 3;// Top-right diagonal

    if (x == centerX && y == centerY - 1) return 4;  // Middle-left
    if (x == centerX && y == centerY + 1) return 6;  // Middle-right

    if (x == centerX + 1 && y == centerY - 1) return 7;// Bottom-left diagonal
    if (x == centerX + 1 && y == centerY) return 8;  // Bottom-center
    if (x == centerX + 1 && y == centerY + 1) return 9; // Bottom-right <diagonal></diagonal>

    return null;
}

// This function will send the enter command
function sendEnterCommand() {
    const name = document.getElementById('name').value.trim();
    if (!name) {
        document.getElementById('response').innerHTML = `<div class="alert alert-warning">Please enter a player name.</div>`;
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

        document.getElementById('location').innerText = data.roomState && data.roomState.Location ? data.roomState.Location : "You're lost";
        document.getElementById('tag').innerText = data.roomState && data.roomState.Tag ? data.roomState.Tag: "";
        let itemsArray = data.roomState.Items;
        if (itemsArray && itemsArray.length > 0) {
            let itemCounts = {};

            // Count occurrences of each item
            itemsArray.forEach(item => {
                if (itemCounts[item]) {
                    itemCounts[item]++;
                } else {
                    itemCounts[item] = 1;
                }
            });

            // Create the output string
            let output = Object.keys(itemCounts).map(item => {
                return itemCounts[item] > 1 ? `${item} x ${itemCounts[item]}` : item;
            }).join(', ');

            // Display the result
            document.getElementById('items').innerText = output;
        } else {
            document.getElementById('items').innerText = "No Items";
        }

        document.getElementById('weapons').innerText = data.roomState  && data.roomState.Weapons.length > 0 ? data.roomState.Weapons : "No Weapons";
        document.getElementById('players').innerText = data.roomState  && data.roomState.Players && data.roomState.Players.length > 0 ? data.roomState.Players : "You're alone";

        document.getElementById('hp').innerText = `${data.playerState.Health}/${data.playerState.MaxHealth}`;
        document.getElementById('stamina').innerText = data.playerState.Stamina;
        document.getElementById('xp').innerText = data.playerState.Experience;
        document.getElementById('weapons_list').innerText = data.playerState.WeaponsInventory && data.playerState.WeaponsInventory.length > 0 ? data.playerState.WeaponsInventory : "No weapons";
        document.getElementById('weapon_in_hand').innerText = data.playerState.Weapon_in_hand ? data.playerState.Weapon_in_hand : "No weapon";
        document.getElementById('inventory').innerText = data.playerState.ItemsInventory && data.playerState.ItemsInventory.length > 0 ? data.playerState.ItemsInventory : "No items";
    })
    .catch(error => {
        document.getElementById('response').innerHTML = `<div class="alert alert-danger">Error: ${error}</div>`;
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
