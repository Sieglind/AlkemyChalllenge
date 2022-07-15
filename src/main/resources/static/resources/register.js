let registerForm = document.getElementById('registerForm');
registerForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    let form = e.currentTarget;
    let url = form.action;
    try {
        console.log(new FormData(form).entries())
        obj = Object.fromEntries(new FormData(form).entries())
        let formDataJsonString = JSON.stringify(obj);
        let responseData = postFromFieldsAsJson({url, formDataJsonString});
    } catch (error) {
        console.error(`An error has ocurred ${error}`);
    }
})

async function postFromFieldsAsJson({url, formDataJsonString}) {
    let fetchOptions = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Accept: "*/*",
        },
        body: formDataJsonString,
    };
    let res = await fetch(url, fetchOptions);
    if (!res.ok) {
        let error = await res.text();
        throw new Error(error);
    }
    return res;
}