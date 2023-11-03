document.addEventListener("DOMContentLoaded", function() {
    var UID = sessionStorage.getItem('UID');  // Fetch UID from sessionStorage
    if (!UID) {
        console.error("User not logged in");
        return;
    }
    var url = "/api/users/" + UID;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok " + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            document.getElementById("name").textContent = data.name;
            document.getElementById("email").textContent = data.email;
            document.getElementById("username").textContent = data.username;
        })
        .catch(error => {
            console.error("There was a problem with the fetch operation:", error);
        });
});

