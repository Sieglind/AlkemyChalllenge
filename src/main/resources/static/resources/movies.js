let movieToSend = Object;
let characterCount = 0;

let characterFormContainer = document.getElementById("characterFormContainer");
let characterCountFormContainer = document.getElementById("characterCountFormContainer");
let movieFormContainer = document.getElementById("movieFormContainer");
let saveMovieFormContainer = document.getElementById("saveMovieFormContainer")

let addMovieForm = document.getElementById("addMovieForm");
addMovieForm.addEventListener("submit", (e) => {
    e.preventDefault();
    let form = e.currentTarget;
    movieToSend = Object.fromEntries(new FormData(form).entries());
    movieToSend.characters = [];
    movieFormContainer.style.display = "none";
    characterCountFormContainer.style.display = "block";
});

let addCharacterCountForm = document.getElementById("addCharacterCountForm");
addCharacterCountForm.addEventListener("submit", (e) => {
    e.preventDefault();
    let form = e.currentTarget;
    characterCount = new FormData(form).get("characterCount");
    console.log(characterCount);
    characterCountFormContainer.style.display = "none";
    characterFormContainer.style.display = "block";
});

let addCharacterForm = document.getElementById("addCharacterForm");
addCharacterForm.addEventListener("submit", (e) => {
    e.preventDefault();
    let form = e.currentTarget;
    let characterToAdd = Object.fromEntries(new FormData(form).entries());
    movieToSend.characters.push(characterToAdd);
    if (characterCount === 1) {
        characterFormContainer.style.display = "none";
        saveMovieFormContainer.style.display = "block";
    } else {
        characterCount = characterCount - 1;
        addCharacterForm.reset();
    }
});

let saveMovieForm = document.getElementById("saveMovieForm");
saveMovieForm.addEventListener("submit",async(e) => {
    e.preventDefault();
    let fetchOptions = {
        method: "POST",
        headers: {
            'Authorization':'Bearer '+sessionStorage.getItem('accessToken'),
            'Content-Type': "application/json",
            'Accept': "*/*",
        },
        body:JSON.stringify(movieToSend),
    };
    let response = await fetch("/movies",fetchOptions);
    let body = await response.json();
    if (response.ok){
        window.location.reload();
    }
});