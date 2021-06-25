window.onload = get_department;
let countCheck = document.getElementById('dep_get');
let register = document.getElementById("reg_form");

countCheck.addEventListener('change',async (e)=>{
    e.preventDefault();
    e.stopPropagation();
    let dept = document.getElementById('dep_get');
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
        document.getElementById("hid_div").classList.remove("hid");
        document.getElementById("hid_div").classList.add("no-hide");
    }
    else {
        document.getElementById("hid_div").classList.remove("no-hide");
        document.getElementById("hid_div").classList.add("hid");
        alert("Not enough space in the department");
    }
})

register.addEventListener('submit',async (e)=>{
    e.preventDefault();
    e.stopPropagation();
    let dept = document.getElementById('dep_get');
    let photo;
    if(document.getElementById("img").value){
        photo = document.getElementById("img").files[0].name;
    }
    console.log(photo);
    let response = await  fetch('project/employee/register',{
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
                photo_path: photo
            }
        })
    })
    console.log(response);
    if(response["status"] === 200)
    {
        location.href = "dashboard.html";
    }
    else{
        alert("Entered email already exists");
    }
})
async function get_department(){
    let response = await fetch('project/department/fetch',{
        method: 'GET'
    })
    let results = await response.json();
    if(response["status"] === 200)
    {
        let dropdown = document.getElementById("dep_get");
        for(let i=0; i<results.length; i++)
        {
            dropdown.innerHTML+="<option>"+results[i]+"</option>";
        }
    }
}