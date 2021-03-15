// Function that receives the facts as a JSON from the servlet and display them at the webpage.
async function getFacts() {
    const getResponse = await fetch('/facts');
    const facts = await getResponse.json();

    const index = Math.floor(Math.random() * 3);
    const factsContainer = document.getElementById('facts-container');
    factsContainer.innerText = '';

    factsContainer.innerText = facts[index];

}