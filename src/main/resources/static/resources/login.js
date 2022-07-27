let loginForm = document.getElementById('loginForm');
loginForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    let form = e.currentTarget;
    let url = form.action;
    try {
        let data = new FormData(form);
        let userName = data.get("userName").toString();
        let password = data.get("password").toString();
        let bodyContent = "username=" + encodeURIComponent(userName) + "&password=" + encodeURIComponent(password);
        let response = await postFromFieldsAsJson({url,bodyContent});
        if (response.ok){
            let tokens = await response.json();
            sessionStorage.setItem("sessionStatus","authorized");
            sessionStorage.setItem("accessToken",tokens.accessToken);
            sessionStorage.setItem("refreshToken",tokens.refreshToken);
            sessionStorage.setItem("userName",userName)
        }
        if(!response.ok){
            sessionStorage.setItem("sessionStatus","unauthorized");
        }
        setTimeout(()=>{window.location="/index.html"},500);
    } catch (error) {
        console.error(`An error has ocurred ${error}`);
    }
})

async function postFromFieldsAsJson({url,bodyContent}){
    let fetchOptions = {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
            Accept: "application/json",
        },
        body: bodyContent,
    };
    return await fetch(url, fetchOptions);
}