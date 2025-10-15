-- =========================
-- Employeeテーブル
-- =========================
CREATE TABLE IF NOT EXISTS employee (
    employee_id VARCHAR(50) PRIMARY KEY,
    employee_name VARCHAR(50),
    password VARCHAR(100),
    birthday DATE,
    mail VARCHAR(50),
    role VARCHAR(50),          -- "USER" / "ADMIN"
    work_start TIME,
    work_end TIME,
    enabled TINYINT(1) DEFAULT 1,
    locked TINYINT(1) DEFAULT 0,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================
-- Attendanceテーブル
-- =========================
CREATE TABLE IF NOT EXISTS attendance (
    attendance_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id VARCHAR(50),
    clock_in TIMESTAMP,
    clock_out TIMESTAMP,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);