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
    decided_by,
    decided_date,
    reject_reason,
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

-- 2025年 祝日データ（振替休日含む）
INSERT INTO holiday_calendar (holiday_date, holiday_type, description) VALUES ('2025-01-01', 'NATIONAL', '元日');
INSERT INTO holiday_calendar (holiday_date, holiday_type, description) VALUES ('2025-01-13', 'NATIONAL', '成人の日');
INSERT INTO holiday_calendar (holiday_date, holiday_type, description) VALUES ('2025-02-11', 'NATIONAL', '建国記念の日');
INSERT INTO holiday_calendar (holiday_date, holiday_type, description) VALUES ('2025-02-23', 'NATIONAL', '天皇誕生日');
-- 天皇誕生日が日曜のため振替休日（2025-02-24）
INSERT INTO holiday_calendar (holiday_date, holiday_type, description) VALUES ('2025-02-24', 'NATIONAL', '天皇誕生日（振替休日）');

INSERT INTO holiday_calendar (holiday_date, holiday_type, description) VALUES ('2025-03-20', 'NATIONAL', '春分の日');
INSERT INTO holiday_calendar (holiday_date, holiday_type, description) VALUES ('2025-04-29', 'NATIONAL', '昭和の日');
INSERT INTO holiday_calendar (holiday_date, holiday_type, description) VALUES ('2025-05-03', 'NATIONAL', '憲法記念日');
INSERT INTO holiday_calendar (holiday_date, holiday_type, description) VALUES ('2025-05-04', 'NATIONAL', 'みどりの日');
INSERT INTO holiday_calendar (holiday_date, holiday_type, description) VALUES ('2025-05-05', 'NATIONAL', 'こどもの日');
-- みどりの日が日曜のため振替休日（2025-05-06）
INSERT INTO holiday_calendar (holiday_date, holiday_type, description) VALUES ('2025-05-06', 'NATIONAL', 'みどりの日（振替休日）');

INSERT INTO holiday_calendar (holiday_date, holiday_type, description) VALUES ('2025-07-21', 'NATIONAL', '海の日');
INSERT INTO holiday_calendar (holiday_date, holiday_type, description) VALUES ('2025-08-11', 'NATIONAL', '山の日');
INSERT INTO holiday_calendar (holiday_date, holiday_type, description) VALUES ('2025-09-15', 'NATIONAL', '敬老の日');
INSERT INTO holiday_calendar (holiday_date, holiday_type, description) VALUES ('2025-09-23', 'NATIONAL', '秋分の日');

INSERT INTO holiday_calendar (holiday_date, holiday_type, description) VALUES ('2025-10-13', 'NATIONAL', 'スポーツの日');
INSERT INTO holiday_calendar (holiday_date, holiday_type, description) VALUES ('2025-11-03', 'NATIONAL', '文化の日');
INSERT INTO holiday_calendar (holiday_date, holiday_type, description) VALUES ('2025-11-23', 'NATIONAL', '勤労感謝の日');
-- 勤労感謝の日が日曜のため振替休日（2025-11-24）
INSERT INTO holiday_calendar (holiday_date, holiday_type, description) VALUES ('2025-11-24', 'NATIONAL', '勤労感謝の日（振替休日）');

