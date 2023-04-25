#create database canon;

use canon;

INSERT INTO operations_properties (property_name, property_value)
VALUES
('receiver_email', 'example@ukr.net'),
('request_time_pattern', '0 0 13 * * MON-FRI'),
('canon_ip', '192.168.1.205');

insert into departments(department_code, department_name)
values
(7654321, 'admin'),
(1, 'administrative'),
(2, 'vtosi'),
(3, '3'),
(4, '4'),
(5, '5'),
(6, '6'),
(7, '7'),
(8, '8'),
(9, '9'),
(10, '10'),
(11, '11'),
(12, '12'),
(13, '13'),
(14, '14'),
(15, '15'),
(-1, 'unresolved');

insert into printer_operations(id, all_operations, copybw, date, printbw, scanbw, scan_color, department_id)
VALUES
(1, 1000, 100, '2023-04-14', 900, 0 , 0, 10),
(2, 800, 100, '2023-04-13', 700, 0 , 0, 10),
(3, 800, 100, '2023-04-01', 700, 0 , 0, 11),
(4, 1800, 100, '2023-04-14', 1700, 0 , 0, 11);
