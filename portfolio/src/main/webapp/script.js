// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['Hello world!', 'Jambo!', 'Mambo vipi?', 'Habari!'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

function addRandomQuote() 
{
    // Array with different type of stuff I love.
    const quotes = ['I love video games!', 'I love food!', 'I love programming!', 'I love basketball!'];

    // Pick a random quote.
    const quote = quotes[Math.floor(Math.random() * quotes.length)];

    // Add it to the page.
    const quoteContainer = document.getElementById('quote-container');
    quoteContainer.innerText = quote;
}

/**
 * Creates an image given a source and appends it to the parent.
 * @param {URL} src Source of image
 * @param {Document Element} parent Element to append image to 
 */
function appendImage(src, parent) {
    let img = document.createElement("img");
    img.src = src;
    parent.appendChild(img);
}

function appendText(txt, parent) {
    let p = document.createElement("p");
    p.innerText = txt;
    parent.appendChild(p);
}
/**
 * Gets a list of images from the server,
 * trying the JSON method of listing the files
 */
async function fetchImages()
{
    const responseFromServer = await fetch('/list-images');
    const jsonFromServ = await responseFromServer.json();
    console.log(`Did the JSON return as an actual object? ${jsonFromServ != null}`);
    console.log(jsonFromServ);
    const imageContainer = document.getElementById("images");
    const doc = document;
    if (jsonFromServ.length > 0) {
        jsonFromServ.forEach(function(src) {
            appendImage(src.mediaLink, imageContainer);
            appendText(src.tag, imageContainer);
        });
    } else {
        imageContainer.innerText = "No images yet. :(";
    }
}