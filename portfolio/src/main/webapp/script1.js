/** Fetches the current date from the server and adds it to the page. */
async function showServerString() {
  const responseFromServer = await fetch('/hello');
  const textFromResponse = await responseFromServer.text();

  const stringContainer = document.getElementById('string-container');
  stringContainer.innerText = textFromResponse;
}