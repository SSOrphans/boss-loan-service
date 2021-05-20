INSERT INTO boss.loan (loan_number, type_id, user_id, branch_id, amount, interest_rate, taken_at, due_by, amount_due)
VALUES ('1234567890', 1, 1, 1, 50000, 0.02, NOW(), NOW(), 25000);

INSERT INTO boss.loan (loan_number, type_id, user_id, branch_id, amount, interest_rate, taken_at, due_by, amount_due)
VALUES ('2345678901', 2, 1, 1, 25000, 0.1, NOW(), NOW(), 5000);

INSERT INTO boss.loan_type (name)
VALUES ('LOAN_UNKNOWN');

INSERT INTO boss.loan_type (name)
VALUES ('LOAN_STUDENT');

INSERT INTO boss.loan_type (name)
VALUES ('LOAN_PERSONAL');