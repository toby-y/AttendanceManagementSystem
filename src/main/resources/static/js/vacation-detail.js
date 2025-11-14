document.addEventListener("DOMContentLoaded",function(){
    const vacationId = window.location.pathname.split("/").pop();
    const decidedBy = "E003"; //仮ユーザー

    // 初期データ取得
    fetch(`/api/vacation/${vacationId}`)
    .then(res => res.json())
    .then(data => {
        document.getElementById("employeeId").textContent = data.employeeId;
        document.getElementById("employeeName").textContent = data.employeeName;
        document.getElementById("period").textContent = `${data.startDate} ～ ${data.endDate}`;
        document.getElementById("vacationType").textContent = data.vacationType;
        document.getElementById("reason").textContent = data.reason;
    });

    // ラジオボタンでの表示切り替え
    document.querySelectorAll('input[name="decision"]').forEach(radio => {
        radio.addEventListener("change", e => {
            document.getElementById("rejectReasonArea").style.display =
            e.target.value === "REJECT" ? "block" : "none";
        });
    });

    // フォーム送信
    document.getElementById("decisionForm").addEventListener("submit", e => {
        e.preventDefault();
        const decision = document.querySelector('input[name="decision"]:checked')?.value;
        const reason = document.getElementById("rejectReason").value.trim();

        if(!decision){
            alert("承認または棄却を選択してください")
            return;
        }
        if(decision === "REJECT" && !reason){
            alert("棄却理由を入力してください");
            return;
        }

        const url =
        decision === "APPROVE"
            ? `/api/vacation/${vacationId}/approve?decidedBy=${encodeURIComponent(decidedBy)}`
            : `/api/vacation/${vacationId}/reject?decidedBy=${encodeURIComponent(decidedBy)}&reason=${encodeURIComponent(reason)}`;

        fetch(url, {method: "POST" })
            .then(res => {
                if(!res.ok) throw new Error("更新失敗");
                alert(decision === "APPROVE" ? "承認しました" : "棄却しました");
                location.href = "/vacation/list";
            })
            .catch(err => alert(err.message));
    });
});