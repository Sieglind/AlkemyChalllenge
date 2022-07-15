let loginForm = document.getElementById('loginForm');
loginForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    let form = e.currentTarget;
    let url = form.action;
    try {
        let data = new FormData(form);
        let bodyContent = "userName=" + encodeURIComponent(data.get("userName")) + "&password=" + encodeURIComponent(data.get("password"));
        let responseData = postFromFieldsAsJson({url,bodyContent});
        console.log(responseData)
    } catch (error) {
        console.error(`An error has ocurred ${error}`);
    }
})

async function postFromFieldsAsJson({url,bodyContent}){
    let fetchOptions = {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
            Accept: "*/*",
        },
        body: bodyContent,
    };
    let res = await fetch(url, fetchOptions);
    if (!res.ok) {
        let error = await res.text();
        throw new Error(error);
    }
    setTimeout(()=>{window.location="/index.html"},1500);
    return res.json();
}