/** Fetches the current date from the server and adds it to the page. */
async function showServerString() {
  const responseFromServer = await fetch('/hello');
  const textFromResponse = await responseFromServer.text();

  const stringContainer = document.getElementById('string-container');
  stringContainer.innerText = textFromResponse;
}

async function showMessages()
{
    const responseFromServer = await fetch('/upload');
    const textFromResponse = await responseFromServer.text();
    const stringContainer = document.getElementById('message-container');
    stringContainer.innerText = textFromResponse;
}


/** Fetches tasks from the server and adds them to the DOM. */
function loadMessages() {
  fetch('/upload').then(response => response.json()).then((messages) => {
    const messagesListEl = document.getElementById('task-list');
    messages.forEach((message) => {
      const messageEl = document.createElement('li');
      messageEl.className = "Message";
      messageEl.innerHTML ='';
      messageEl.appendChild(createListElement(message.comment));
      messageEl.appendChild(createListElement("---"));
      messageEl.appendChild(createListElement(message.timestamp));
      messageEl.appendChild(document.createElement('hr'));
      messagesListEl.appendChild(messageEl);
    })
  });
}

/** Creates an <li> element containing text. */
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}
