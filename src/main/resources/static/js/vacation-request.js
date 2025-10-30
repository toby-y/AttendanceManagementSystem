document.addEventListener("DOMContentLoaded", function () {
    const employeeId = "E001" //仮ログインユーザー
    const form = document.getElementById("vacation-request-form");
    const messageDiv = document.getElementById("message");

    const JpLocale = {
        weekdays: {
            shorthand: ['日','月','火','水','木','金','土'],
            longhand: ['日曜日','月曜日','水曜日','木曜日','金曜日','土曜日']
        },
        months: {
            shorthand: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
            longhand: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
        }
    };

    flatpickr("#startDate", {
        dateFormat: "Y/m/d(D)",
        locale: JpLocale,
        ohChange: function(selectedDates,dateStr) {
            endCalendar.style('minDate',dateStr);
        }
    });

    const endCalendar = flatpickr("#endDate", {
        dateFormat: "Y/m/d(D)",
        locale: JpLocale
    });

    // 休暇申請フォーム
    form.addEventListener("submit", function(event){
        event.preventDefault();

        const requestData = {
            employeeId: employeeId,
            startDate: document.getElementById("startDate").value,
            endDate: document.getElementById("endDate").value,
            vacationType: document.getElementById("vacationType").value,
            reason: document.getElementById("reason").value   
        };
        fetch(`/api/vacation/request`,{
            method: "POST",
            headers: { "Content-Type": "application/json"},
            body: JSON.stringify(requestData)
        })
        .then(response => {
            if(!response.ok) throw new Error("登録失敗");
            return response.json();
        })
        .then(savedVacation => {
            // 申請完了の通知
            alert("休暇申請が完了しました");

            // 一覧画面にリダイレクト
            window.location.href = `/vacation/employee/${employeeId}`;
        })
        .catch(err => {
            messageDiv.style.color = "red";
            messageDiv.textContent = "申請に失敗しました。";
            console.error(err);
        });
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
