const baseServerURL="http://localhost:8080";

let submitBTN=document.getElementById("submit");
let outputArea=document.querySelector(".non-editable-textarea");

submitBTN.addEventListener("click",function(){
    let inputArea=document.querySelector(".input");
    let data=inputArea.value;
    let inputLang=document.getElementById("inputLanguage").value;
    let outputLang=document.getElementById("outputLanguage").value;
    fetchData(data,inputLang,outputLang);
});

async function fetchData(data,inputLang,outputLang){
    showLoader();
    outputArea.innerText="";
    const url = `${baseServerURL}/translator/${inputLang}/${outputLang}` ;
    fetch(url, {
        method: "POST",
        body: JSON.stringify(data),
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