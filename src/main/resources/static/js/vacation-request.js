document.addEventListener("DOMContentLoaded", function () {
    const employeeId = "E001" //仮ログインユーザー
    const form = document.getElementById("vacation-request-form");
    const messageDiv = document.getElementById("message");

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
});
