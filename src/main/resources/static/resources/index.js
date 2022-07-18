let banner = document.getElementById("banner");
let taskSelector = document.getElementById("taskSelector");
let register = document.getElementById("register");
let login = document.getElementById("login");
let logout = document.getElementById("logout");

logout.addEventListener("click",()=>{
    sessionStorage.clear();
    window.location.reload();
})

switch (sessionStorage.getItem("sessionStatus")){
    case "authorized":
        banner.textContent = "Welcome " + sessionStorage.getItem("userName");
        register.style.display = "none";
        login.style.display = "none";
        logout.style.display = "block";
        taskSelector.style.display = "block";
        break;
    case "unauthorized":
        banner.textContent = "Invalid Credentials";
        break;
}