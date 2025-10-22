INSERT INTO employee (employee_id, employee_name, password, birthday, mail, role, work_start, work_end, enabled, locked)
VALUES 
('E001', '山田 太郎', 'pass123', '1985-03-15', 'yamada@example.com', 'USER', '09:00:00', '18:00:00', 1, 0),
('E002', '佐藤 花子', 'pass456', '1990-07-22', 'sato@example.com', 'USER', '09:30:00', '18:30:00', 1, 0),
('E003', '鈴木 一郎', 'pass789', '1982-11-05', 'suzuki@example.com', 'ADMIN', '08:45:00', '17:45:00', 1, 0),
('E004', '高橋 美咲', 'passabc', '1995-01-10', 'takahashi@example.com', 'USER', '09:00:00', '18:00:00', 1, 0),
('E005', '伊藤 健太', 'passxyz', '1988-09-30', 'ito@example.com', 'USER', '10:00:00', '19:00:00', 1, 0);

INSERT INTO vacation (
    employee_id,
    start_date,
    end_date,
    vacation_type,
    status,
    reason,
    approver_id,
    approved_date,
    create_date,
    update_date
) VALUES
-- 申請中
('E001', '2025-10-01', '2025-10-01', 'PAID', 'APPLYING', '通院のため', NULL, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 承認済み
('E001', '2025-10-08', '2025-10-08', 'ABSENTEEISM', 'APPROVED', '家族の都合', 'E003', '2025-10-05', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 棄却
('E001', '2025-10-10', '2025-10-10', 'PAID', 'REJECTED', '無断欠勤', 'E003', '2025-10-06', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 申請中
('E001', '2025-10-15', '2025-10-17', 'SPECIAL', 'APPLYING', '結婚式参加', NULL, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 承認済み
('E001', '2025-10-20', '2025-10-21', 'PAID', 'APPROVED', '私用', 'E003', '2025-10-18', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 棄却
('E001', '2025-10-22', '2025-10-22', 'ABSENTEEISM', 'REJECTED', '体調不良連絡なし', 'E003', '2025-10-20', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 申請中
('E001', '2025-10-29', '2025-10-29', 'HOLIDAY', 'APPLYING', '祝日', NULL, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
