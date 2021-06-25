window.onload = retrievePage;

async function retrievePage(){
    window.history.forward();
    let response = await fetch('project/employee/fetch',{
        method: 'GET'
    });
    let results = await response.json();
    console.log(response);
    console.log(results);
    if(response["status"] === 200)
    {
        let tab = document.getElementById("ret_tab");
        let t = "";
        for(let i = 0; i < results.length; i++) {
            let tr = "<tr>"
            tr += "<td>" + results[i]["emp"]["employee_id"] +"</td>";
            tr += "<td>" + results[i]["emp"]["first_name"] + " " + results[i]["emp"]["last_name"] + "</td>";
            tr += "<td>" + results[i]["emp"]["email"] + "</td>";
            tr += "<td>" + results[i]["emp"]["title"] + "</td>";
            tr += "<td>" + results[i]["dep"] + "</td>";
            tr += "</tr>";
            t += tr;
        }
        tab.innerHTML += t;
    }
    else{
        console.log("Error");
    }
}