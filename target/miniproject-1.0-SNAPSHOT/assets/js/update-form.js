window.onload = getValues;
let update = document.getElementById("upd_form");
let dropdown = document.getElementById("dep_get");
async function getValues() {
    let response = await fetch('project/department/fetch', {
        method: 'GET'
    })
    let results = await response.json();
    if (response["status"] === 200) {
        let dropdown = document.getElementById("dep_get");
        for (let i = 0; i < results.length; i++) {
            dropdown.innerHTML += "<option>" + results[i] + "</option>";
        }
    }
    let curr = JSON.parse(sessionStorage.getItem("update"));
    console.log(curr);
    let response2 = await fetch('project/department/name', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            email: curr["email"]
        })
    });
    if(response2["status"] === 200)
    {
        let data = await response2.json();
        console.log("Data is: ")
        console.log(data);
        document.getElementById("dep_get").value = data["name"];
        sessionStorage.setItem("depname",data["name"]);
    }
    else{
        alert("Could not find the department");
    }
    document.getElementById("first_n").value = curr["first_name"];
    document.getElementById("last_n").value = curr["last_name"];
    document.getElementById("email_u").value = curr["email"];
    document.getElementById("title_u").value = curr["title"];
    document.getElementById("img").value = curr["photo_path"];
}

update.addEventListener('submit',async (e)=>{
    e.preventDefault();
    e.stopPropagation();
    let dept = document.getElementById('dep_get');
    let curr = JSON.parse(sessionStorage.getItem("update"));
    console.log(curr);
    console.log("This is a test");
    let photo;
    if(document.getElementById("img").value){
        photo = document.getElementById("img").files[0].name;
    }
    console.log(photo);
    console.log(curr["employee_id"]);
    let response = await  fetch('project/employee/update',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            dep: dept.options[dept.selectedIndex].text,
            emp: {
                first_name: document.getElementById("first_n").value,
                last_name: document.getElementById("last_n").value,
                email: document.getElementById("email_u").value,
                title: document.getElementById("title_u").value,
                photo_path: photo,
                employee_id : curr["employee_id"]
            }
        })
    })
    console.log(response);
    if(response["status"] === 200)
    {
        alert("Update successfull!");
        location.href = 'dashboard.html';
    }
    else{
        alert("Entered email already exists");
    }
})

dropdown.addEventListener('change',async (e)=>{
    e.preventDefault();
    e.stopPropagation();
    let dept = document.getElementById('dep_get');
    let curr = sessionStorage.getItem("depname");
    if( dept.options[dept.selectedIndex].text != curr ){
        let response = await fetch('project/department/count',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                name : dept.options[dept.selectedIndex].text,
            })
        })
        console.log(response);
        if(response["status"] === 200)
        {
            console.log("Changed successfully");
        }
        else {
            alert("Not enough space in the department");
            dept.options[dept.selectedIndex].text = curr;
        }
    }
})