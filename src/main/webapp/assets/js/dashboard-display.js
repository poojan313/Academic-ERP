window.onload = displayEmail;

function displayEmail() {
    let result_json = JSON.parse(sessionStorage.getItem("result"));
    document.getElementById("welcome").innerHTML="<h1>Welcome "+result_json["email"]+"!</h1>"    ;
    document.getElementById("Name").innerHTML="<h3>Name = "+result_json["first_name"]+" "+result_json["last_name"]+"</h3>"    ;
    document.getElementById("Title").innerHTML="<h3>Title = "+result_json["title"]+"</h3>"    ;
}

function register_load()
{
    location.href = "Register.html";
}

function retrieve_load()
{
    location.href = "Retrieve.html";
}

function update_load(){
    location.href = "Update.html"
}

function delete_load(){
    location.href = "Delete.html"

}