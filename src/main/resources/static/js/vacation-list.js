document.addEventListener("DOMContentLoaded", function () {
    const employeeId = "E001" //仮ログインユーザー
    const employeeInfo = document.getElementById("employeeInfo");
    const vacationList = document.getElementById("vacationTableBody");
    const vacationSummary = document.getElementById("vacationSummary")

    // 休暇一覧
    fetch(`/api/vacation/employee/${employeeId}`)
        .then(response => {
            if(!response.ok) throw new Error("取得失敗");
            return response.json();
        })
        .then(data => {
            // 社員情報を表示
            if (data.length > 0) {
                const employeeName = data[0].employeeName || "";
                employeeInfo.textContent = `社員ID: ${employeeId} ${employeeName}`;
            } else {
                employeeInfo.textContent = `社員ID: ${employeeId}`;
            }

            vacationList.innerHTML = "";
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
                    <td>${vac.rejectedReason || ""}</td>
                    `;
                vacationList.appendChild(tr);
            });
        })
        .catch(error => {
            vacationList.innerHTML = `<tr><td colspan="8">データ取得に失敗しました</td></tr>`;
            console.error(error);
        });
    
    //　有給日数表示用
    fetch(`/api/vacation/summary/${employeeId}`)
        .then(response => {
            if(!response.ok) throw new Error("取得失敗");
            return response.json();
        })
        .then(data => {
            vacationSummary.innerHTML="";
            const tr = document.createElement("tr");
            tr.innerHTML = `
                <td>${data.paidLeaveTotal}</td>
                <td>${data.paidLeaveUsed}</td>
                <td>${data.paidLeaveRemaining}</td>
            `;
            vacationSummary.appendChild(tr);
        })
        .catch(error => {
        vacationSummary.innerHTML = `<tr><td colspan="8">データ取得に失敗しました</td></tr>`;
        console.error(error);
    });
});