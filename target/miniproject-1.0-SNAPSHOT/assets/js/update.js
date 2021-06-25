window.onload = loadDetails;

async function loadDetails(){

    let response = await fetch('project/employee/fetch',{
        method: 'GET'
    });
    let results = await response.json();
    console.log(response);
    console.log(results);
    if(response["status"] === 200)
    {
        let tab = document.getElementById("upd_tab");
        let t = "";
        for(let i = 0; i < results.length; i++) {
            let tr = "<tr>"
            tr += "<td>" + results[i]["emp"]["employee_id"] +"</td>";
            tr += "<td>" + results[i]["emp"]["first_name"] + " " + results[i]["emp"]["last_name"] + "</td>";
            tr += "<td>" + results[i]["emp"]["email"] + "</td>";
            tr += "<td>" + results[i]["emp"]["title"] + "</td>";
            tr += "<td>" + results[i]["dep"] + "</td>";
            tr += "<td><button class='btn btn-warning text-white' id=" + results[i]["emp"]["email"] +" onclick='updateData(this.id)'>Update</button>";
            tr += "</tr>";
            t += tr;
        }
        tab.innerHTML += t;
    }
    else{
        console.log("Error");
    }
}

async function updateData(id){
    let response = await fetch("project/employee/all",{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            email: id,
        })
    })
    console.log(response);
    if(response["status"] === 200){
        let update = await response.json();
        console.log(update["employee_id"]);
        sessionStorage.setItem("update",JSON.stringify(update));
        location.href = "Update-Form.html";
    }
}