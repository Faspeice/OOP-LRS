document.getElementById("filterForm").addEventListener("submit", function(event) {
    event.preventDefault();

    let A = document.getElementById("A").value;
    let B = document.getElementById("B").value;
    let C = document.getElementById("C").value;
    let maxDist = document.getElementById("maxDist").value;

    fetch(`/points?A=${A}&B=${B}&C=${C}&maxDist=${maxDist}`)
        .then(response => response.json())
        .then(data => {
            let table = document.getElementById("pointsTable");
            table.innerHTML = "";
            data.forEach((point, index) => {
                let row = `<tr><td>${index + 1}</td><td>${point.x}</td><td>${point.y}</td></tr>`;
                table.innerHTML += row;
            });
        })
        .catch(error => console.error("Ошибка:", error));
});
