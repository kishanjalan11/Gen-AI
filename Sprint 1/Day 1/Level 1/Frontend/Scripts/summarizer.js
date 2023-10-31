const baseServerURL="http://localhost:8080";

let submitBTN=document.getElementById("submit");
let outputArea=document.querySelector(".non-editable-textarea");

submitBTN.addEventListener("click",function(){
    let inputArea=document.querySelector(".input");
    let input=inputArea.value;
    fetchData(input);
});

async function fetchData(input){
    showLoader();
    outputArea.innerText="";
    const url = `${baseServerURL}/summarizer` ;
    fetch(url, {
        method: "POST",
        body: JSON.stringify(input),
        headers: {
            "Content-Type": "application/json"
           }
       })
       .then(function (res) {
            return res.text();
       })
       .then(function (res) {
            hideLoader();
            console.log(res);
            outputArea.innerText=res;
       })
       .catch(function (error) {
            console.error('Network Error',error);
            outputArea.innerText="Network Error. Please Try Later"
            hideLoader();
       });
}

//Loader
let loader=document.querySelector(".loader");

function showLoader(){
    loader.style.display="block";
}

function hideLoader(){
    loader.style.display="none";
}