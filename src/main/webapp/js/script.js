document.getElementById("getData").addEventListener("click", function() {
    fetch("/hello")
        .then(response => response.json())
        .then(data => document.getElementById("response").innerHTML = data.message);
});
