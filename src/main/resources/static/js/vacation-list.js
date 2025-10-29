document.addEventListener("DOMContentLoaded", function () {
    const employeeId = "E001" //仮ログインユーザー
    const tbody = document.getElementById("vacationTableBody");

    // 休暇一覧
    fetch(`/api/vacations/employee/${employeeId}`)
        .then(response => {
            if(!response.ok) throw new Error("取得失敗");
            return response.json();
        })
        .then(data => {
            tbody.innerHTML = "";
            data.forEach(vac => {
                const tr = document.createElement("tr");
                tr.innerHTML = `
                    <td>${vac.startDate}</td>
                    <td>${vac.endDate}</td>
                    <td>${vac.vacationType}</td>
                    <td>${vac.reason}</td>
                    <td>${vac.status}</td>
                    <td>${vac.decidedBy || ""}</td>
                    <td>${vac.decidedDate ? vac.decidedDate.slice(0,10) : ""}</td>
                    <td>${vac.rejectReason || ""}</td>
                    `;
                tbody.appendChild(tr);
            });
        })
        .catch(error => {
            tbody.innerHTML = `<tr><td colspan="8">データ取得に失敗しました</td></tr>`;
            console.error(error);
        });  
});