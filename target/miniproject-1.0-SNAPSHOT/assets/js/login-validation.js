document.getElementById("No-user").style.display = "hidden";
let l = document.getElementById('login-validation');

l.addEventListener('submit',async (e)=> {
    e.preventDefault();
    e.stopPropagation();
    if(l.checkValidity() === true){
        let response = await fetch('project/employee/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                email: document.getElementById('email').value,
            })
        });
        try{
            let result = await response.json();
            sessionStorage.setItem("result",JSON.stringify(result));
            location.href = "dashboard.html";
        }
        catch (e)
        {
            document.getElementById('login-validation').style.display = "none";
            document.getElementById("login-title").style.display = "none";
            document.getElementById("No-user").classList.remove("hid");
            document.getElementById("No-user").classList.add("no-user");
        }

    }
});




