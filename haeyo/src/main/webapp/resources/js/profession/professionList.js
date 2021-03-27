const bookmark = document.querySelectorAll(".book");
const bookmark2 = document.querySelectorAll(".book2");
const score = document.querySelector(".score");
const ul = document.getElementsByClassName("star");



window.onload = function init(){
}

for (var i = 0; i < bookmark.length; i++) {
    bookmark[i].addEventListener('click', book, false);
}
for (var i = 0; i < bookmark2.length; i++) {
    bookmark2[i].addEventListener('click', book, false);
}


// function starScore() {
//     const li = document.createElement("li");
//     const scorInt = score.innerText;
//     for (let i = 0; i < scorInt; i++) {
//         li.appendChild(document.createTextNode("Four"));
//         ul[i].appendChild(li);
//     }
// }

function book() {
    (this.classList.contains("select")) ? this.classList.remove("select") : this.classList.add("select");
}